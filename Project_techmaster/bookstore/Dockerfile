FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
COPY . .
EXPOSE 8801
ADD   /target/bookstore-0.0.1-SNAPSHOT.jar bookstore-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/bookstore-0.0.1-SNAPSHOT.jar"]
