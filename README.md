## How to develop?

#### Prerequisites:
    - docker
    - docker-compose
    - npm
    - node 8

#### Run database image:
```
$ cd docker/
$ docker-compose -f docker-compose-mysql.yml up
```

#### Migrate & seed:
```
$ cd /backend
$ node_modules/.bin/sequelize db:migrate
$ node_modules/.bin/sequelize db:seed:all
```

#### Run server:
```
$ cd ../backend/
$ node server.js
```

## How to run in Docker?

#### Prerequisites:
    - docker-compose

#### Just:
```
$ cd docker/ && docker-compose up
```
