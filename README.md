# QualificationTest 
##WebShop

<h1> Installation </h1>

<ol>
  <li> Download source files</li>
  <li>Create Postgre ShopDb from db_dcripts folder (backup_shopdb.sql) </li>
  <li> Change src/main/webapp/META-INF/context.xml for DB settings</li> 
  <li> Change tomcat server and port in pom.xml -> tomcat7-maven-plugin.url</li>
  <li> Change your loging path in src/main/resources/log4j.properties</li>
  <li>Build with maven3 and Java8. Use command:</li> 
</ol>
  ``` bash
  mvn -Dtomcat.deploy.username=<your tomcat user> -D tomcat.deploy.password=<your tomcat password> clean tomcat7:deploy 
  ```
