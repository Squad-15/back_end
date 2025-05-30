# Verificando o status dos serviços Docker

Este documento complementa o DOCKER.md, fornecendo instruções detalhadas para verificar se todos os serviços estão funcionando corretamente.

## Verificar status dos contêineres

```bash
docker ps
```

Você deve ver dois contêineres em execução:

- jotanunes-app (Aplicação Spring Boot)
- jotanunes-rabbitmq (Mensageria RabbitMQ)

> Nota: O banco de dados PostgreSQL está hospedado no Supabase e não em um contêiner Docker local.

## Banco de Dados PostgreSQL

O banco de dados está hospedado no Supabase e não em um contêiner Docker local.
A conexão com o banco de dados Supabase já está configurada no arquivo `application.properties`.

## Verificar o RabbitMQ

### Verificar status do RabbitMQ

```bash
docker exec jotanunes-rabbitmq rabbitmqctl status
```

### Listar filas do RabbitMQ

```bash
docker exec jotanunes-rabbitmq rabbitmqctl list_queues
```

Acesse a interface web do RabbitMQ em http://localhost:15672 (usuário: guest, senha: guest)

## Testar endpoints da API REST

### Endpoints disponíveis para teste

```bash
# PowerShell
Invoke-WebRequest -Uri "http://localhost:8080/metadata/departaments" -Method GET

# Linux/macOS
curl http://localhost:8080/metadata/departaments
```

Outros endpoints úteis para testar:

- GET /users/all - Lista todos os usu�rios
- GET /metadata/location - Lista todas as localiza��es
- GET /modulo - Lista todos os m�dulos

## Verificando logs da aplica��o

```bash
# Verificar logs da aplica��o Spring Boot
docker logs jotanunes-app

# Verificar logs espec�ficos do Cloudinary (modo de desenvolvimento)
docker logs jotanunes-app | Select-String "Cloudinary"
```

## Resolu��o de problemas

### Se um servi�o n�o iniciar corretamente

1. Verifique os logs: `docker logs [nome-do-container]`
2. Reinicie o cont�iner: `docker restart [nome-do-container]`
3. Reinicie todos os servi�os: `docker-compose down && docker-compose up -d`

### Se a aplicação não conseguir conectar ao RabbitMQ

1. Verifique se as propriedades em `application-docker.properties` estão corretas
2. Verifique se o serviço RabbitMQ está em execução: `docker ps`
3. Teste a conexão diretamente com os comandos acima

### Correções implementadas para o Supabase

1. **Instalação do pacote netcat**

   - O pacote virtual `netcat` foi substituído pelo pacote específico `netcat-openbsd`

2. **Melhorias no script de inicialização (entrypoint.sh)**

   - Removida instalação duplicada de pacotes (já instalados no Dockerfile)
   - Adicionado diagnóstico avançado de rede durante tentativas de conexão
   - Melhorado mecanismo de verificação e retry para conexão com PostgreSQL
   - Corrigido encerramento do script para garantir execução da aplicação mesmo após falhas de conexão

3. **Configuração de rede aprimorada**
   - Adicionada política de restart para garantir que o container continue tentando funcionar
   - Implementado healthcheck para monitorar a saúde da aplicação
   - Mantidas configurações de DNS e hosts estáticos para garantir resolução de nomes

### Como testar a conexão com o Supabase

1. Reconstrua os containers com:

   ```powershell
   docker-compose down
   docker-compose up -d --build
   ```

2. Verifique os logs do container para diagnosticar problemas:

   ```powershell
   docker logs jotanunes-app
   ```

3. Teste diretamente a conectividade com o banco do Supabase:
   ```powershell
   docker exec -it jotanunes-app bash -c "PGPASSWORD=0uxfUlLPKfYYx15b psql -h db.wrkrpvpcftsievtpkezf.supabase.co -U postgres -c 'SELECT version();'"
   ```

### Ferramentas de diagnóstico adicionadas

No container agora estão disponíveis as seguintes ferramentas para diagnóstico de rede:

1. `netcat-openbsd` - Verificação de portas abertas
2. `postgresql-client` - Conexão e testes diretos com PostgreSQL
3. `dnsutils` - Ferramentas para diagnóstico de DNS
4. `curl` - Requisições HTTP/HTTPS
5. `iputils-ping` - Verificação de conectividade de rede
6. `traceroute` - Diagnóstico de rota de rede
