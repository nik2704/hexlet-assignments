FROM gradle:7.2.0-jdk16

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} docker.jar

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "docker.jar"]