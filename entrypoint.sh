#!/bin/sh
# Aguarda o PostgreSQL estar disponível
echo "Aguardando serviços dependentes..."
sleep 15
echo "Iniciando a aplicação..."
exec java -jar /app/app.jar
