FROM openjdk:21-jdk

COPY target/IS_Projekt.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]