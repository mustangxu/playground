#FROM apache/skywalking-base
FROM adoptopenjdk/openjdk16-openj9:alpine-jre
EXPOSE 8000
ARG JAR_FILE=target/java-playground-1.0.jar
ADD ${JAR_FILE} app.jar
ADD docker/agent /skywalking
ENTRYPOINT ["java","-XX:+UseZGC","-jar","--enable-preview","-javaagent:/skywalking/skywalking-agent.jar","/app.jar"]
