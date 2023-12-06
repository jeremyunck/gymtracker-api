# Use the official Maven image as a build stage
FROM maven:3.8.4-openjdk-17-slim
WORKDIR /app

# Copy the project files and download dependencies
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Copy the JAR file built in the previous stage
COPY target/gymtracker-0.0.1-SNAPSHOT.jar gymtracker.jar

# Expose the port your application will run on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "gymtracker.jar"]
