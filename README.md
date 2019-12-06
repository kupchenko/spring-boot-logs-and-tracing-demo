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