pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'changed_files="$(git diff-tree -r --name-only --no-commit-id $GIT_COMMIT $GIT_PREVIOUS_COMMIT)" && echo "$changed_files" | grep "src/" && eval "ls src/"'    
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
