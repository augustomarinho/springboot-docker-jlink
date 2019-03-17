FROM debian:sid-slim as jdk11-custom-modules-001

RUN apt-get update && \
    apt-get install -y wget && \
    cd /opt && \
    wget https://download.java.net/java/GA/jdk11/9/GPL/openjdk-11.0.2_linux-x64_bin.tar.gz && \
    tar zxf openjdk-11.0.2_linux-x64_bin.tar.gz && \
    ln -s jdk-11.0.2 java && \
    rm -f openjdk-11.0.2_linux-x64_bin.tar.gz

RUN /opt/jdk-11.0.2/bin/jlink \
    --module-path /programas/java/jdk/jmods \
    --compress=2 \
    --add-modules jdk.jfr,jdk.management.agent,java.base,java.logging,java.xml,jdk.unsupported,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument \
    --no-header-files \
    --no-man-pages \
    --strip-debug \
    --output /opt/jdk-11-custom-modules

FROM debian:sid-slim as jdk11_0_2
ENV JAVA_HOME=/opt/java
ENV PATH="$PATH:$JAVA_HOME/bin"
COPY --from=jdk11-custom-modules-001 /opt/jdk-11-custom-modules $JAVA_HOME
RUN ln -s $JAVA_HOME/bin/java /usr/bin/java


ARG TARGET_DIR
ARG JAR_NAME

ENV TARGET_DIR $TARGET_DIR
ENV JAR_NAME $JAR_NAME
ENV LOCAL_JAR_NAME="docker-jlink.jar"
ENV USER_APP="usr_app"

# Creating app directory
RUN mkdir /app

# Entrying in App dir
WORKDIR app

# Adding jar artefact to image
ADD ${TARGET_DIR}/${JAR_NAME} ${LOCAL_JAR_NAME}

# Creating Group and User and setting permission for User's runner of the Application
RUN groupadd $USER_APP; \
useradd $USER_APP -g $USER_APP; \
chmod -R 755 /app; \
chown -R $USER_APP:$USER_APP /app

# Run command below with this User and Permissions
USER $USER_APP

# Command to execute application
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ${LOCAL_JAR_NAME}" ]