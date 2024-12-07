# Base image with Java 21
FROM openjdk:21-jdk-slim as builder

# Set working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and project files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download Maven dependencies
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src src

# Build the application
RUN ./mvnw package -DskipTests

# Create a minimal image for the final application
FROM openjdk:21-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the application JAR file from the builder
COPY --from=builder /app/target/users-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the service port
EXPOSE 8081

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
