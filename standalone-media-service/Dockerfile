FROM openjdk:11.0.3-jdk-stretch
ADD ./target/standalone-media-service-0.0.1-SNAPSHOT.jar /app/user/standalone-media-service-0.0.1-SNAPSHOT.jar
WORKDIR /app/user
ENTRYPOINT ["java","-jar","standalone-media-service-0.0.1-SNAPSHOT.jar"]