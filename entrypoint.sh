#!/bin/sh

echo "Aguardando o RabbitMQ iniciar..."
sleep 10

# Verificar conexão com o PostgreSQL
echo "Verificando conexão com o banco de dados PostgreSQL (Supabase)..."
MAX_RETRIES=30
RETRY_INTERVAL=5
COUNTER=0

# As ferramentas já estão instaladas no Dockerfile, então não precisamos instalar novamente aqui

# Verificar resolução de DNS
echo "Verificando resolução de DNS para o host do banco..."
nslookup aws-0-sa-east-1.pooler.supabase.com || true
host aws-0-sa-east-1.pooler.supabase.com || true

# Verificar conectividade de rede
echo "Verificando conectividade de rede..."
ping -c 3 aws-0-sa-east-1.pooler.supabase.com || true

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
