FROM postgres:latest

ENV POSTGRES_DB=libreria
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=admin1234

COPY init.sql /docker-entrypoint-initdb.d/

EXPOSE 5432
