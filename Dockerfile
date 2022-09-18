FROM maven:3.8.6-eclipse-temurin-11-alpine
WORKDIR /usr/src/ProyectoJava
COPY ProyectoJava .
RUN mvn clean install -U -f pom-docker.xml
RUN sed -i 's/localhost/db/' src/main/java/py/una/bd/Bd.java
RUN cat mainClassList | xargs -i sh -c "sed -i 's/server.*</{}/' pom-docker.xml && mvn clean package -f pom-docker.xml && mv target/socket-0.0.1-SNAPSHOT-jar-with-dependencies.jar '{}'.jar"
RUN apk add iproute2