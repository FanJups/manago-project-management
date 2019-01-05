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
$ cd ../spring-backend/
$ mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

#### Run server with H2 in-memory db:
```
$ cd ../spring-backend/
$ mvn spring-boot:run
```

#### Develop with IntelliJ Idea:
- import project using _pom.xml_ file
- _run_ -> _edit configurations_
- select _environment_
- pass spring profile to VM options `-Dspring-boot.run.profiles=<my_profile_here>`. (If you want to use H2 create/drop database then do not specify spring profile and leave VM options input empty. If you wish to use it alongside mysql docker image then use `mysql` profile. Eventually, if you want to run a full-stack application, skip this section and navigate to _How to run in Docker_)
- apply changes and hit _run_

#### Running frontend:
- follow instructions in 'Run server with mysql5 image'
- navigate to `frontend/` directory
- `$ npm i && npm start`
- app is available at _localhost:4200_

#### Access PHPMyAdmin:
Visit _localhost:8000_ and login as *root* *root*

# How to run in Docker?

_not yet implemented_
