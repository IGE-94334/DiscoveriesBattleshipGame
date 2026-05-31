FROM eclipse-temurin:17-jdk-jammy

RUN apt-get update && apt-get install -y \
    maven \
    git \
    curl \
    wget \
    unzip \
    && rm -rf /var/lib/apt/lists/*

RUN mvn --version && java -version && git --version

WORKDIR /workspace
