# Use the official OpenJDK image as a base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the local target directory to the container's /app directory
COPY target/imgservice.jar /app/imgservice.jar

# Expose port 8080 to the outside world (or the port your application uses)
EXPOSE 8080

# Define the command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/imgservice.jar"]
