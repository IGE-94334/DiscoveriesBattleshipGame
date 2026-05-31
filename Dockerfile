# Imagem de produção para o Discoveries Battleship Game
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia o jar gerado pelo mvn package
COPY target/Battleship-1.0-SNAPSHOT.jar app.jar

# Jogo de consola interativo — usar 'docker run -it' para jogar
ENTRYPOINT ["java", "-jar", "app.jar"]
