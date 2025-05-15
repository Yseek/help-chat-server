FROM mcr.microsoft.com/devcontainers/java:21-bullseye

# Install Gradle
ENV GRADLE_VERSION=8.7

RUN curl -sSL https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle.zip && \
    unzip gradle.zip -d /opt/gradle && \
    ln -s /opt/gradle/gradle-${GRADLE_VERSION}/bin/gradle /usr/bin/gradle && \
    rm gradle.zip

# Install Node.js and npm (required for gitmoji-cli)
RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get update && \
    apt-get install -y nodejs && \
    npm install -g gitmoji-cli

# 작업 디렉토리
WORKDIR /workspace

EXPOSE 8080    