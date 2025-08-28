pipeline {
    agent any

    environment {
        APP_NAME        = "springboot-demo"
        DOCKER_IMAGE    = "springboot-demo:latest"
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

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                    sh '''
                        echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                        docker tag $DOCKER_IMAGE $DOCKER_HUB_REPO:latest
                        docker push $DOCKER_HUB_REPO:latest
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                // Apply the whole YAML manifest first (creates deployments, PVC, services)
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
