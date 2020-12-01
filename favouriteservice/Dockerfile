FROM java:8-jre
WORKDIR usr/src
ENV MONGO_DATABASENAME=SBA_PROG
ENV MONGO_HOST=localhost
ENV MONGO_PORT=27017
EXPOSE 9400
ADD ./target/favouriteservice-1.0.jar /usr/src/favouriteservice-1.0.jar
ENTRYPOINT ["java", "-jar", "favouriteservice-1.0.jar"]