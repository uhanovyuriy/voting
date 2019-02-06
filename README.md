[![Codacy Badge](https://api.codacy.com/project/badge/Grade/33debddccd5242c49ceaf51c86d71467)](https://www.codacy.com/app/YorikUh/voting?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=uhanovyuriy/voting&amp;utm_campaign=Badge_Grade)

## REST API to decide where to have lunch for the current day, based on voting

The API is built on **Spring-Boot** (security, web, data-jpa, cache, security-test, test), 
for testing the application is used **HSQLDB** (in memory). 

The application has a time limit for voting, the time is set in the properties, 
the user can change his mind before that time and change voice.

##### URI to access resources:

 - api/rest/users — working with users (ADMIN);
 - api/rest/users/register - registered users (PermitAll);
 - api/rest/restaurants — working with restaurants (ADMIN, USER);
 - api/rest/restaurants/"restaurantId"/dishes — working with dishes (ADMIN, USER);
 - api/rest/voting — voting (ADMIN, USER);

##### Caching strategy:

Caching authorisation data, when changes are made to the user database, the cache is cleared.
Data is cached at the request of restaurants and dishes, when changes are made to restaurants or dishes, 
the cache is cleared.
Idle - 5 minutes, live time - 10 minutes, max elements restaurants cache - 200, users - 1000.

##### Several commands for the application in CURL:

#### get All Users
`curl -s http://localhost:8080/api/rest/users --user admin1@gmail.com:admin`

#### get Users 100001
`curl -s http://localhost:8080/api/rest/users/100001 --user admin1@gmail.com:admin`

#### create User
`curl -s -X POST -d '{"name":"newUser","email":"newEmail@yandex.ru","password":"newpassword","roles":["ROLE_USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/rest/users/register`

#### create User with existing email
`curl -s -X POST -d '{"name":"newUser","email":"user1@yandex.ru","password":"newpassword","registered":"2018-12-28T10:15:00","roles":["ROLE_USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/rest/users/register`

#### get All Restaurants (ADMIN, USER)
`curl -s http://localhost:8080/api/rest/restaurants --user admin1@gmail.com:admin`

#### get Restaurant 100005
`curl -s http://localhost:8080/api/rest/restaurants/100005  --user admin1@gmail.com:admin`

#### get Restaurant not found
`curl -s http://localhost:8080/api/rest/restaurants/100010 --user admin1@gmail.com:admin`

#### delete Restaurant
`curl -s -X DELETE http://localhost:8080/api/rest/restaurants/100006 --user admin2@gmail.com:admin`

#### create Restaurant
`curl -s -X POST -d '{"name":"newRestaurant","address":"newAddress"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/rest/restaurants --user admin1@gmail.com:admin`

#### update Restaurant
`curl -s -X PUT -d '{"name":"UpdateRestaurant","address":"UpdateAddress"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/rest/restaurants/100005 --user admin1@gmail.com:admin`

#### create Dish
`curl -s -X POST -d '{"name":"newDish","price":"524"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/rest/restaurants/100006/dishes --user admin1@gmail.com:admin`

#### getAll Dishes for Restaurant between dates
`curl -s "http://localhost:8080/api/rest/restaurants/100005/dishes?startDate=2018-10-28&endDate=2018-10-30" --user admin1@gmail.com:admin`

#### delete Dish
`curl -s -X DELETE http://localhost:8080/api/rest/restaurants/100004/dishes/100010 --user admin2@gmail.com:admin`

#### get between HistoryVoting
`curl -s "http://localhost:8080/api/rest/voting?startDate=2018-10-28&endDate=2018-10-29" --user admin1@gmail.com:admin`

#### vote
`curl -s -v -X POST -d'{}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/rest/voting?restaurantId=100005 --user user1@yandex.ru:password`