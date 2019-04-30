 # UWM Capstone Project - RESTful Service

&nbsp;
----

The provided codebase is a [spring-boot](https://projects.spring.io/spring-boot/) based project that requires [git](https://git-scm.com/downloads), 
[java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) _(or greater)_ and 
[apache maven](https://maven.apache.org/download.cgi) be installed on your machine.

#### Admin
*Java
_The functionality to read all admins, read an admin by email id, read an admin by email and pin, update admin by email, delete admin, and clear all admins.
    * [Rest Controller](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/controller/AdminRestController.java)
        * [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/controller/AdminRestControllerUnitTest.java)
    * [Dao](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/db/AdminDao.java)
        * [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/db/AdminDaoRowMapperUnitTest.java)
    *  [Row Mapper](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/db/AdminDaoRowMapper.java)
        *  [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/db/AdminDaoRowMapperUnitTest.java)
    
    * [Read All Admin] (http://example.uwm-nm-te-capstone.com:8333/poster-backend/admin/)
        *   Model Schema
            [
              {
                "email": "string",
                "first_name": "string",
                "last_name": "string",
                "pin": "string",
                "read_r": true,
                "role": "string",
                "write_w": true
              }
            ]
        *   Request Header
            {
              "Accept": "application/json"
            }        
        *   Response Body
            [
                {
                 "email": "string",
                 "first_name": "string",
                 "last_name": "string",
                 "read_r": true,
                 "write_w": true,
                 "pin": "string",
                 "role": "admin"
                }
            ]
    
#### Judge
*Java
_The functionality to upsert judges, read all judges, read judge by id, read judge by status, delete judge by id, update judge by id, read judge by email and pin and clear all judges.
    * [Rest Controller](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/controller/JudgeRestController.java)
        * [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/controller/JudgeRestControllerUnitTest.java)
    * [Dao](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/db/JudgeDao.java)
        * [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/db/JudgeDaoUnitTest.java)
    *  [Row Mapper](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/db/JudgeDaoRowMapper.java)
        *  [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/db/JudgeDaoRowMapperUnitTest.java)
    
        * [Read All Judges] (http://example.uwm-nm-te-capstone.com:8333/poster-backend/judge/)
            *   Model Schema
                [
                  {
                    "email": "string",
                    "first_name": "string",
                    "judge_id": 0,
                    "last_name": "string",
                    "pin": "string",
                    "role": "string",
                    "status": "string"
                  }
                ]
            *   Request Header
                    {
                      "Accept": "application/json"
                    }        
            *   Response Body
                [
                  {
                    "judge_id": 0,
                    "first_name": "string",
                    "last_name": "string",
                    "status": "string",
                    "email": "string",
                    "pin": "string",
                    "role": "judge"
                  }
                ]
    
#### Poster
*Java
_The functionality to create a poster, upsert a poster, read poster by id, get all posters,get all posters by id, update poster by id, set a vote, read posters by email and pin, insert scores by round and category, read posters by status, clear all posters and delete posters by id.
    * [Rest Controller](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/controller/PosterRestController.java)
        * [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/controller/PosterRestControllerUnitTest.java)
    * [Dao](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/db/PosterDao.java)
        * [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/db/PosterDaoUnitTest.java)
    *  [Row Mapper](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/db/PosterDaoRowMapper.java)
        *  [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/db/PosterDaoRowMapperUnitTest.java)
#### Round
*Java
_The functionality to set the round.
    * [Rest Controller](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/controller/RoundRestController.java)
        * [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/controller/RoundRestControllerUnitTest.java)
    * [Dao](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/db/RoundDao.java)
        * [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/db/RoundDaoUnitTest.java)
    *  [Row Mapper](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/db/RoundDaoRowMapper.java)
        *  [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/db/RoundDaoRowMapperUnitTest.java)
#### Score
*Java
_The functionality to insert scores, read scores by round and judge, read all scores, get scores by score id, get scores by poster id, get score by judge id, get score by round, update score, delete score by poster id, get all aggignments, upsert score, clear scores by round, read scores by round and delete score by id.  
    * [Rest Controller](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/controller/ScoreRestController.java)
        * [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/controller/ScoreRestControllerUnitTest.java)
    * [Dao](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/db/ScoreDao.java)
        * [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/db/ScoreDaoUnitTest.java)
    *  [Row Mapper](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/main/java/edu/uwm/capstone/db/ScoreDaoRowMapper.java)
        *  [Unit Test](https://gitlab.uwm-nm-te-capstone.com/uwm/2019-spring/poster-backend/blob/master/src/test/java/edu/uwm/capstone/db/ScoreDaoRowMapperUnitTest.java)
&nbsp;
---

### Building this Project

To perform a build and execute all unit tests:
```
mvn clean install
```

To execute all component tests:
```
mvn -P test-component test
```

&nbsp;
---

### Using this Project

To run the RESTful services:
```
mvn spring-boot:run
```

or you can execute the JAR that is created by the install command above via:
```
java -jar target/*.jar
```

By default, application.properties configures a local instance of H2 so that anyone can use this project immediately.
See `service/src/main/resources` for available profiles.

To run with locally installed MySQL database configured via port 3306 with a schema named 'capstone'
as defined within `service/src/main/resources/application-localmysql.properties`:
```
mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=localmysql"
```

Once the application is running locally Swagger based REST documentation and testing will be available at:
- [http://localhost:8333/poster-backend/swaggerui](http://localhost:8333/poster-backend/swaggerui)

and the Concatenation and Palindrome REST endpoints will also be accessible.

UWM Capstone Project REST-ful Service
- [poster.ceas.uwm.edu](http://poster.ceas.uwm.edu)


### Use Docker for project 

`Dockerfile` available at root of project could be used to create a docker image which could be shipped, shared. In order to build this container, run following commands

```
docker build -t poster-backend .
```
 and then this docker image could be used to run application on your machine                                                                                                                            

```
docker run -p port-to-be-run:port-of-application poster-backend
```

for example: ```docker run -p 8333:8333 poster-backend```

here application runs on 8333. You can also pull docker image from registry and run it locally, in order to pull centralized image from registry use following:

```
docker pull registry.uwm-nm-te-capstone.com:8083/nm-capstone-cookbooks/nm-capstone-backend-cookbooks/poster-backend

```
then use above listed run command to run this docker image on your machine. 

