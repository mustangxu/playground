#
# Build stage
#
FROM maven:3.9-eclipse-temurin-23 AS build
COPY src /home/app/src
COPY pom.xml /home/app
COPY docker/parent-pom.xml /home/pom.xml
RUN mvn -f /home/app/pom.xml -T1C -DskipTests clean package

#FROM apache/skywalking-java-agent:9.2.0-java21
FROM bellsoft/liberica-openjdk-debian:24
RUN apt -y update
RUN apt -y upgrade
RUN apt install -y sqlite3
WORKDIR /app
EXPOSE 8000
COPY --from=build /home/app/target/java-playground-1.0-SNAPSHOT.jar app.jar
ENV spring_profiles_active=embedded
ENV db_root=/app
ENV jasypt.encryptor.password=helloworld
ENTRYPOINT ["java","-Xmx2G",\
    "--enable-preview",\
    "-XX:+UnlockExperimentalVMOptions","-XX:+UseZGC",\
    #"-javaagent:/skywalking/agent/skywalking-agent.jar",\
    "-jar","/app/app.jar"]
