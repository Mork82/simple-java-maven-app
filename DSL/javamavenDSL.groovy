job('Java Maven App DSL') {
    description('Java Maven App con DSL para el curso de Jenkins')
    scm {
        git('https://github.com/Mork82/simple-java-maven-app.git', 'master') { node ->
            node / gitConfigName('Jose Corcoles')
            node / gitConfigEmail('joscorcon@gmail.com')
        }
    }
    steps {
        maven {
          mavenInstallation('mavenjenkins')
          goals('-B -DskipTests clean package')
        }
        maven {
          mavenInstallation('mavenjenkins')
          goals('test')
        }
        shell('''
          echo "Entrega: Desplegando la aplicación" 
          java -jar "/var/lib/jenkins/workspace/Java Maven App DSL/target/my-app-1.0-SNAPSHOT.jar"
        ''')
        publishers {
            archiveArtifacts('target/*.jar')
            archiveJunit('target/surefire-reports/*.xml')
        }
    }

}
