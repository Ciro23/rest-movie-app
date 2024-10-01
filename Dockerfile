# Use Tomcat 10.1 base image
FROM tomcat:10.1-jdk21

# Copy the WAR file into the webapps directory
COPY target/rest-movie-app-1.0.war /usr/local/tomcat/webapps/

# Expose the port Tomcat runs on
EXPOSE 8080

