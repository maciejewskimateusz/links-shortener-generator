FROM maven:3.8.5-openjdk-17-slim as maven_build
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn clean package

FROM openjdk:17-slim-buster
EXPOSE 8080
COPY --from=maven_build ./target/links-shortener-generator-*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]