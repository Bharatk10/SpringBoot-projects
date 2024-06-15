FROM openjdk:17-jdk-slim

MAINTAINER zettamine.com

COPY target/docker-accounts.jar docker-accounts.jar

ENTRYPOINT [ "java","-jar","docker-accounts.jar" ]