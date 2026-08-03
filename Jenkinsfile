pipeline {
    agent any
    tools {
        maven 'maven_3_5_0'
    }

     environment {
        DOCKER_IMAGE = "tofazzal/spring-boot-docker"
        IMAGE_TAG    = "${BUILD_NUMBER}"
        SPRING_PROFILE = "prod"
    }

    stages {
        stage('Build Maven') {
            steps {
                deleteDir()

                checkout scmGit(
                    branches: [[name: '*/main']], extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/TofazzalTopu/spring-boot-docker-jenkins-kubernates']]
                    )
                sh 'mvn clean package -DskipTests'
            }
        }

        stage("Build Docker Image") {
            steps {
                sh 'pwd'
                sh 'ls -la'
                sh 'docker build -t ${DOCKER_IMAGE}:${IMAGE_TAG} .'
            }
        }


        stage('Verify Docker') {
            steps {
                sh '''
                    whoami
                    docker --version
                    docker ps
                '''
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([
                    string(credentialsId: 'my-docker-hub-pwd', variable: 'DOCKER_PASS')
                ]) {

                    sh '''
                    echo $DOCKER_PASS | docker login -u tofazzal --password-stdin
                    docker push ${DOCKER_IMAGE}:${IMAGE_TAG}
                    '''
                }
            }
        }


        stage('Helm Lint') {
            steps {
                sh 'helm lint helm/spring-boot'
            }
        }

        stage('Helm Package') {
            steps {
                sh '''
                    mkdir -p helm-package
                    helm package helm/spring-boot --destination helm-package
                    ls -lh helm-package
                '''
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                withCredentials([
                    file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')
                ]) {

                    sh '''
                        export KUBECONFIG=$KUBECONFIG

                        helm upgrade --install spring-boot \
                            helm/spring-boot \
                            --set image.repository=${DOCKER_IMAGE} \
                            --set image.tag=${IMAGE_TAG} \
                            --set spring.profile=${SPRING_PROFILE}
                    '''
                }
            }
        }
        stage('Verify Deployment') {
            steps {
                withCredentials([
                    file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')
                ]) {

                    sh '''
                        export KUBECONFIG=$KUBECONFIG

                        kubectl get pods
                        kubectl get svc
                        kubectl rollout status deployment/spring-boot
                    '''
                }
            }
        }
    }
}