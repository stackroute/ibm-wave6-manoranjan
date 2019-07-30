FROM openjdk:11.0.3-jdk-stretch
ADD ./target/userpayment-0.0.1-SNAPSHOT.jar /app/user/userpayment-0.0.1-SNAPSHOT.jar
WORKDIR /app/user
ENTRYPOINT ["java","-jar","userpayment-0.0.1-SNAPSHOT.jar"]