FROM openjdk:8
EXPOSE 8080
ADD target/producer-service.jar producer-service.jar
ENTRYPOINT ["java","-jar","/producer-service.jar"]