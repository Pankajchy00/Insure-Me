node{
    stage('code checkout'){
    git 'https://github.com/Pankajchy00/Insure-Me.git'
    }
    stage('bulding and packgeing'){
        sh 'mvn clean package'
    }
    stage('publishing the reoprt'){
        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '/var/lib/jenkins/workspace/insureme/target/surefire-reports', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
    }
    stage ('making image of the application'){
        sh "docker build -t pankajkumar921/insureme2:1.0 ."
    }
    stage('pushing to the dockerhub'){
        withCredentials([string(credentialsId: 'dockerhub', variable: 'dockerhub')]) {
            sh "docker login -u pankajkumar921 -p ${dockerhub}"
        }
    }
    stage('pushing it to dockerhub'){
        sh 'docker push pankajkumar921/insureme2:1.0'
    }
    stage('deplyoing on production server'){
        ansiblePlaybook become: true, credentialsId: 'ubuntu', disableHostKeyChecking: true, installation: 'ansible', inventory: '/etc/ansible/hosts', playbook: 'ansible-playbook.yml'
    }
    
}

