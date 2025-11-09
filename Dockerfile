#FROM openjdk:25-ea-1-jdk-slim-bullseye
#ARG JAR_FILE=target/*.jar
##WORKDIR /app
##COPY target/myPetYourPet-0.0.1-SNAPSHOT.jar backend.jar
#COPY ${JAR_FILE} app.jar
#RUN mkdir -p uploads
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM openjdk:25-ea-1-jdk-slim
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
ARG JAR_FILE=target/myPetYourPet-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
WORKDIR /app
RUN mkdir -p uploads
VOLUME /app/uploads
EXPOSE 8082
#ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]

#Automatic
# 🧩 Stage 1: Build the JAR
#FROM maven:3.9.4-eclipse-temurin-21 AS builder
#
#WORKDIR /build
#
## Copy your Maven project files
#COPY pom.xml .
#COPY src ./src
#
## Build the JAR (skip tests if needed)
#RUN mvn clean package -DskipTests
#
## 🧩 Stage 2: Run the JAR
#FROM openjdk:25-ea-1-jdk-slim
#
#WORKDIR /app
#
## Copy the JAR from the builder stage
#COPY --from=builder /build/target/*.jar app.jar
#
## Create uploads directory and expose volume
#RUN mkdir -p uploads
#VOLUME /app/uploads
#
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]
