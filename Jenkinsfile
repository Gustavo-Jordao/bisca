pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'javac src/AppStart.java && ls src/'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
