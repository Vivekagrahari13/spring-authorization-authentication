FROM openjdk:17-jdk-slim
EXPOSE 8000
ADD target/spring-security-img-springboot.jar spring-security-img-springboot.jar

ENTRYPOINT ["java", "-jar", "/spring-security-img-springboot.jar"]
