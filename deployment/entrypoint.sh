#!/bin/bash
set -e

# Start PostgreSQL server
service postgresql start

export PGPASSWORD=rahul

until pg_isready -U postgres; do
  sleep 1
done

# Create user rahul with password 'rahul' if it does not exist
su - postgres -c "psql -tc \"SELECT 1 FROM pg_roles WHERE rolname='rahul'\" | grep -q 1 || psql -c \"CREATE USER rahul WITH PASSWORD 'rahul';\""
su - postgres -c "psql -c \"ALTER USER rahul WITH SUPERUSER;\""

# Create database if not exists
su - postgres -c "psql -tc \"SELECT 1 FROM pg_database WHERE datname = 'bon'\" | grep -q 1 || psql -c \"CREATE DATABASE bon;\""

# Load schema and data
su - postgres -c "psql -d bon -f /docker-entrypoint-initdb.d/schema.sql"
su - postgres -c "psql -d bon -f /docker-entrypoint-initdb.d/data_dump.sql"

# Start Spring Boot application
exec java -jar /app/app.jar
