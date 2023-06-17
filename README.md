# ktor-notes-api

A simple REST APIs made with ktor for performing CRUD operation and authenticating users.
It manages notes created by the users.

## Features
1. User Login / Signup 
2. Perform CRUD operation on notes
3. JWT Authentication
4. Ktorm ORM for DB
5. Dependency Injection using Koin
6. Password Hashing using Bcrypt 

## Build With

[ktor server](https://ktor.io/docs/welcome.html) : Framework for building REST APIs.  
[JSON Web Token](https://jwt.io/introduction) : To access the authenticated ends.  
[Ktorm](https://www.ktorm.org/) : ORM for SQL database.  
[Docker](https://ktor.io/docs/docker.html) : For Containerizing.  
[Jackson](https://github.com/FasterXML/jackson) : For serialization and deserialization of JSON.  
[Koin](https://insert-koin.io/) : For dependency injection.  
[Bcrypt](https://github.com/patrickfav/bcrypt): For password hashing.

## Installation

1. Create tables in your sql database (because ktorm not creates table if they don't exist)
   according to the ktorm tables located in `data/schema` package.
2. Introduce these environment variables in your server to integrate with sql database and
   configure jwt token.
   1. `DB_URL` - URL of your database.
   2. `DB_USER` - database username.
   3. `DB_PWD` - database password.
   4. `JWT_SECRET` - secret for jwt token.
   5. `JWT_REALM` - jwt realm
   6. `PORT` - (optional) For the port to use  
      OR you can edit the variables name according to your server in `/src/main/resources/application.conf` file.
3. Deploy to the server that supports FatJar or Docker OR open this project in IntelliJ IDEA, community or enterprise edition
   and follow [these steps](https://ktor.io/docs/intellij-idea.html#run_app) to run the Application.

[More info on Deployment of ktor project.](https://ktor.io/docs/deploy.html)

## More info

This is the server side implementation. The client side is an [android](https://developer.android.com/) app which you
can find [here](https://github.com/sDevPrem/my-notes-mvvm-retrofit) to see this REST API in action.
