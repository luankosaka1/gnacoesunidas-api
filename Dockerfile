FROM eclipse-temurin:17.0.3_7-jdk-alpine
WORKDIR /app
COPY target/gnacoesunidas-0.0.1.jar deploy_0.0.1.jar
EXPOSE 8080
CMD ["java", "-jar", "deploy_0.0.1.jar"]