FROM openjdk:21-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo .jar generado por Maven al contenedor
COPY target/reservation-service-*.jar reservation-service.jar

# Exponer el puerto en el que el servicio estará disponible
EXPOSE 8084

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "reservation-service.jar"]
