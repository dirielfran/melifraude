FROM openjdk:8-jdk-slim
COPY "./target/Geolocalizacion-0.0.1-SNAPSHOT.jar" "appmeli.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","appmeli.jar"]