FROM openjdk:11
ADD /build/libs/service-*.jar /app/service.jar
WORKDIR /app/
ENTRYPOINT ["java", "-Xmx512m", "-Dspring.profiles.active=prod","-jar","service.jar"]
EXPOSE 8080