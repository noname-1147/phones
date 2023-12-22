## Test assesment
Assessment provides service which allows to view and reserve a list of mobile phones available to the testing department.\
Project uses Micronaut framework with Java 21, and can be either run, compiled to docker image or to GraalVM native image if an environment set up accordingly.\
\
All the necessary infrastructure is included in docker-compose file.\
\
Swagger-UI is available at http://localhost:8080/swagger-ui/index.html  
\
Project is secured by JWT and you can login using POST /login method with following credentials:\
\
{\
"username":"test1",\
"password":"password"\
}\
\
Assessment mentioned that I should have used Fonapi API for network technology and bands information. As Fonoapi seems to be disabled permanently I used the following free API: https://rapidapi.com/makingdatameaningful/api/mobile-phone-specs-database  
However as information about models is mostly static I do not see the point in using any API at runtime besides the case if we want to allow the users to add new phone models, which is fine feature but out of the scope of this assessment.\
\
Tests are not included at this point as writing them would take me more than time I'm allowed to use for assessment. 
