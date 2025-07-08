FROM openjdk:17-jdk-slim
COPY target/inopolis-0.0.1-SNAPSHOT.jar elk.jar
ENTRYPOINT ["java", "-jar", "elk.jar"]
