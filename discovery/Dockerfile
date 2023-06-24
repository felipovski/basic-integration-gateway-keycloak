FROM openjdk:17-alpine
WORKDIR /
ADD target/discovery-0.0.1-SNAPSHOT.jar //
EXPOSE 2001

ENTRYPOINT ["java", "-jar", "/discovery-0.0.1-SNAPSHOT.jar"]