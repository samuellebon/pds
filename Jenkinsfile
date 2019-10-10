pipeline {
    agent any 
    stages {

        stage('Continuous Integration') {

            stages {

                stage('Fetch from SCM') {

                    steps {
                        checkout scm
                    }

                }
      
                stage('Build and analysis') {

                    steps {
                        sh "mvn clean install -Dmaven.test.skip=true && mvn com.github.spotbugs:spotbugs-maven-plugin:3.1.7:spotbugs"

                    }
                                
                }

                stage('Publish Spotbugs') {

                    steps {

                        script {
                            def spotbugs = scanForIssues tool: spotBugs(pattern: '**/target/spotbugsXml.xml')
                            publishIssues issues: [spotbugs]
                        }

                    }

                }

            }

        }
    
    }
}