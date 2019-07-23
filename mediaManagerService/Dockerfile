FROM openjdk:11.0.3-jdk-stretch
ADD ./target/mediaManagerService-0.0.1-SNAPSHOT.jar /app/user/mediaManagerService-0.0.1-SNAPSHOT.jar
WORKDIR /app/user
ENTRYPOINT ["java","-jar","mediaManagerService-0.0.1-SNAPSHOT.jar"]