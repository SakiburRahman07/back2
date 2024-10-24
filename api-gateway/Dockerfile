# Use the official OpenJDK image as the base image
FROM openjdk:11-jre-slim

# Set the argument for the JAR file location
ARG JAR_FILE=target/*.jar

# Copy the JAR file from the build context to the container
COPY ${JAR_FILE} api-gateway.jar

# Expose the application port
EXPOSE 8500

# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]
