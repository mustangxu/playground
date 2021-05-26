#FROM apache/skywalking-base
FROM adoptopenjdk/openjdk16-openj9:alpine-jre
EXPOSE 8000
ARG DOCKER_PATH=java-playground
ARG JAR_FILE=java-playground-1.0.jar
ADD ${DOCKER_PATH}/target/${JAR_FILE} app.jar
ADD ${DOCKER_PATH}/docker/agent /skywalking
ENTRYPOINT ["java","-XX:+UseZGC","-Xms1G","-Xmx1G","--enable-preview",\
    "-javaagent:/skywalking/skywalking-agent.jar","-jar","/app.jar"]
