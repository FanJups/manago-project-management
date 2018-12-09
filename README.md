# How to develop with Docker?

#### Prerequisites: 
    - docker
    - docker-compose
    - npm
    - node 8
    - mvn
    - java 8

#### Run server with mysql5 image:
```
$ cd docker/
$ docker-compose -f docker-compose-mysql.yml up
$ cd ../backend/
$ mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```
    
#### Run server with H2 in-memory db:
```
$ cd ../backend/
$ mvn spring-boot:run
```

#### Develop with IntelliJ Idea:
- import project using _pom.xml_ file
- _run_ -> _edit configurations_
- select _environment_
- pass spring profile to VM options `-Dspring-boot.run.profiles=<my_profile_here>`
- apply changes and hit _run_

#### Access PHPMyAdmin:
Visit _localhost:8000_ and login as *root* *root*
    
# How to run in Docker?

_not yet implemented_
    
    
    
    

