FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy all poms
COPY pom.xml .
COPY shared-events/pom.xml                     shared-events/
COPY shipkart-api-gateway/pom.xml              shipkart-api-gateway/
COPY shipkart-config-server/pom.xml            shipkart-config-server/
COPY shipkart-productcart-service/pom.xml      shipkart-productcart-service/
COPY shipkart-productcatalog-service/pom.xml   shipkart-productcatalog-service/
COPY shipkart-productinfo-service/pom.xml      shipkart-productinfo-service/
COPY shipkart-productinventory-service/pom.xml shipkart-productinventory-service/
COPY shipkart-productorder-service/pom.xml     shipkart-productorder-service/
COPY shipkart-productpayment-service/pom.xml   shipkart-productpayment-service/

# Copy shared-events src first — needed before dependency:resolve
COPY shared-events/src shared-events/src

# Install shared-events first using its own pom directly
RUN mvn install -f shared-events/pom.xml -DskipTests

# Now resolve all other dependencies
RUN mvn dependency:resolve -B

# Copy all sources
COPY shared-events/src                     shared-events/src
COPY shipkart-api-gateway/src              shipkart-api-gateway/src
COPY shipkart-config-server/src            shipkart-config-server/src
COPY shipkart-productcart-service/src      shipkart-productcart-service/src
COPY shipkart-productcatalog-service/src   shipkart-productcatalog-service/src
COPY shipkart-productinfo-service/src      shipkart-productinfo-service/src
COPY shipkart-productinventory-service/src shipkart-productinventory-service/src
COPY shipkart-productorder-service/src     shipkart-productorder-service/src
COPY shipkart-productpayment-service/src   shipkart-productpayment-service/src

# Build all modules in one shot
RUN mvn package -DskipTests

# ─── Stage 2: One stage per service ───────────────────────────
FROM eclipse-temurin:21-jre-alpine AS config-server
WORKDIR /app
COPY --from=builder /app/shipkart-config-server/target/*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jre-alpine AS api-gateway
WORKDIR /app
COPY --from=builder /app/shipkart-api-gateway/target/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jre-alpine AS product-catalog
WORKDIR /app
COPY --from=builder /app/shipkart-productcatalog-service/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jre-alpine AS product-inventory
WORKDIR /app
COPY --from=builder /app/shipkart-productinventory-service/target/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jre-alpine AS product-info
WORKDIR /app
COPY --from=builder /app/shipkart-productinfo-service/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jre-alpine AS product-cart
WORKDIR /app
COPY --from=builder /app/shipkart-productcart-service/target/*.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jre-alpine AS product-order
WORKDIR /app
COPY --from=builder /app/shipkart-productorder-service/target/*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jre-alpine AS product-payment
WORKDIR /app
COPY --from=builder /app/shipkart-productpayment-service/target/*.jar app.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "app.jar"]