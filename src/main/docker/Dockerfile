FROM openjdk:11
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Xmx512m", "-Dspring.profiles.active=prod","-jar","/app.jar"]