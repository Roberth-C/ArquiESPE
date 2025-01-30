FROM openjdk:21-jdk-slim
WORKDIR /app
COPY wait-for-it.sh /app/wait-for-it.sh
COPY target/user-service-0.0.1-SNAPSHOT.jar app.jar
RUN chmod +x /app/wait-for-it.sh
CMD ["/app/wait-for-it.sh", "database:5432", "--", "java", "-jar", "app.jar"]
