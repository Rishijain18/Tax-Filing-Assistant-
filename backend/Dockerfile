FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /workspace

# Copy source and build the application
COPY pom.xml ./
COPY src ./src
RUN mvn -B -DskipTests clean package

FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the packaged jar from the builder stage and rename it explicitly
COPY --from=builder /workspace/target/tax-filing-assistant-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

# Add Spring Boot runtime configuration with sane defaults
ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=${PORT:8080}
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Add health check
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:${SERVER_PORT}/actuator/health || exit 1

# Use shell form to allow environment variable substitution
ENTRYPOINT java $JAVA_OPTS \
           -Dserver.port=${SERVER_PORT} \
           -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} \
           -jar app.jar