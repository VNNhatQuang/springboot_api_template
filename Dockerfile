FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /springboot_api_template
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /springboot_api_template
COPY --from=build /springboot_api_template/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
