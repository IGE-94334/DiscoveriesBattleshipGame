# Usa a imagem base oficial do OpenJDK 17 (versão leve alpine)
FROM eclipse-temurin:17-jre-alpine

# Cria uma diretoria de trabalho dentro do contentor
WORKDIR /app

# Copia o ficheiro JAR compilado na pasta target para dentro da imagem
COPY target/Battleship-1.0-SNAPSHOT.jar app.jar

# Define o comando que será executado quando o contentor arrancar
ENTRYPOINT ["java", "-jar", "app.jar"]
