# Use a imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Instalação das dependências necessárias
RUN apt-get update && \
    apt-get install -y tesseract-ocr tesseract-ocr-por libtesseract-dev && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Definir a variável TESSDATA_PREFIX para o diretório do Tesseract
ENV TESSDATA_PREFIX=/usr/share/tesseract-ocr/4.00/tessdata/

# Diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR gerado para o contêiner
COPY target/extrator-pdf-1.0-SNAPSHOT.jar /app/app.jar

# Comando de entrada para executar o aplicativo
ENTRYPOINT ["java", "-jar", "/app/app.jar"]