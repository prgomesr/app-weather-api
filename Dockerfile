FROM maven:3.8.5-openjdk-17-slim AS builder

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

FROM prgomesr/openjdk-17-jdk-slim-fonts

RUN useradd -ms /bin/bash -u 1000 prgomesr

USER prgomesr

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]