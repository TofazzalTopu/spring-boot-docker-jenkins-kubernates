FROM jenkins/jenkins:2.516.3-lts-jdk17

USER root

RUN apt-get update && apt-get install -y \
    docker.io \
    curl \
    git \
    wget \
    unzip

# Install Maven
RUN apt-get install -y maven

# Install Helm
RUN curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash

# Install kubectl
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" && \
    install -m 0755 kubectl /usr/local/bin/kubectl && \
    rm kubectl

USER jenkins