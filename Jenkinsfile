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

        stage('Build & Push Docker Image with Docker Compose') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                    sh '''
                        # Build app image using docker-compose
                        docker-compose build

                        # Login to Docker Hub
                        echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin

                        # Tag image from docker-compose (adjust service name if different)
                        docker tag docker-ci-pipeline_app:latest $DOCKER_HUB_REPO:latest

                        # Push to Docker Hub
                        docker push $DOCKER_HUB_REPO:latest
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                withKubeConfig([credentialsId: 'kubeconfig-cred']) {
                    sh '''
                        # Apply Kubernetes manifests
                        kubectl apply -f springboot-mysql.yaml

                        # Update deployment image to latest
                        kubectl set image deployment/$APP_NAME $APP_NAME=$DOCKER_HUB_REPO:latest -n default --record

                        # Wait for rollout
                        kubectl rollout status deployment/$APP_NAME -n default
                    '''
                }
            }
        }
    }
}
