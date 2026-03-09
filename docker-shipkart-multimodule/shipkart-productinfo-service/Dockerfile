## Stage 1: Build the JAR
#FROM maven:3.9.6-eclipse-temurin-21 AS builder
#WORKDIR /app
#COPY pom.xml .
#RUN mvn dependency:go-offline -B
#COPY src ./src
#RUN mvn clean package -DskipTests

# Stage 2: Run the JAR
FROM eclipse-temurin:21-jre-alpine
#WORKDIR /app
#COPY --from=builder /app/target/*.jar info.jar
COPY /target/*.jar info.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","info.jar"]