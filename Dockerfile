# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="vinicius_ufop@yahoo.com.br"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 9000 available to the world outside this container
EXPOSE 9000

# The application's jar file
ARG JAR_FILE=target/marketplace-backend-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} marketplace-backend.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/marketplace-backend.jar"]