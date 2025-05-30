# Instruções para uso do Docker

Este documento contém as instruções para executar o projeto JotaNunes usando Docker.

## Pré-requisitos

- Docker
- Docker Compose

## Configuração de variáveis de ambiente

Para as configurações do Cloudinary, defina as seguintes variáveis de ambiente antes de iniciar os contêineres:

```bash
# Windows (PowerShell)
$env:CLOUDINARY_CLOUD_NAME="seu-cloud-name"
$env:CLOUDINARY_API_KEY="sua-api-key"
$env:CLOUDINARY_API_SECRET="seu-api-secret"

# Linux/macOS
export CLOUDINARY_CLOUD_NAME="seu-cloud-name"
export CLOUDINARY_API_KEY="sua-api-key"
export CLOUDINARY_API_SECRET="seu-api-secret"
```

## Construindo e iniciando os contêineres

Para construir e iniciar os contêineres, execute:

```bash
docker-compose up --build
```

Para executar em segundo plano:

```bash
docker-compose up -d --build
```

## Parando os contêineres

Para parar os contêineres:

```bash
docker-compose down
```

Para parar e remover volumes (isso irá apagar os dados do banco de dados):

```bash
docker-compose down -v
```

## Verificando logs

Para verificar os logs:

```bash
# Todos os serviços
docker-compose logs

# Serviço específico (app, postgres ou rabbitmq)
docker-compose logs app
```

## Acessando a aplicação

A aplicação estará disponível em:

- API: http://localhost:8080
- Interface de gerenciamento do RabbitMQ: http://localhost:15672 (usuário: guest, senha: guest)
