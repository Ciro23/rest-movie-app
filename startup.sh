#!/bin/bash

# Replace placeholders in context.xml with actual environment variables
sed -i 's/\${DB_HOST}/'"$DB_HOST"'/g' /usr/local/tomcat/conf/context.xml
sed -i 's/\${DB_PORT}/'"$DB_PORT"'/g' /usr/local/tomcat/conf/context.xml
sed -i 's/\${DB_NAME}/'"$DB_NAME"'/g' /usr/local/tomcat/conf/context.xml
sed -i 's/\${DB_USER}/'"$DB_USER"'/g' /usr/local/tomcat/conf/context.xml
sed -i 's/\${DB_PASSWORD}/'"$DB_PASSWORD"'/g' /usr/local/tomcat/conf/context.xml

# Start Tomcat
catalina.sh run
