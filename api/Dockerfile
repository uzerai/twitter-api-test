FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13-alpine-slim

LABEL author="edvbot@gmail.com"
LABEL purpose="Simple dockerfile to start a given twitter-api.jar java application."

COPY target/twitter-api-*.jar twitter-api.jar

EXPOSE 8080
CMD java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar twitter-api.jar