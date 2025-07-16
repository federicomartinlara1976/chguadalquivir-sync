# Usa una imagen base con Java (versión compatible con tu Spring Boot)
FROM eclipse-temurin:17-jdk-jammy

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copia el JAR construido (ajusta el nombre si usas Maven/Gradle)
COPY target/chguadalquivir-sync-prod.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]