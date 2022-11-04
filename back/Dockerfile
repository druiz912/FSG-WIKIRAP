FROM openjdk:18
EXPOSE 8082
COPY /target/*.jar /usr/local/lib/wikirap-v1.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/wikirap-v1.jar"]