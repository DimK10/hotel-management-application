FROM maven:3.8.6-openjdk-18 AS build
RUN mkdir -p workspace
WORKDIR workspace
COPY pom.xml /workspace
COPY src /workspace/src
COPY client /workspace/client
RUN mvn -f pom.xml clean install -Pprod -DskipTests=true


FROM openjdk:18.0.2.1-jdk
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","-DSPRING_APPLICATION_JSON: '{ \"spring.datasource.url\"  : \"jdbc:mysql://localhost:$MYSQLDB_DOCKER_PORT/$MYSQLDB_DATABASE?useSSL=false\", \"spring.datasource.username\" : \"$MYSQLDB_USER\", \"spring.datasource.password\" : \"$MYSQLDB_ROOT_PASSWORD\", \"spring.jpa.properties.hibernate.dialect\" : \"org.hibernate.dialect.MySQL5InnoDBDialect\",\"spring.jpa.hibernate.ddl-auto\" : \"create-drop\",\"SECRET_KEY\": \"$SECRET_KEY\" }'","app.jar"]
