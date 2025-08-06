# Use OpenJDK image as the base
FROM openjdk:21-jdk-slim

# Set a working directory inside container
WORKDIR /app

# Copy the jar file into the container
COPY target/demo-spring-boot-devOps-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 9898

# Command to run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
