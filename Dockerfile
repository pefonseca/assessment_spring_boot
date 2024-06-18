# Stage 1: Build
FROM ubuntu:latest AS build

# Atualizar e instalar JDK e Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven && \
    apt-get clean

# Copiar o código-fonte para o diretório de trabalho
COPY . /app

# Definir o diretório de trabalho
WORKDIR /app

# Executar o build do Maven
RUN mvn clean install

# Verificar o conteúdo do diretório target
RUN ls -la /app/target

# Stage 2: Run
FROM openjdk:17-jdk-slim

# Definir a porta que a aplicação vai expor
EXPOSE 8080

# Copiar o JAR do estágio de build para o estágio final
COPY --from=build /app/target/*.jar app.jar

# Definir o comando de entrada para rodar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
