# call_centre_manager
Managing of teams by call centre agents

App Stack
* Java 11
* Springboot 2.3.5.RELEASE
* Gradle 6.6.1
* RDBMS: Postgres driver v: 42.2.8
* Swagger-ui v: 2.9.2
* Lombok

Getting started 
* Please make sure you have java >= 11 installed. Make sure you have an instance of postres installed(On prem, remote or docker)
* Replace Application.yaml with your database access secrets under spring > datasource.
* *Under url, replace localhost with your host, replace db name and use ccm as currentSchema*
* *Replace username and password*
* Run Resources> data.sql in your postgres db session

Running the app
1. ```gradle build```
2. ```gradle bootRun```

App usage
*Swagger UI has been built into the app, and you can access the deployed ui interface at http://localhost:8080/swagger-ui.html or the deployed one at http://vast-anchorage-06801.herokuapp.com/swagger-ui.html# *
