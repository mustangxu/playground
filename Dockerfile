FROM maven:3.8.1-adoptopenjdk-15 as build
WORKDIR /app
ADD . /app
RUN --mount=type=cache,target=/root/.m2 mvn -DskipTests clean package

FROM adoptopenjdk/openjdk15-openj9:alpine-jre
WORKDIR /app
EXPOSE 8000
COPY --from=build /app/java-playground/target/java-playground-1.0.jar app.jar
COPY java-playground/docker/agent /skywalking
ENTRYPOINT ["java","-XX:+UseZGC","-Xms1G","-Xmx1G","--enable-preview",\
    "-javaagent:/skywalking/skywalking-agent.jar","-jar","/app/app.jar"]