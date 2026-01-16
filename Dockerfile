# build
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# run
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
RUN mkdir -p /data
ENV SPRING_DATASOURCE_URL=jdbc:sqlite:/data/smartcity.db
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
