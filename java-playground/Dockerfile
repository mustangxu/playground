#FROM apache/skywalking-base
FROM bellsoft/liberica-openjre-alpine-musl:11
WORKDIR /app
EXPOSE 8000
ADD target/java-playground-1.0.jar app.jar
ADD docker/skywalking /skywalking
VOLUME [ "/skywalking" ]
ENTRYPOINT ["java","-Xms1G","-Xmx2G","--enable-preview",\
    "-XX:+UnlockExperimentalVMOptions","-XX:+UseZGC",\
    "-javaagent:/skywalking/agent/skywalking-agent.jar","-jar","/app/app.jar"]
