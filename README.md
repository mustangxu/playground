# Jay's code playground

[![Codacy Security Scan](https://github.com/mustangxu/playground/actions/workflows/codacy.yml/badge.svg?branch=master)](https://github.com/mustangxu/playground/actions/workflows/codacy.yml)
[![CodeQL](https://github.com/mustangxu/playground/actions/workflows/codeql.yml/badge.svg)](https://github.com/mustangxu/playground/actions/workflows/codeql.yml)
[![Docker Image CI](https://github.com/mustangxu/playground/actions/workflows/docker-image.yml/badge.svg)](https://github.com/mustangxu/playground/actions/workflows/docker-image.yml)
[![Qodana](https://github.com/mustangxu/playground/actions/workflows/code_quality.yml/badge.svg)](https://github.com/mustangxu/playground/actions/workflows/code_quality.yml)

# Usage
cd to main folder: `java-playground`, so far contains the following demos:
* eclipselink JPA
* sharding sphere
* openapi with swagger UI
* websocket
* open feign
* quartz
* easyexcel
* jmdns
* skywalking tracing, log
* maven docker build task
* eth RPC
* spring boot client, a spring boot server docker image is [here](https://hub.docker.com/repository/docker/mustangxu/spring-boot-admin-server/general)

run:
```shell
cd java-playground
mvn spring-boot:run -DskipTests # use mysql as datasource, or
mvn spring-boot:run -DskipTests -Dspring-boot.run.profiles=h2 # use h2 in memory db as ds
```
