FROM postgres:15-alpine

# Copia el script de inicialización
COPY init.sql /docker-entrypoint-initdb.d/

# Exponer el puerto de PostgreSQL
EXPOSE 5432

