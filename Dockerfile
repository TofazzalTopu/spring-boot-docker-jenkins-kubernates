# -------- Base Image --------
FROM eclipse-temurin:11-jre

# -------- Metadata (optional but recommended) --------
LABEL application="spring-boot-docker"

# -------- App Directory --------
WORKDIR /app

RUN apt-get update && \
    apt-get install -y wget && \
    rm -rf /var/lib/apt/lists/* \

# -------- Security: Non-root user --------
RUN groupadd -r appgroup && \
    useradd -r -g appgroup appuser && \
    mkdir -p /app/log && \
    chown -R appuser:appgroup /app

# -------- Copy Artifact --------
COPY target/spring-boot-docker.jar spring-boot-docker.jar

# -------- Set Ownership --------
RUN chown appuser:appgroup spring-boot-docker.jar

# -------- Switch User --------
USER appuser

# -------- Expose Port --------
EXPOSE 8081

# -------- JVM Options --------
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# -------- Health Check --------
HEALTHCHECK --interval=30s --timeout=5s --start-period=20s --retries=3 \
  CMD wget -qO- http://localhost:8081/actuator/health || exit 1

# -------- Run Application --------
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar spring-boot-docker.jar"]