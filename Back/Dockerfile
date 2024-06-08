# Stage 1: Build the application
FROM openjdk:17-jdk-slim as builder

WORKDIR /app

# Copy Maven files
COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./

# Make Maven wrapper executable and resolve dependencies
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copy application source code
COPY src/ ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Create logs directory
RUN mkdir ./logs

# Copy the JAR file built in the previous stage
COPY --from=builder /app/target/Bybysitting-0.0.1-SNAPSHOT.jar .

# Expose port 8080
EXPOSE 8080

# Define the entry point to run the application
ENTRYPOINT ["java", "-jar", "Bybysitting-0.0.1-SNAPSHOT.jar"]