#!/bin/sh
set -e

echo "Verificando conexão com o RabbitMQ..."
until nc -z rabbitmq 5672; do
  echo "RabbitMQ ainda não está disponível - aguardando"
  sleep 2
done
echo "RabbitMQ está disponível"

# Verificar conexão com o PostgreSQL (Supabase)
echo "Verificando conexão com o banco de dados PostgreSQL (Supabase)..."
MAX_RETRIES=30
RETRY_INTERVAL=5
COUNTER=0

# Verificar resolução de DNS
echo "Verificando resolução de DNS para o host do banco..."
nslookup aws-0-sa-east-1.pooler.supabase.com || echo "Não foi possível resolver o DNS"

# Verificar conectividade de rede
echo "Verificando conectividade de rede..."
ping -c 3 aws-0-sa-east-1.pooler.supabase.com || echo "Ping falhou, mas isso pode ser normal"

echo "Tentando conexão com o banco de dados... Isso pode levar algum tempo."
until PGPASSWORD=0uxfUlLPKfYYx15b psql -h aws-0-sa-east-1.pooler.supabase.com -p 6543 -U postgres.wrkrpvpcftsievtpkezf -c '\q' > /dev/null 2>&1 || [ $COUNTER -eq $MAX_RETRIES ]; do
    COUNTER=$((COUNTER + 1))
    echo "Aguardando que o banco de dados fique disponível... $COUNTER/$MAX_RETRIES"
    
    # Diagnóstico adicional
    if [ $((COUNTER % 5)) -eq 0 ]; then
        echo "Tentando telnet para verificar conectividade da porta..."
        nc -zv aws-0-sa-east-1.pooler.supabase.com 6543 || echo "Não foi possível conectar via netcat"
        echo "Verificando rota de rede para o host..."
        traceroute aws-0-sa-east-1.pooler.supabase.com || echo "Traceroute não disponível"
    fi
    
    sleep $RETRY_INTERVAL
done

if [ $COUNTER -eq $MAX_RETRIES ]; then
    echo "Falha ao conectar com o banco de dados após várias tentativas."
    echo "Tentando iniciar a aplicação mesmo assim..."
else
    echo "Conexão com o banco de dados estabelecida com sucesso!"
fi

echo "Iniciando a aplicacao Spring Boot..."
exec java -jar /app/app.jar
