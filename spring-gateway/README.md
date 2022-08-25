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
java -jar target\spring-gateway-0.0.1-SNAPSHOT.jar
```


#### Delete Docker Image
``` docker
 docker rm capstone-spring-gateway
```

#### Create Docker Image
``` docker
 docker build -t capstone-spring-gateway .
```

#### Docker Tag
``` docker
 docker tag capstone-spring-gateway:latest mashumabduljabbar/capstone-spring-gateway:latest
```

#### Push Docker Registry
``` docker
 docker push mashumabduljabbar/capstone-spring-gateway:latest
```

### PROD
#### Login
``` docker
docker login
```

#### Pull Docker Registry
``` docker
docker pull mashumabduljabbar/capstone-spring-gateway:latest
```

#### Run Image with Daemon
``` docker
docker run -d -p 8080:7171 mashumabduljabbar/capstone-spring-gateway
```