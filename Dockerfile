# Etapa de construção usando a imagem oficial do Maven com JDK 17
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /usr/src/app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

# Etapa de execução usando a imagem OpenJDK do Red Hat Universal Base Image (UBI)
FROM registry.access.redhat.com/ubi8/openjdk-17
COPY --from=build /usr/src/app/target/quarkus-app /deployments/
EXPOSE 8080
CMD ["java", "-jar", "/deployments/quarkus-run.jar"]
