FROM eclipse-temurin:17-jdk as build
WORKDIR /workspace/app

# Copiar arquivos Maven e estrutura
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Baixar dependências primeiro (para melhor uso do cache em builds futuros)
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline -B

# Copiar código-fonte
COPY src src

# Gerar o jar
RUN ./mvnw package -DskipTests

# Estágio de runtime
FROM eclipse-temurin:17-jre
WORKDIR /app

# Instalar pacotes necessários
RUN apt-get update && \
    apt-get install -y postgresql-client dnsutils curl iputils-ping netcat-openbsd traceroute && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copiar o jar do estágio de build
COPY --from=build /workspace/app/target/*.jar app.jar

# Criar script de entrypoint diretamente no Dockerfile
RUN echo '#!/bin/sh' > /entrypoint.sh && \
    echo 'set -e' >> /entrypoint.sh && \
    echo '' >> /entrypoint.sh && \
    echo 'echo "Verificando conexão com o RabbitMQ..."' >> /entrypoint.sh && \
    echo 'until nc -z rabbitmq 5672; do' >> /entrypoint.sh && \
    echo '  echo "RabbitMQ ainda não está disponível - aguardando"' >> /entrypoint.sh && \
    echo '  sleep 2' >> /entrypoint.sh && \
    echo 'done' >> /entrypoint.sh && \
    echo 'echo "RabbitMQ está disponível"' >> /entrypoint.sh && \
    echo '' >> /entrypoint.sh && \
    echo 'echo "Iniciando a aplicacao Spring Boot..."' >> /entrypoint.sh && \
    echo 'exec java -jar /app/app.jar' >> /entrypoint.sh && \
    chmod +x /entrypoint.sh

# Expor a porta usada pela aplicação
EXPOSE 8080

# Iniciar a aplicação com perfil docker
ENTRYPOINT ["/entrypoint.sh"]
