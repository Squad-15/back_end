# 🚀 Plataforma de Treinamento Interno — Backend

Este repositório contém o **backend** da plataforma de treinamento interno da empresa **Jota Nunes**. Ele é responsável por toda a lógica de negócio, autenticação, persistência de dados e integração com o front-end da aplicação.

## 📖 Sobre o Projeto

O backend foi desenvolvido para dar suporte à plataforma de treinamentos, garantindo segurança, performance e escalabilidade.  
Através de uma API REST, é possível gerenciar usuários, módulos, trilhas de aprendizado, progresso individual e autenticação com base em tokens.

## 📌 Tecnologias Utilizadas

O projeto foi desenvolvido com as seguintes tecnologias e ferramentas:

- **[Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)** — Linguagem robusta e multiplataforma utilizada no desenvolvimento backend
- **[Spring Boot](https://spring.io/projects/spring-boot)** — Framework para construção de APIs e microserviços de forma produtiva
- **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)** — Abstração para acesso a dados com ORM (Hibernate)
- **[Hibernate](https://hibernate.org/)** — Framework ORM para persistência de dados em banco relacional
- **[Spring Security](https://spring.io/projects/spring-security)** — Gerenciamento de autenticação, autorização e segurança
- **[Spring Boot Starter Validation](https://spring.io/guides/gs/validating-form-input/)** — Validação de dados via anotações (JSR-380, Bean Validation)
- **[Spring Boot Starter Mail](https://spring.io/guides/gs/sending-email/)** — Envio de e-mails via SMTP configurável
- **[Spring Boot Starter AMQP](https://spring.io/projects/spring-amqp)** — Integração com RabbitMQ para mensageria
- **[PostgreSQL](https://www.postgresql.org/)** — Banco de dados relacional utilizado para persistência (em runtime)
- **[Lombok](https://projectlombok.org/)** — Redução de código boilerplate com anotações simples (getters/setters, construtores)
- **[Java JWT (com.auth0)](https://github.com/auth0/java-jwt)** — Biblioteca para criação e validação de JSON Web Tokens (JWT)
- **[SpringDoc OpenAPI](https://springdoc.org/)** — Geração automática de documentação OpenAPI (Swagger UI)
- **[Cloudinary HTTP44](https://cloudinary.com/documentation/java_integration)** — Integração para upload e gerenciamento de imagens na nuvem Cloudinary
- **[Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools)** — Ferramentas para melhorar a experiência de desenvolvimento (hot reload)
- **[Spring Dotenv](https://github.com/paulschwarz/spring-dotenv)** — Carregamento de variáveis de ambiente de arquivos `.env` para Spring Boot


## 📁 Estrutura do Projeto

```bash
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── jota_nunes/
│   │   │           ├── controller/        # Controladores REST (endpoints)
│   │   │           ├── service/           # Camada de serviço com regras de negócio
│   │   │           ├── model/             # Entidades JPA (tabelas do banco)
│   │   │           ├── repository/        # Interfaces de acesso ao banco (Spring Data JPA)
│   │   │           ├── security/          # Configuração de autenticação e autorização
│   │   │           └── dto/               # Data Transfer Objects (entrada e saída da API)
│   │   └── resources/
│   │       ├── application.properties     # Configurações da aplicação (DB, JWT, etc.)
│   │       └── db/migration/              # Arquivos de migração do Flyway
└── pom.xml                               # Arquivo de configuração Maven
```

## 🛠️ Como Rodar o Projeto

### 1. Clone o Repositório
```bash
git clone https://github.com/Squad-15/back_end.git
```

### 2. Acesse o diretório
```bash
cd squad_15_jota_nunes_back
```

### 3. Adicione no application.properties suas credenciais do banco
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/<NOME_DO_BANCO>
spring.datasource.username=<SEU_USUARIO>
spring.datasource.password=<SUA_SENHA>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 4. Rode a aplicação
```bash
./mvnw spring-boot:run
```

### 5. A Aplicação ficará disponível em: **http://localhost:8080**

## 📦 Endpoints Principais

- **POST /auth/login — Autenticação e geração de token**
- **POST /auth/register — Criação de usuários**
- **GET /users/all — Listagem de usuários**
- **GET /path — Listagem de trilhas**
- **POST /modulo — Cadastro de módulos de treinamento**

## 👥 Autores e Colaboradores

Agradecemos a todos que contribuíram para o desenvolvimento deste projeto:

### 🎨 Prototipação
- **Adrya Kauane**  
  Responsável pela criação dos protótipos e definição da experiência do usuário (UX/UI).  

### 📚 Documentação e Modelagem de Banco
- **Austin Manoel**  
  Estruturação do README, escrita de manuais de uso, organização da documentação técnica  
  e participação na modelagem do banco de dados.

### 💻 Front-End e Back-End
- **Icaro Ryan**  
  Desenvolvimento da interface (front-end), roteamento com Next.js e desenvolvimento da lógica de negócio/API (back-end).

### 💻 Front-End
- **Marcos Guilherme**  
  Implementação de telas, estilização com Tailwind CSS e componentes em React.

### 🚀 DevOps / Deploy e Modelagem de Banco
- **Christopher Edlly**  
  Configuração e deploy da aplicação em ambiente de produção, além da participação na modelagem do banco de dados.

---

🙏 A todos que colaboraram direta ou indiretamente: **muito obrigado!**
