FROM maven:3.8.1-jdk-11-openj9 AS build
RUN mkdir -p workspace
WORKDIR workspace
COPY pom.xml /workspace
COPY src /workspace/src
COPY client /workspace/client
RUN mvn -f pom.xml clean install -Pprod -DskipTests=true


FROM openjdk:11-jdk
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","app.jar"]