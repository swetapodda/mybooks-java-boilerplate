FROM java:8-jre
WORKDIR usr/src
EXPOSE 8761
ADD ./target/netflix-eureka-naming-server-1.0.jar /usr/src/netflix-eureka-naming-server-1.0.jar
ENTRYPOINT ["java", "-jar", "netflix-eureka-naming-server-1.0.jar"]