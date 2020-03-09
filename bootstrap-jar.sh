#!/bin/sh

mvn compile -am -P standalone -Dmaven.test.skip=true

mvn package -am -P standalone -Dmaven.test.skip=true

# sever port:8081
# remote debug port: 5006
# profile: war
java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5006 target/log4j2-slow.jar --server.port=8081 \
--spring.profiles.active=jar
