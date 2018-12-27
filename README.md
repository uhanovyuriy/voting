Curl command

#### get All Users
`curl -s http://localhost:8080/voting/rest/admin/users --user admin1@gmail.com:admin`

#### get Users 100001
`curl -s http://localhost:8080/voting/rest/admin/users/100001 --user admin1@gmail.com:admin`

#### get All Restaurants
`curl -s http://localhost:8080/voting/rest/admin/restaurants --user admin1@gmail.com:admin`

#### get Restaurant 100005
`curl -s http://localhost:8080/voting/rest/admin/restaurants/100005  --user admin1@gmail.com:admin`

#### get Restaurant not found
`curl -s -v http://localhost:8080/voting/rest/admin/restaurants/100010 --user admin1@gmail.com:admin`

#### delete Restaurant
`curl -s -X DELETE http://localhost:8080/voting/rest/admin/restaurants/100006 --user admin2@gmail.com:admin`

#### create Restaurant
`curl -s -X POST -d '{"name":"newRestaurant","address":"newAddress"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/rest/admin/restaurants --user admin1@gmail.com:admin`

#### update Restaurant
`curl -s -X PUT -d '{"name":"UpdateRestaurant","address":"UpdateAddress"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voting/rest/admin/restaurants/100005 --user admin1@gmail.com:admin`

