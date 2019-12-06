FROM openjdk:11
ADD build/service-* app.jar
ENTRYPOINT ["java", "-Xmx512m", "-Dspring.profiles.active=prod","-jar","/app.jar"]