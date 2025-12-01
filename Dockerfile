FROM openjdk:25-ea-1-jdk-slim

ARG JAR_FILE=target/myPetYourPet-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
WORKDIR /app
RUN mkdir -p uploads
VOLUME /app/uploads
EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
