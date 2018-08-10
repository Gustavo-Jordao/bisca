pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'changed_files="$(git diff-tree -r --name-only --no-commit-id ORIG_HEAD HEAD)" && check_run() { echo "$changed_files" | grep "$1" && eval "$2" } && check_run src/ "ls src/"'
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
