# Стадия 1: Сборка JAR (Gradle)
FROM gradle:8.4-jdk17-alpine AS builder

WORKDIR /app

COPY build.gradle settings.gradle /app/
COPY src /app/src

RUN gradle clean build --no-daemon -x test

# Стадия 2: Запуск JAR (минимальный образ)
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]