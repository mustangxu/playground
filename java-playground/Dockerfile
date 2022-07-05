#
# Build stage
#
FROM maven:3.8-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#FROM apache/skywalking-base
FROM bellsoft/liberica-openjre-alpine-musl:11
WORKDIR /app
EXPOSE 8000
COPY --from=build /home/app/target/java-playground-1.0.jar app.jar
ADD docker/skywalking /skywalking
VOLUME [ "/skywalking" ]
ENTRYPOINT ["java","-Xms1G","-Xmx2G","--enable-preview",\
    "-XX:+UnlockExperimentalVMOptions","-XX:+UseZGC",\
    "-javaagent:/skywalking/agent/skywalking-agent.jar","-jar","/app/app.jar"]
