#FROM apache/skywalking-base
FROM openjdk:11
EXPOSE 8000
ARG JAR_FILE=target/playground-1.0.jar
ADD ${JAR_FILE} app.jar
ADD docker/agent /skywalking
ENTRYPOINT ["java","-jar","--enable-preview","-javaagent:/skywalking/skywalking-agent.jar","/app.jar"]
