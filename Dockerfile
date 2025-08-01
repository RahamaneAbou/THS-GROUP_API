# Étape 1 : construire le projet avec Gradle
FROM gradle:7.6-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle build --no-daemon || true

# Étape 2 : exécuter le jar
FROM eclipse-temurin:17
EXPOSE 8080
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]à-    