FROM maven:3.8.6-openjdk-18 AS build
RUN mkdir -p workspace
WORKDIR workspace
COPY pom.xml /workspace
COPY src /workspace/src
COPY userB /workspace/userB
RUN mvn -f pom.xml clean install -Pprod -DskipTests=true


FROM openjdk:18.0.2.1-jdk
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar", "-Dspring.datasource.password=${MYSQLDB_ROOT_PASSWORD}", "-Dsecret_key=${SECRET_KEY}", "-Dspring.profiles.active=prod", "app.jar"]
