pipeline {
    agent any

    environment{
        TIME_ZONE = 'Asia/Seoul'

        //REPOSITORY_CREDENTIAL_ID = 'gitlab-jenkins-key'
        REPOSITORY_URL = 'https://github.com/FISA-on-Top/wooriSec.git'
        TARGET_BRANCH = 'feature/#2' 

        AWS_CREDENTIAL_NAME = 'ECR-access'
        ECR_NAME = 'AWS'
        ECR_PATH = '038331013212.dkr.ecr.ap-northeast-2.amazonaws.com'
        IMAGE_NAME = 'was'
        REGION = 'ap-northeast-2'

        SSH_PATH = '~/ECS_Key/DevWAS.pem'
        WASSERVER_USERNAME = 'ubuntu'
        WASSERVER_IP = '10.0.12.174' 
        CONTAINER_NAME = 'was'
    }

    stages {
        stage('init') {
            steps {
                echo 'init stage'
                deleteDir()
            }
            post {
                success {
                    echo 'success init in pipeline'
                }
                failure {
                    error 'fail init in pipeline'
                }
            }
        }
        stage('Clone'){
            steps{
                git branch: "$TARGET_BRANCH", 
                url: "$REPOSITORY_URL"
                sh "ls -al"
            }
            post{
                success {
                    echo 'success clone project'
                }
                failure {
                    error 'fail clone project' // exit pipeline
                }     
            }
        }

        stage('Test & Build gradle'){
            // agent{
            //     docker {
            //         image 'openjdk:11'
            //         args '-v "$PWD":/app'
            //         reuseNode true
            //     }
            // }
            steps {
                echo "build"
                
                sh 'chmod +x ./gradlew'
                sh './gradlew clean build -x test'

                echo "test"
                sh './gradlew test'
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
                    docker build --no-cache -t ${IMAGE_NAME}:${BUILD_NUMBER} .
                    docker build -t ${IMAGE_NAME}:latest .
                    docker tag $IMAGE_NAME:$BUILD_NUMBER $ECR_PATH/$IMAGE_NAME:$BUILD_NUMBER
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
                      docker.image("${IMAGE_NAME}:${BUILD_NUMBER}").push()
                      docker.image("${IMAGE_NAME}:latest").push()
                    }

                    sh("docker rmi ${IMAGE_NAME}:${BUILD_NUMBER}")
                    sh("docker rmi ${IMAGE_NAME}:latest")

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

        stage('Deploy to WAS server') {
            steps {
                sshagent(credentials:['devfront']) { 

                    sh """
                        ssh -t -i $SSH_PATH -o StrictHostKeyChecking=yes $WASSERVER_USERNAME@$WASSERVER_IP '
                        
                            # Login to ECR and pull the Docker image
                            aws ecr get-login-password --region $REGION | docker login --username $ECR_NAME --password-stdin $ECR_PATH
                            
                            # Pull image from ECR to web server
                            docker pull $ECR_PATH/$IMAGE_NAME:latest

                            # Remove the existing container, if it exists
                            if docker ps -a | grep $CONTAINER_NAME; then
                                docker rm -f $CONTAINER_NAME
                            fi
                            
                            # Run a new Docker container using the image from ECR
                            docker run -d \
                            -p 3000:3000\
                            --name $CONTAINER_NAME $ECR_PATH/$IMAGE_NAME:latest
                        '
                    """
                }
            }
        }
    }
}