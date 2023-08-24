def PROJECT_NAME = 'backend'
def DOCKER_IMAGE = 'myrepo/picky-app'  // DockerHub 또는 다른 저장소의 이미지 이름을 설정합니다.

pipeline {
    agent any

    environment{
        TIME_ZONE = 'Asia/Seoul'

        //REPOSITORY_CREDENTIAL_ID = 'gitlab-jenkins-key'
        REPOSITORY_URL = 'https://github.com/FISA-on-Top/wooriSec.git'
        TARGET_BRANCH = 'main' 

        AWS_CREDENTIAL_NAME = 'ECR-access'
        ECR_PATH = '038331013212.dkr.ecr.ap-northeast-2.amazonaws.com'
        IMAGE_NAME = 'was'
        REGION = 'ap-northeast-2'

        WEBSERVER_USERNAME = 'ubuntu'
        WEBSERVER_IP = '43.201.20.90' 

        SSH_PATH = '~/Private_key/DevWAS.pem'
        WASERVER_USERNAME = 'ubuntu'
        WASERVER_IP = '10.0.12.174' 
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

        // stage('Build Docker Image'){
        //     when{
        //         // Dockerfile에 대한 변경 사항이 있는 경우에만 실행
        //         changeset "dockerfile"
        //     }
        //     steps{
        //         script{
        //             sh '''
        //             docker build --no-cache -t ${IMAGE_NAME}:${BUILD_NUMBER} .
        //             docker build -t ${IMAGE_NAME}:latest .
        //             docker tag $IMAGE_NAME:$BUILD_NUMBER $ECR_PATH/$IMAGE_NAME:$BUILD_NUMBER
        //             docker tag $IMAGE_NAME:latest $ECR_PATH/$IMAGE_NAME:latest
        //             '''
        //         }
        //     }
        //     post{
        //         success {
        //             echo 'success dockerizing project'
        //         }
        //         failure {
        //             error 'fail dockerizing project' // exit pipeline
        //         }
        //     }
        // }

        // stage('Push to ECR') {
        //     when{
        //         // Dockerfile에 대한 변경 사항이 있는 경우에만 실행
        //         changeset "dockerfile"
        //     }
        //     steps {
        //         script {
        //             // cleanup current user docker credentials
        //             sh 'rm -f ~/.dockercfg ~/.docker/config.json || true'

        //             docker.withRegistry("https://${ECR_PATH}", "ecr:${REGION}:${AWS_CREDENTIAL_NAME}") {
        //               docker.image("${IMAGE_NAME}:${BUILD_NUMBER}").push()
        //               docker.image("${IMAGE_NAME}:latest").push()
        //             }
        //         }
        //     }
        //     post {
        //         success {
        //             echo 'success upload image'
        //         }
        //         failure {
        //             error 'fail upload image' // exit pipeline
        //         }
        //     }
        // }

        stage('Prepare') {
            steps {
                sh 'gradlew clean'
            }
        }             
        stage('Build') {
            steps {
                sh 'gradlew build -x test'
            }
        }

        stage('Test') {
            steps {
                sh 'gradlew test'
            }
        }

        stage('Deploy') {
            steps {
                sshagent(['my-ssh-credentials']) {  // 서버에 SSH 연결하기 위한 Jenkins credentials ID를 설정합니다.
                    sh """
                        ssh -o StrictHostKeyChecking=no deploy@my-deployment-server "
                            docker pull ${DOCKER_IMAGE}
                            docker stop picky-app
                            docker rm picky-app
                            docker run -d --name picky-app -p 8080:8080 ${DOCKER_IMAGE}
                        "
                    """
                }
            }
        }
    }
}