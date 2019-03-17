# springboot-docker-jlink
Project created to study Jlink, Docker and SpringBoot applications

# Create Docker container
```
docker run --name docker-jlink -d -p 9000:9000 -p 9010:9010 --memory 400MB --memory-reservation 256MB --env JAVA_OPTS='-server -Xms64m -Xmx128m -XX:MaxMetaspaceSize=256m -XX:+UseStringDeduplication -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9010 -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false' augustomarinho/springboot-docker-jlink:jdk11-1.0.0-SNAPSHOT
```

# Create local Jlink custom modules
```
jlink --module-path $LOCAL_PATH/java/jdk/jmods --compress=2 --add-modules jdk.jfr,jdk.management.agent,java.base,java.logging,java.xml,jdk.unsupported,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument --no-header-files --no-man-pages --strip-debug --output $LOCAL_PATH/java/jdk-11-custom-modules
```
# Build project and Docker Container
```
    mvn clean install docker:build
```

# Misc
Use VisualVM to analyse JVM parameters (https://visualvm.github.io/download.html)