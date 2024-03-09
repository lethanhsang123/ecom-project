FROM eclipse-temurin:17-jdk-jammy

# Set the image's working directory.
# This instructs Docker to use this path as the default location for all subsequent commands
WORKDIR /app

# copy file to working directory
# COPY .mvn/ .mvn
# COPY mvnw pom.xml ./
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/product-service.jar
ADD ./src/main/resources /app/config

EXPOSE 8082

ENTRYPOINT ["java","-jar","product-service.jar", "--spring.profiles.active=docker"]