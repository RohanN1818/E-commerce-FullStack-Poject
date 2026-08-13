# Stage 1: Build the application using Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and source code into container
COPY pom.xml .
COPY src ./src

# Package application into a runnable JAR file
RUN mvn clean package -DskipTests

# Stage 2: Run the application using OpenJDK
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy built JAR file from Stage 1
COPY --from=build /app/target/*.jar app.jar

# Expose backend port
EXPOSE 8080

# Run Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]