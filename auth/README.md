### DEV

#### Database 
``` sql
mysql> CREATE DATABASE `DBCapstone` /*!40100 COLLATE 'utf8mb4_general_ci' */;
USE `DBCapstone`;

mysql> CREATE USER 'organization'@'localhost' IDENTIFIED BY PASSWORD;

mysql> GRANT ALL PRIVILEGES ON DBCapstone . * TO 'organization'@'localhost';

mysql> FLUSH PRIVILEGES;
```

#### Build Maven
``` powershell
mvnw.cmd clean package
```

#### Run Test Maven
``` powershell
java -jar target\user.jar
```

#### Create Docker File
``` docker
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

#### Login
``` powershell
docker login
```

#### Delete Docker Image
``` powershell
docker rmi capstone-user
```

#### Create Docker Image
``` powershell
docker build -t capstone-user .
```

#### Docker Tag
``` powershell
docker tag capstone-user:latest mashumabduljabbar/capstone-user:latest
```

#### Push Docker Registry
``` powershell
docker push mashumabduljabbar/capstone-user:latest
```

### PROD
#### Login
``` powershell
docker login
```

#### Pull Docker Registry
``` powershell
docker pull mashumabduljabbar/capstone-user:latest
```

#### Run Image with Daemon
``` powershell
docker run -d -p 8761:8761 mashumabduljabbar/capstone-user
```