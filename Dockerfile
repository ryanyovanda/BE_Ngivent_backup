FROM adoptopenjdk:8-jre-hotspot

# Set the working directory
WORKDIR /app

# Copy the built .jar file into the container from the target directory
COPY /path/to/your/project/target/mini_project-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8080

# Set the entry point to run the .jar file
ENTRYPOINT ["java", "-jar", "mini_project-0.0.1-SNAPSHOT.jar"]