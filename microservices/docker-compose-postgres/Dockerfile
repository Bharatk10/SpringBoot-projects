FROM openjdk:17-jdk-slim


ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
 
# Expose the application port
EXPOSE 8090
 
# Run the App
ENTRYPOINT ["java", "-jar", "/app.jar"]