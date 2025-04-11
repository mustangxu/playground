FROM maven:3.9-eclipse-temurin-23

COPY . /home/app
COPY docker/parent-pom.xml /home/pom.xml
COPY docker/settings.xml /root/.m2/
WORKDIR /home/app

EXPOSE 8080

ENTRYPOINT ["mvn", "spring-boot:run"]
