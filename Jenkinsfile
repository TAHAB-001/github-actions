pipeline {
    agent any

    environment {
        APP_NAME        = "springboot-demo"
        DOCKER_HUB_REPO = "tahabohra001/springboot-demo"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', credentialsId: 'github-creds', url: 'https://github.com/TAHAB-001/github-actions.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image with Docker Compose') {
            steps {
                sh '''
                    # Build images using docker-compose
                    docker-compose build

                    # Tag the app service image for Docker Hub
                    docker tag ${APP_NAME}_app:latest $DOCKER_HUB_REPO:latest
                '''
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                    sh '''
                        echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                        docker push $DOCKER_HUB_REPO:latest
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                withKubeConfig([credentialsId: 'kubeconfig-cred', serverUrl: 'https://<YOUR_CLUSTER_ENDPOINT>']) {
                    sh '''
                        kubectl apply -f k8s/springboot-mysql.yaml -n default
                        kubectl set image deployment/$APP_NAME $APP_NAME=$DOCKER_HUB_REPO:latest -n default --record
                        kubectl rollout status deployment/$APP_NAME -n default
                    '''
                }
            }
        }
    }
}
