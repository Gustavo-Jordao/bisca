pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'changed_files="$(git diff-tree -r --name-only --no-commit-id ORIG_HEAD HEAD)" && echo "$changed_files" | grep "src/" && eval "ls src/"'    
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
