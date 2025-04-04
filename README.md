# Jay's code playground

[![Codacy Security Scan](https://github.com/mustangxu/playground/actions/workflows/codacy.yml/badge.svg?branch=master)](https://github.com/mustangxu/playground/actions/workflows/codacy.yml)
[![CodeQL](https://github.com/mustangxu/playground/actions/workflows/codeql.yml/badge.svg)](https://github.com/mustangxu/playground/actions/workflows/codeql.yml)
[![Docker Image CI](https://github.com/mustangxu/playground/actions/workflows/docker-image.yml/badge.svg)](https://github.com/mustangxu/playground/actions/workflows/docker-image.yml)
[![Docker Pulls](https://img.shields.io/docker/pulls/mustangxu/playground)](https://hub.docker.com/r/mustangxu/playground)

## Usage

cd to main folder: `java-playground`, so far contains the following demos:

* JDK 21 / 22 / 23 and spring boot 3.5.x
* JDK new features:
    * native memory access
    * vector calculation
    * record
    * virtual thread
    * ~~string templates~~
    * unnamed variables
    * scoped value
* eclipselink JPA
* sharding sphere
* openapi with swagger UI
* websocket with SockJS (enabled if `jakarta.websocket.WebSocketContainer` on classpath)
* open feign
* quartz
* easyexcel
* jmdns
* skywalking tracing, log
* maven docker build task
* eth RPC client
* a [wolfram](https://www.wolframalpha.com/) client
* spring boot client, a spring boot server docker image can be
  found [here](https://hub.docker.com/repository/docker/mustangxu/spring-boot-admin-server/general) [![Docker Pulls](https://img.shields.io/docker/pulls/mustangxu/spring-boot-admin-server)](https://hub.docker.com/r/mustangxu/spring-boot-admin-server)
* jmh performance test
* jasypt-spring integration
  using [my another project](https://github.com/mustangxu/jasypt-spring-aws-kms-starter) [![Maven Central](https://img.shields.io/maven-central/v/com.jayxu/jasypt-spring-aws-kms-starter)](https://search.maven.org/artifact/com.jayxu/jasypt-spring-aws-kms-starter)
* ~~a openai client using [my another project](https://github.com/mustangxu/openai4j)~~
  [![Maven Central](https://img.shields.io/maven-central/v/com.jayxu/openai4j)](https://search.maven.org/artifact/com.jayxu/openai4j)
* AWS lambda function demo
* zstd-jni lib
* Spring AI
    * deepseek
    * milvus vector db on zilliz
* http2
* http3 (~~enable by set `http3.enabled` property to `true`~~ under `http3` project)

run:

```shell
cd java-playground
mvn spring-boot:run -DskipTests # use mysql as datasource, or
mvn spring-boot:run -DskipTests -Dspring-boot.run.profiles=embedded # use in memory db as ds
```
