#!/bin/sh

# Çalışma dizinini projenin kök dizinine çevir
cd /app

# Maven komutlarını çalıştır
./mvnw clean package

# Spring Boot uygulamasını başlatma
exec java -jar /app/target/spring_rest_docker.jar