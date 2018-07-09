# servlettest

#Deployment instructions:
* in the **`config.properties`** file, specify the effective server address in the field **jdbc.url**
* assemble the project with the `mvn clean install` command
* file **servlettest.war** from the **target** directory deploy to host on tomcat server
* the start page will be available at `http://tomcat_server_host:servlet_port`

In the test purposes the database is filled with demostration data.

To create a database, run the script from the **create_db.sql** file.