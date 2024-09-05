## Creazione del database
Lo schema del database si trova in `src/main/resources/schema.sql` e deve essere importato manualmente su PostgreSQL.
## Generazione DAO e modelli DB con MyBatis
Eseguire il seguente comando per avviare la generazione del codice Java dopo una modifica al database:
```
mvn mybatis-generator:generate -Dmybatis.generator.overwrite=true
```
## Configurare Tomcat JDBC Pool
1. Scaricare il [driver PostgreSQL](https://jdbc.postgresql.org/download/) e piazzarlo all'interno di `$CATALINA_HOME/lib/`
2. Modificare il file `$CATALINA_HOME/conf/context.xml` inserendo il seguente blocco all'interno di `<Context>` (fare riferimento alla [documentazione](https://tomcat.apache.org/tomcat-9.0-doc/jdbc-pool.html#Attributes) per gli attributi):
    ```xml
    <Resource name="jdbc/MyDB"
              auth="Container"
              type="javax.sql.DataSource"
              driverClassName="org.postgresql.Driver"
              url="jdbc:postgresql://localhost:5432/movie_app"
              username="postgres"
              password="root"
              initialSize="3"
              maxTotal="10"
              maxIdle="5"
              minIdle="2"
              maxWait="10000"
              testOnBorrow="true"
              validationQuery="SELECT 1"
              validationQueryTimeout="7"
              removeAbandoned="true"
              />
    ```
3. Modificare il file `WEB-INF/web.xml` della web app Java inserendo il seguente blocco:
    ```xml
    <web-app>
        <resource-ref>
            <description>PostgreSQL Datasource</description>  
            <res-ref-name>jdbc/MyDB</res-ref-name>  
            <res-type>javax.sql.DataSource</res-type>  
            <res-auth>Container</res-auth>  
        </resource-ref>
    </web-app>
    ```
4. Accedere al datasource tramite Java:
    ```java
    InitialContext ctx;  
    try {  
        ctx = new InitialContext();  
        return (DataSource) ctx.lookup("java:comp/env/jdbc/MyDB");  
    } catch (NamingException e) {  
        throw new MovieAppException(e);  
    }
    ```