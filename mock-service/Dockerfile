FROM openjdk:17-alpine
WORKDIR /
ADD target/mock-service-0.0.1-SNAPSHOT.jar //
EXPOSE 8089

ENTRYPOINT ["java", "-jar", "/mock-service-0.0.1-SNAPSHOT.jar"]