FROM openjdk:17

ADD ./target/links-shortener-generator-*.jar /app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]