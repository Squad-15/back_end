# Instruções para uso do Docker

Este documento contém as instruções para executar o projeto JotaNunes usando Docker.

## Pré-requisitos

- Docker
- Docker Compose
- Acesso ao banco de dados PostgreSQL no Supabase (configurado externamente)
- Verificar que a porta 8081 está disponível no host (a aplicação será mapeada para esta porta)

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

> **Nota**: Se estas variáveis não forem definidas, o sistema iniciará no modo de desenvolvimento usando credenciais fictícias para o Cloudinary.

## Construindo e iniciando os contêineres

Para construir e iniciar os contêineres, execute:

```bash
docker-compose up --build
```

Para executar em segundo plano:

```bash
docker-compose up -d --build
```

## Acessando a aplicação

Após a inicialização dos contêineres, a aplicação estará disponível em:

```
http://localhost:8081
```

> **Nota:** A porta foi alterada de 8080 para 8081 no host para evitar conflitos com outros serviços que possam estar usando a porta 8080.

## Solução de problemas de conectividade

Se enfrentar problemas de conexão com o banco de dados Supabase:

1. Certifique-se de que seu firewall permita conexões saintes para bancos de dados remotos
2. Verifique a resolução DNS dentro do contêiner
3. O script de entrada foi configurado para verificar a conectividade antes de iniciar a aplicação

## Parando os contêineres

Para parar os contêineres:

```bash
docker-compose down
```

## Verificando logs

Para verificar os logs:

```bash
# Todos os serviços
docker-compose logs

# Serviço específico (app ou rabbitmq)
docker-compose logs app
docker-compose logs rabbitmq
```

## Acessando a aplicação

A aplicação estará disponível em:

- API: http://localhost:8080
- Interface de gerenciamento do RabbitMQ: http://localhost:15672 (usuário: guest, senha: guest)

## Verificando o status dos serviços

### Verificar status dos contêineres

```bash
docker ps
```

### Banco de Dados PostgreSQL

O banco de dados está hospedado no Supabase e não em um contêiner Docker local.
A conexão com o Supabase já está configurada no arquivo `application.properties`.

### Testar endpoints da API REST

```bash
# PowerShell
Invoke-WebRequest -Uri "http://localhost:8080/metadata/departaments" -Method GET

# Linux/macOS
curl http://localhost:8080/metadata/departaments
```
