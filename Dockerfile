# В этом файле описывается как собирать контейнер

# Используем официальный образ OpenJDK для Java
FROM openjdk:17

# Указываем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем jar файл приложения в контейнер
COPY target/delete-1.0-SNAPSHOT.jar attestation.jar

# Открываем порт, на котором будет работать ваше приложение
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "attestation.jar"]