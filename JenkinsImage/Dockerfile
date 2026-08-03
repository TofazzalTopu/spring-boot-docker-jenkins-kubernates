FROM jenkins/jenkins:lts

USER root

# Install required packages
RUN apt-get update && apt-get install -y \
    curl \
    git \
    wget \
    unzip \
    docker.io \
    maven \
    ca-certificates \
    gnupg \
    lsb-release \
    && rm -rf /var/lib/apt/lists/*

# -----------------------------
# Install Helm
# -----------------------------
RUN curl -fsSL https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash

# -----------------------------
# Install kubectl
# -----------------------------
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/arm64/kubectl" && \
    install -m 0755 kubectl /usr/local/bin/kubectl && \
    rm kubectl

# -----------------------------
# Verify installations
# -----------------------------
RUN java -version && \
    mvn -version && \
    docker --version && \
    helm version && \
    kubectl version --client

USER jenkins
