FROM maven:3.9.8-eclipse-temurin-21

WORKDIR /app

COPY . .

CMD ["mvn", "mybatis-generator:generate", "-Dmybatis.generator.overwrite=true"]
