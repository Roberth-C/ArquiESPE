-- CREATE DATABASE libreria;



-- Verifica si la base de datos "libreria" no existe antes de crearla
SELECT 'CREATE DATABASE libreria'
WHERE NOT EXISTS (
    SELECT FROM pg_database WHERE datname = 'libreria'
)\gexec
