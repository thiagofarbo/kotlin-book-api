![book-arquitetura drawio](https://github.com/thiagofarbo/kotlin-book-api/assets/3967737/55656d68-9a22-42c0-8705-fff8b28cad33)


# About this project
This application aims to purchase and rent books.


## Prerequisites
  * Kotlin
  * SpringBoot
  * Maven 3
  * Docker
  * Docker compose
  * Postgres
  * Redis
  * Flyway
  * Kafka

# Database.
We are using Postgrtes as our database running in a Docker container

# Docker image.
To create the Docker image, simply navigate to the project root and execute the command below

```
    docker build -f Dockerfile -t kotlin-book-api
```
# To run the Docker image execute the command below.
 ```   
    docker run -p 8085:8085 kotlin-book-api
 ```

# URL's REST API



#### Comming soon.
