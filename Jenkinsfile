pipeline {
    agent any
    tools {
        maven 'maven_3_5_0'
    }

    stages {
        stage('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/TofazzalTopu/eureka-service']])
                sh 'mvn clean install'
            }
        }

        stage ("Build Docker Image"){
            steps{
                script {
                    sh 'docker build -t tofazzal/spring-boot-docker .'
                }
            }
        }
        stage("Push Docker Image To Docker Hub") {
            steps{
                script {
                    withCredentials([string(credentialsId: 'my-docker-hub-pwd', variable: 'my-docker-hub-pwd')]) {
                    sh 'docker login -u tofazzal -p ${my-docker-hub-pwd}'
                    }
                    sh 'docker push tofazzal/spring-boot-docker'
                }
            }
        }
    }

}