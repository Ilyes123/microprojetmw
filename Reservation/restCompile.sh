ant clean
ant build
javac -cp "lib/*:bin:*:$GLASSFISH_HOME/lib/javaee.jar" -d bin src/rest/*.java
