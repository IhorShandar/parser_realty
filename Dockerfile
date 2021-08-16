FROM openjdk:11-jre-slim
EXPOSE 4444
ARG JAR_FILE=target/myApp.jar
COPY ${JAR_FILE} myApp.jar
ENTRYPOINT ["java", "-Djava.security.agd=file:/dev/./urandom","-jar", "/myApp.jar"]
