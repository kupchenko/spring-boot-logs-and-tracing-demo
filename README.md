# Getting Started

### Running application with profiles:

* VM options in IntelliJ IDEA: 
```
-Dspring.profiles.active=prod
```

* Running with gradlew args:
```
./gradlew bootRun --args='--spring.profiles.active=prod'
```

* Build docker image:
```
docker build -f src/main/docker/Dockerfile .
```

* Tag image:
```
docker tag [IMAGE_ID] dkupc/spring-boot-logs-and-tracing:1.0
```

* Run docker image:
```
docker run -p 8080:8080 -d [IMAGE_ID]
```