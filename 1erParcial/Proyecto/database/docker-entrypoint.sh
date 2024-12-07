#!/bin/bash
set -e

# Ejecutar el entrypoint original de PostgreSQL
exec docker-entrypoint.sh "$@"

chmod +x docker-entrypoint.sh
