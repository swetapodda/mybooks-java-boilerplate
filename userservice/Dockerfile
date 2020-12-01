FROM java:8-jre
WORKDIR /usr/src
ENV  MYSQL_DATABASENAME=testnotes
ENV  MYSQL_URL=jdbc:mysql://localhost:3306/testnotes?verifyServerCertificate=false&useSSL=false&requireSSL=false&allowPublicKeyRetrieval=true
ENV  MYSQL_USERNAME=db2admin
ENV  MYSQL_PASSWORD=db2admin
EXPOSE 9300
ADD ./target/userservice-1.0.jar /usr/src/userservice-1.0.jar
ENTRYPOINT ["java", "-jar", "userservice-1.0.jar"]