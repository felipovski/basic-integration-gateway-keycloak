FROM openjdk:17-alpine
WORKDIR /
ADD target/gateway-0.0.1-SNAPSHOT.jar //
EXPOSE 8089

ENTRYPOINT ["java", "-jar", "/gateway-0.0.1-SNAPSHOT.jar"]