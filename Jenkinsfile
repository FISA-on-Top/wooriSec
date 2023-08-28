pipeline {
    agent any

    environment{
        //REPOSITORY_CREDENTIAL_ID = 'gitlab-jenkins-key'
        REPOSITORY_URL = 'https://github.com/FISA-on-Top/wooriSec.git'
        //TARGET_BRANCH = ''

        ECR_NAME = 'AWS'
        ECR_PATH = '038331013212.dkr.ecr.ap-northeast-2.amazonaws.com'
        IMAGE_NAME = 'was'
        IMAGE_VERSION = "0.${BUILD_NUMBER}"
        AWS_REGION = 'ap-northeast-2'

        SSH_PATH = '/var/jenkins_home/.ssh/DevWAS.pem'
        WASSERVER_USERNAME = 'ubuntu'
        WASSERVER_IP = '10.0.12.174' 
        CONTAINER_NAME = 'was'
    }

    stages {
        stage('Test & Build gradle'){
            steps {
                echo "build"
                
                sh 'chmod +x ./gradlew'
                sh './gradlew clean build -x test'

                echo "test skipped"
                // sh './gradlew test'
            }
            post{
                success {
                    echo 'success testing & building gradle project '
                }
                failure {
                    error 'fail testing & building gradle project' // exit pipeline
                }
            }            

        }         
        stage('Build Docker Image'){
            steps{
                script{
                    sh '''
                    docker build -t ${IMAGE_NAME}:${IMAGE_VERSION} .
                    docker build -t ${IMAGE_NAME}:latest .
                    docker tag $IMAGE_NAME:$IMAGE_VERSION $ECR_PATH/$IMAGE_NAME:$IMAGE_VERSION
                    docker tag $IMAGE_NAME:latest $ECR_PATH/$IMAGE_NAME:latest
                    '''
                }
            }
            post{
                success {
                    echo 'success dockerizing project'
                }
                failure {
                    error 'fail dockerizing project' // exit pipeline
                }
            }
        }
        stage('Push to ECR') {
            steps {
                script {
                    // cleanup current user docker credentials
                    sh 'rm -f ~/.dockercfg ~/.docker/config.json || true'

                    docker.withRegistry("https://${ECR_PATH}", "ecr:${REGION}:${AWS_CREDENTIAL_NAME}") {
                      docker.image("${IMAGE_NAME}:${IMAGE_VERSION}").push()
                      docker.image("${IMAGE_NAME}:latest").push()
                    }

                    sh("docker rmi -f ${ECR_PATH}/${IMAGE_NAME}:${IMAGE_VERSION}")
                    sh("docker rmi -f ${ECR_PATH}/${IMAGE_NAME}:latest")
                    sh("docker rmi -f ${IMAGE_NAME}:${IMAGE_VERSION}")
                    sh("docker rmi -f ${IMAGE_NAME}:latest")

                }
            }
            post {
                success {
                    echo 'success upload image'
                }
                failure {
                    error 'fail upload image' // exit pipeline
                }
            }
        }

        stage('Pull and Deploy to WAS server') {
            when {
                branch 'develop'
                // anyOf {
                //     branch 'feature/*'
                //     branch 'develop'
                // }
            }            
            steps {
                echo "Current branch is ${env.BRANCH_NAME}"

                withCredentials([usernamePassword(credentialsId: 'aws-docker-access', passwordVariable: 'ECR_PASSWORD', usernameVariable: 'ECR_USERNAME')]){
                    sshagent(credentials:['devfront']) { 

                        sh """
                            ssh -t -i $SSH_PATH -o StrictHostKeyChecking=no $WASSERVER_USERNAME@$WASSERVER_IP '
                            
                                ls

                                # Login to ECR and pull the Docker image
                                echo "login into aws"
                                aws ecr get-login-password --region $REGION | docker login --username $ECR_USERNAME --password-stdin $ECR_PASSWORD
                                
                                # Pull image from ECR to web server
                                echo "pull the image from ECR "
                                docker pull $ECR_PATH/$IMAGE_NAME:latest

                                # Remove the existing container, if it exists
                                echo " remove docker container if it exists"
                                if docker ps -a | grep $CONTAINER_NAME; then
                                    docker rm -f $CONTAINER_NAME
                                fi
                                
                                # Run a new Docker container using the image from ECR
                                echo "docker run"
                                docker run -d \
                                -p 3000:3000\
                                --name $CONTAINER_NAME $ECR_PATH/$IMAGE_NAME:latest
                            '
                        """
                    }
                }
            }
            post{
                success {
                    echo 'success deploy to was server'
                }
                failure {
                    error 'fail deploy to was server' // exit pipeline
                }
            }
        }
    }
}