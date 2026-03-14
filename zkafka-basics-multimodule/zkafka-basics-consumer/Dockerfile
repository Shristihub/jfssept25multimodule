FROM eclipse-temurin:21-jre-alpine
COPY /target/*.jar consumer.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "consumer.jar"]