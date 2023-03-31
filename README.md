# Jay's code playground

[![Codacy Security Scan](https://github.com/mustangxu/playground/actions/workflows/codacy.yml/badge.svg?branch=master)](https://github.com/mustangxu/playground/actions/workflows/codacy.yml)
[![CodeQL](https://github.com/mustangxu/playground/actions/workflows/codeql.yml/badge.svg)](https://github.com/mustangxu/playground/actions/workflows/codeql.yml)
[![Docker Image CI](https://github.com/mustangxu/playground/actions/workflows/docker-image.yml/badge.svg)](https://github.com/mustangxu/playground/actions/workflows/docker-image.yml)

## Usage
cd to main folder: `java-playground`, so far contains the following demos:
* JDK 17 and spring boot 3.x
* JDK new features:
    * native memory access
    * vector calculation
    * record
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
* eth RPC client
* a [wolfram](https://www.wolframalpha.com/) client
* spring boot client, a spring boot server docker image is [here](https://hub.docker.com/repository/docker/mustangxu/spring-boot-admin-server/general)
* jmh performance test
* jasypt-spring integration using [my another project](https://github.com/mustangxu/jasypt-spring-aws-kms-starter)
* a openai client using [my another project](https://github.com/mustangxu/openai4j)

run:
```shell
cd java-playground
mvn spring-boot:run -DskipTests # use mysql as datasource, or
mvn spring-boot:run -DskipTests -Dspring-boot.run.profiles=h2 # use h2 in memory db as ds
```
