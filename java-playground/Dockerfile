#FROM apache/skywalking-base
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
EXPOSE 8000
ADD target/java-playground-1.0.jar app.jar
ADD docker/agent /skywalking
ENTRYPOINT ["java","-XX:+UseZGC","-Xms1G","-Xmx1G","--enable-preview",\
    "-javaagent:/skywalking/skywalking-agent.jar","-jar","/app/app.jar"]
