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
java -jar target/config-server-0.0.1-SNAPSHOT.jar
```


#### Delete Docker Image
``` docker
 docker rm capstone-config-server
```

#### Create Docker Image
``` docker
 docker build -t capstone-config-server .
```

#### Docker Tag
``` docker
 docker tag capstone-config-server:latest mashumabduljabbar/capstone-config-server:latest
```

#### Push Docker Registry
``` docker
 docker push mashumabduljabbar/capstone-config-server:latest
```

### PROD
#### Login
``` docker
docker login
```

#### Pull Docker Registry
``` docker
docker pull mashumabduljabbar/capstone-config-server:latest
```

#### Run Image with Daemon
``` docker
docker run -d -p 9269:9269 mashumabduljabbar/capstone-config-server
```