FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER apps.saranganrajan.com
COPY target/core-domain-extractor-0.0.1-SNAPSHOT.jar core-domain-extractor.jar
ENTRYPOINT ["java","-jar","/core-domain-extractor.jar"]