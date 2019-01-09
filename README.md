[![Codacy Badge](https://api.codacy.com/project/badge/Grade/33debddccd5242c49ceaf51c86d71467)](https://www.codacy.com/app/YorikUh/voting?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=uhanovyuriy/voting&amp;utm_campaign=Badge_Grade)

## REST API по принятию решения где пообедать на текущий день, на основе голосования

API  построено на  **Spring-Boot** (security, web, data-jpa, cache, security-test, test), для тестирования приложения используется **HSQLDB** (in memory). 

В приложении имеется ограничение голосования по времени, выставляется в properties, 
до етого времени пользователь может передумать и проголосовать заново.

URI для доступа к ресурсам:

 - voting/rest/users — работа с пользователями (ADMIN);
 - voting/rest/users/register - регистрация пользователей (PermitAll);
 - voting/rest/users/restaurants — работа с ресторанами (ADMIN);
 - voting/rest/users/voting — голосование (ADMIN, USER);
 - voting/rest/users/voting/result — результат голосования (ADMIN, USER);
 - voting/rest/users/admin/voting - операции над историей голосования (ADMIN);


Несколько команд для приложения в CURL:

#### get All Users
`curl -s http://localhost:8080/voting/rest/users --user admin1@gmail.com:admin`

#### get Users 100001
`curl -s http://localhost:8080/voting/rest/users/100001 --user admin1@gmail.com:admin`

#### create User
`curl -s -X POST -d '{"name":"newUser","email":"newEmail@yandex.ru","password":"newpassword","registered":"2018-12-28T10:15:00","roles":["ROLE_USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/rest/users/register --user admin1@gmail.com:admin`

#### get All Restaurants (ADMIN, USER)
`curl -s http://localhost:8080/voting/rest/users/restaurants --user admin1@gmail.com:admin`
`curl -s http://localhost:8080/voting/rest/users/voting/restaurants --user user1@yandex.ru:password`

#### get Restaurant 100005
`curl -s http://localhost:8080/voting/rest/users/restaurants/100005  --user admin1@gmail.com:admin`

#### get Restaurant not found
`curl -s http://localhost:8080/voting/rest/users/restaurants/100010 --user admin1@gmail.com:admin`

#### delete Restaurant
`curl -s -X DELETE http://localhost:8080/voting/rest/users/restaurants/100006 --user admin2@gmail.com:admin`

#### create Restaurant
`curl -s -X POST -d '{"name":"newRestaurant","address":"newAddress"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/rest/users/restaurants --user admin1@gmail.com:admin`

#### update Restaurant
`curl -s -X PUT -d '{"name":"UpdateRestaurant","address":"UpdateAddress"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/rest/users/restaurants/100005 --user admin1@gmail.com:admin`

#### get All HistoryVoting
`curl -s http://localhost:8080/voting/rest/users/admin/voting --user admin1@gmail.com:admin`

#### create voice
`curl -s -v -X POST -d'{}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/rest/users/voting?restaurantId=100005 --user user1@yandex.ru:password`

#### get result voting today
`curl -s http://localhost:8080/voting/rest/users/voting/result?dateTime=2018-10-31T15:45:25 --user admin2@gmail.com:admin`
