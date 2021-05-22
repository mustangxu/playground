#FROM apache/skywalking-base
FROM openjdk:11
EXPOSE 8000
ARG JAR_FILE=target/playground-1.0.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","--enable-preview","/app.jar"]
