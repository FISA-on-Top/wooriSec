def PROJECT_NAME = 'backend'
def DOCKER_IMAGE = 'myrepo/picky-app'  // DockerHub 또는 다른 저장소의 이미지 이름을 설정합니다.

pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials'  // DockerHub 또는 다른 저장소의 Jenkins credentials ID를 설정합니다.
    }

    stages {
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

        stage('Build Docker Image') {
            steps {
                script {
                    def appVersion = sh(script: "echo ${PROJECT_NAME} | cut -d'-' -f2", returnStdout: true).trim()
                    sh "docker build -t ${DOCKER_IMAGE}:${appVersion} ."
                }
            }
        }

        // stage('Push Docker Image') {
        //     steps {
        //         script {
        //             withDockerRegistry(credentialsId: "${DOCKER_CREDENTIALS_ID}") {
        //                 sh "docker push ${DOCKER_IMAGE}"
        //             }
        //         }
        //     }
        // }

        stage('Deploy') {
            steps{
                git branch : 'feature/deploy',
                credentialsId: '',
                url : 'https://github.com/FISA-on-Top/Nginx.git'
            }
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

    post {
        always {
            archiveArtifacts artifacts: 'build/libs/*.jar', allowEmptyArchive: true
        }
    }
}