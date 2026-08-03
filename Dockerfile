# -------- Base Image --------
FROM eclipse-temurin:11-jre

# -------- Metadata (optional but recommended) --------
LABEL application="spring-boot-docker"

# -------- App Directory --------
WORKDIR /app

# -------- Security: Non-root user --------
RUN groupadd -r appgroup && \
    useradd -r -g appgroup appuser

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

# -------- Run Application --------
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar spring-boot-docker.jar"]