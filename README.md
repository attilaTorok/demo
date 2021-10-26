# Demo project for REST endpoints

## How to run locally:
```
mvn package -Dmaven.test.skip
mvn spring-boot:run 
```
## How to run with Docker:
```
mvn package -Dmaven.test.skip
docker build -t rest .
docker run -p 8080:8080 rest
```
## Send requests with curl:
```
curl -X POST http://localhost:8080/fizzbuzz/ -H 'Content-Type: application/json' -d '3'
curl -X POST http://localhost:8080/palindrome/ -H 'Content-Type: application/json' -d 'palindrome'
curl -X POST http://localhost:8080/duplicates/ -H 'Content-Type: application/json' -d '[1,2,3,2,1]'
```