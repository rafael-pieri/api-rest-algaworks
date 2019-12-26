[![Build Status](https://travis-ci.com/rafael-pieri/api-rest-algaworks.svg?branch=master)](https://travis-ci.com/rafael-pieri/api-rest-algaworks)

## RESTful Web Services

Example of implementing a RESTFul web service. This service has no hypermedia...

### How to run the application 
Execute the following command to up a MySQL instance: 

`docker run --name mysql-instance -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.18`

And then:

`mvn spring-boot:run`

### Swagger UI

http://localhost:8081/swagger-ui.html