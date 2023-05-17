FROM openjdk:21-jdk-oraclelinux7
WORKDIR /app
COPY build/libs/starwars-api-1.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
