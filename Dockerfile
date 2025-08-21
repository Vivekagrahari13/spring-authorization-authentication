FROM openjdk:17-jdk-slim
LABEL authors="Admin"
EXPOSE 8000
ADD target/spring-security-img-springboot.jar spring-security-img-springboot.jar
