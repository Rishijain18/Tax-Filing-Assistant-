FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /workspace

# Copy source and build the application
# mvnw and .mvn don't exist in this folder on Render; the builder image includes Maven.
COPY pom.xml ./
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the packaged jar from the builder stage
COPY --from=builder /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]