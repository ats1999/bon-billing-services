# Use official OpenJDK 21 image
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Copy built jar from ../web-service (relative to Dockerfile location)
COPY ./web-service/target/web-service-0.0.1-SNAPSHOT.jar app.jar

# Install PostgreSQL client tools
RUN apt-get update && apt-get install -y postgresql postgresql-client && rm -rf /var/lib/apt/lists/*

# Copy schema and data files
COPY ./deployment/schema.sql /docker-entrypoint-initdb.d/schema.sql
COPY ./deployment/data_dump.sql /docker-entrypoint-initdb.d/data_dump.sql
COPY ./data /docker-entrypoint-initdb.d/data

# Add entrypoint script for DB initialization and app start
COPY ./deployment/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Expose default Spring Boot port
EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]