FROM openjdk:11
EXPOSE 8085
ADD target/ExchangeService-0.0.1-SNAPSHOT.jar ExchangeService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ExchangeService-0.0.1-SNAPSHOT.jar"]