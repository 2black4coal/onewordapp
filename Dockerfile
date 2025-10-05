# Use official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and project files
COPY . .

# Grant execute permission to mvnw
RUN chmod +x mvnw

# Build the JAR file without running tests
RUN ./mvnw clean package -DskipTests

# Expose the port your Spring Boot app runs on
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "target/onewordapp-0.0.1-SNAPSHOT.jar"]
