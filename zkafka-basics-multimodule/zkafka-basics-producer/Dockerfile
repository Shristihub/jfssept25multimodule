FROM eclipse-temurin:21-jre-alpine
COPY /target/*.jar producer.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "producer.jar"]