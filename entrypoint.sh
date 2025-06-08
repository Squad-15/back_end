#!/bin/sh
set -e

echo "Iniciando aplicação Spring Boot..."
echo "Usando CloudAMQP para RabbitMQ e Supabase para PostgreSQL"

# A aplicação irá conectar aos serviços externos conforme necessário
echo "Iniciando a aplicacao Spring Boot..."
exec java -jar /app/app.jar
