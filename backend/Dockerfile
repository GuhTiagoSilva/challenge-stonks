FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/challenge-stonks-0.0.1-SNAPSHOT.jar stonks.jar
ENTRYPOINT ["java","-jar","/stonks.jar"]