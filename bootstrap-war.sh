#!/bin/sh

mvn compile -am -P standalone -Dmaven.test.skip=true

mvn package -am -P standalone -Dmaven.test.skip=true

# sever port:8080
# remote debug port: 5005
# profile: war
java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 target/log4j2-slow.war --server.port=8080\
 --spring.profiles.active=war