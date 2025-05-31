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

# Copiar entrypoint
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Copiar o jar do estágio de build
COPY --from=build /workspace/app/target/*.jar app.jar

# Expor a porta usada pela aplicação
EXPOSE 8080

# Iniciar a aplicação com perfil docker
ENTRYPOINT ["/entrypoint.sh"]
