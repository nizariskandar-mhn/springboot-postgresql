# Use a lightweight base image
FROM eclipse-temurin:17-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the application jar
COPY target/*.jar app.jar

# Set environment variables (default profile is 'development')
ENV SPRING_PROFILES_ACTIVE=dev

# Expose the application port
EXPOSE 4040

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
