FROM eclipse-temurin:17-jdk
EXPOSE 8080
COPY target/sb-ecom-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]