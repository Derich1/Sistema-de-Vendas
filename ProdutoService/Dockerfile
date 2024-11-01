ARG ARG_PLATFORM=linux/amd64
FROM --platform=${ARG_PLATFORM} adoptopenjdk:11-jdk
LABEL maintainer="Derich Rosário"
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
