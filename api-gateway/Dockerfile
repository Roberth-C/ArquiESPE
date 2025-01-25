# Usar una imagen base de Java
FROM eclipse-temurin:17-jdk-alpine

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR del microservicio al contenedor
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto que utiliza el microservicio
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
