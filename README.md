# AndersonWeather
* install nodejs
* run MySqlServer at port 3306:
  * create schema weather
* go to \db-service\src\main\resources\application.properties
  * change spring.datasource.username and spring.datasource.password to yours
* open project in idea or something else :
  * wait for maven dependencies to be installed 
  * setup project SDK
* open \eureka-service\src\main\java\com\eurekaService :
  * run EurekaServiceApp.java
* open \db-service\src\main\java\com\dbService
  * run DbServiceApp.java
* open \random-data-service\src\main\java\com\randomData
  * run RandomDataApp.java
* open \analyze-data-service\src\main\java\com\analyzeData :
  * run AnalyzeDataServiceApp.java
* go to thermenal :
  * enter cd client-site 
  * npm start
* in browser open localhost:3000

# do not judge strictly this is my first spring project :)
