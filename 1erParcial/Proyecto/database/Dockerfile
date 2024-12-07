# Base image for PostgreSQL
FROM postgres:15

# Set environment variables
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=admin1234

# Copy the initialization script
COPY init.sql /docker-entrypoint-initdb.d/

# Expose PostgreSQL port
EXPOSE 5432
