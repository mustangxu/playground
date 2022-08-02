#
# Build stage
#
FROM maven:3.8-openjdk-18-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
COPY docker/parent-pom.xml /home/pom.xml
RUN mvn -f /home/app/pom.xml -DskipTests package

#FROM apache/skywalking-base
FROM bellsoft/liberica-openjre-alpine-musl:17
WORKDIR /app
EXPOSE 8000
COPY --from=build /home/app/target/java-playground-1.0-SNAPSHOT.jar app.jar
ADD docker/skywalking /skywalking
VOLUME [ "/skywalking" ]
ENTRYPOINT ["java","-Xmx1G","--enable-preview",\
    "-XX:+UnlockExperimentalVMOptions","-XX:+UseZGC",\
    "-javaagent:/skywalking/agent/skywalking-agent.jar","-jar","/app/app.jar"]
