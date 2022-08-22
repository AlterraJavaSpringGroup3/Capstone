### DEV
#### Login
``` docker
docker login
```

#### Create Docker File
``` docker
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

#### Build Maven
``` Maven
mvnw.cmd clean package
```

#### Run Test Maven
``` Maven
java -jar target/service-registry-0.0.1-SNAPSHOT.jar
```


#### Delete Docker Image
``` docker
 docker rm service-registry
```

#### Create Docker Image
``` docker
 docker build -t service-registry .
```

#### Docker Tag
``` docker
 docker tag service-registry:latest mashumabduljabbar/service-registry:latest
```

#### Push Docker Registry
``` docker
 docker push mashumabduljabbar/service-registry:latest
```

### PROD
#### Login
``` docker
docker login
```

#### Pull Docker Registry
``` docker
docker pull mashumabduljabbar/service-registry:latest
```

#### Run Image with Daemon
``` docker
docker run -d -p 8761:8761 mashumabduljabbar/service-registry
```