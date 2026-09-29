#
# Build stage
#
FROM maven:3-eclipse-temurin-26 AS build
WORKDIR /app
COPY . ./
RUN --mount=type=cache,target=/root/.m2/repository mvn -T1C -DskipTests clean package

FROM eclipse-temurin:26-jre
#RUN apt -y update
#RUN apt -y upgrade
#RUN yum install -y sqlite3
WORKDIR /app
EXPOSE 8000
COPY --from=build /app/java-playground/target/java-playground-1.0-SNAPSHOT.jar app.jar
ENV spring_profiles_active=embedded
ENV db_root=/app
ENV jasypt.encryptor.password=helloworld
ENTRYPOINT ["java","-Xmx2G",\
    "--enable-preview",\
    "-XX:+UnlockExperimentalVMOptions","-XX:+UseZGC",\
    "-jar","/app/app.jar"]
    #"-javaagent:/skywalking/agent/skywalking-agent.jar",\
