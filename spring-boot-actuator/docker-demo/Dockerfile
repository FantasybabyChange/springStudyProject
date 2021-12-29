FROM java:8
EXPOSE 8080
ARG JAR_FILE
ADD target/${JAR_FILE} /docker-demo.jar
ENTRYPOINT ["java", "-jar","/docker-demo.jar"]