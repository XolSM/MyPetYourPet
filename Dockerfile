#FROM openjdk:25-ea-1-jdk-slim-bullseye
#ARG JAR_FILE=target/*.jar
##WORKDIR /app
##COPY target/myPetYourPet-0.0.1-SNAPSHOT.jar backend.jar
#COPY ${JAR_FILE} app.jar
#RUN mkdir -p uploads
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM openjdk:25-ea-1-jdk-slim
ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ADD ${JAR_FILE} app.jar
WORKDIR /app
#COPY target/backend.jar backend.jar
RUN mkdir -p uploads
VOLUME /app/uploads
EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]