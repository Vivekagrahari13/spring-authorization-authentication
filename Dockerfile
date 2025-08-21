#FROM ubuntu:latest
#LABEL authors="Admin"
#EXPOSE 8000
#ADD target/spring-security-img-springboot.jar spring-security-img-springboot.jar
#
#ENTRYPOINT ["java", "-jar", "/spring-security-img-springboot.jar"]

# Use OpenJDK instead of Ubuntu for Java applications
FROM openjdk:17-jdk-slim

LABEL authors="Admin"

# Create a non-root user for security
RUN addgroup --system spring && adduser --system spring --ingroup spring

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/spring-security-img-springboot.jar app.jar

# Change ownership to spring user
RUN chown spring:spring app.jar

# Switch to non-root user
USER spring

# Expose port
EXPOSE 8000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
