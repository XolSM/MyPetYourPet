FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/backend.jar backend.jar

RUN mkdir -p uploads
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend.jar"]