# Primeiro estágio: construir a aplicação
FROM maven:3-openjdk-17 AS build-image

WORKDIR /to-build-app

# Copia apenas o arquivo pom.xml para aproveitar o cache do Docker
COPY pom.xml .

# Instalação das dependências (offline para aproveitar o cache)
RUN mvn dependency:go-offline -B

# Copia o restante dos arquivos e constrói o pacote JAR
COPY src ./src
RUN mvn package -DskipTests

# Segundo estágio: criar a imagem final
FROM openjdk:17-alpine

WORKDIR /app

# Copia o pacote JAR da etapa anterior
COPY --from=build-image /to-build-app/target/*.jar app.jar

# Expor a porta 8080
EXPOSE 8080

# Comando de execução da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]