#FROM apache/skywalking-base
FROM adoptopenjdk/openjdk16-openj9:alpine-jre
WORKDIR /app
EXPOSE 8000
ARG JAR_FILE=java-playground-1.0.jar
ADD target/${JAR_FILE} app.jar
ADD docker/agent /skywalking
ENTRYPOINT ["java","-XX:+UseZGC","-Xms1G","-Xmx1G","--enable-preview",\
    "-javaagent:/skywalking/skywalking-agent.jar","-jar","/app/app.jar"]
