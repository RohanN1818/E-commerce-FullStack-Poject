# Stage 1: Build stage using Maven + Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Package application into a runnable JAR file
RUN mvn clean package -DskipTests

# Stage 2: Run stage using Java 21 JRE
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy built JAR file from Stage 1
COPY --from=build /app/target/*.jar app.jar

# Expose backend port
EXPOSE 8080

# Run Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]