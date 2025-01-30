FROM openjdk:21-jdk-slim
WORKDIR /app
COPY wait-for-it.sh /app/wait-for-it.sh
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar
RUN chmod +x /app/wait-for-it.sh
CMD ["/app/wait-for-it.sh", "eureka-server:8761", "--", "java", "-jar", "app.jar"]
