node{
    def mavenHome
    def mavenCMD
    def tagName
    stage('prepare enviroment'){
        echo 'initialize all the variables'
        mavenHome = tool name: 'maven' , type: 'maven'
        mavenCMD = "${mavenHome}/bin/mvn"
        tagName="3.0"
    }
    stage('git code checkout'){
        try{
            echo 'checkout the code from git repository'
            git credentialsId: 'jenkins-github', url: 'https://github.com/NubeEra-MCO/InsureMe.git'
        }
        catch(Exception e){
            echo 'Exception occured in Git Code Checkout Stage'
            currentBuild.result = "FAILURE"
            emailext body: '''Dear All,
            The Jenkins job ${JOB_NAME} has been failed. Request you to please have a look at it immediately by clicking on the below link. 
            ${BUILD_URL}''', subject: 'Job ${JOB_NAME} ${BUILD_NUMBER} is failed', to: 'mujahed.trainer@gmail.com'
        }
    }    
    stage('Build the Application'){
        echo "Cleaning... Compiling...Testing... Packaging..."
        sh "${mavenCMD} clean package"        
    }    
    stage('publish test reports'){
        echo "Reporting"
        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '/var/lib/jenkins/workspace/PL-InsureMe/target/surefire-reports', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
    }    
    stage('Containerize the application'){
        echo 'Creating Docker image'
        sh "docker build -t mujahed/insure-me:${tagName} ."
    }    
    stage('Pushing it ot the DockerHub'){
            echo 'Pushing the docker image to DockerHub'
            sh "docker login -u mujahed -p 'Admin_123'"
            sh "docker push mujahed/insure-me:${tagName}"            
    }        
    stage('Configure and Deploy to the test-server'){
        // ansiblePlaybook become: true, credentialsId: 'ansible-key', disableHostKeyChecking: true, installation: 'ansible', inventory: '/etc/ansible/hosts', playbook: 'ansible-playbook.yml'
        sh "ansible-playbook ansible-playbook.yml"
    }
}
