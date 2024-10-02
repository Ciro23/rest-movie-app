# First stage: Build the project using Maven
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

# Second stage: Run the application with Tomcat
FROM tomcat:10.1-jdk21

COPY postgresql-42.7.4.jar /usr/local/tomcat/lib/
COPY context.xml /usr/local/tomcat/conf/context.xml

COPY startup.sh /usr/local/tomcat/bin/startup.sh
RUN chmod +x /usr/local/tomcat/bin/startup.sh

COPY --from=build /app/target/rest-movie-app-1.0.war /usr/local/tomcat/webapps/
EXPOSE 8080

CMD ["/usr/local/tomcat/bin/startup.sh"]