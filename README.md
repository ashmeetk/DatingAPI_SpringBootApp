# DatingAPI_SpringBootApp
SpringBoot app which uses: SpringRest, SpringDataJPA, JUnitTesting, SpringValidation. 
Course referred: Project Development Using Spring Boot by Bharath Thippireddy


#SpringBoot Application:
  Open app in STS. To start app, right click on datingapi and run as spring boot app. App will be started on tomcat port 8080.
  We have defined serval REST API's. Through Postman we can send REST requests.
    1. To save user details in database. For constraints, refer Validations section.
    Method: POST
    API: http://localhost:8080/edating/api/users/register-user
    RequestBody: {
                    "userName":"ashmeet",
                    "password":"testing",
                    "age":23,
                    "email":"ashmeetkb13@gmail.com",
                    "phoneNumber":"9540919888",
                    "gender":"Female",
                    "city":"Delhi",
                    "country":"India"
                 }
    2. To update an interest for a user.
    Method: POST
    API: http://localhost:8080/edating/api/interest/update
    RequestBody: {
                    "likes":"writing",
                    "dislikes":"junk food",
                    "hobbies":"badminton",
                    "profileUrl":"ashmeetkb13@gmail.com",
                    "about":"25 years old",
                    "userAccountId":"4"
                 }
    3. To get list of all users
    Method: GET
    API: http://localhost:8080/edating/api/users/get/all
    
    4. To delete a particular user and his interests
    Method: DELETE
    API: http://localhost:8080/edating/api/users/delete/1
    
    5. To get all matching users for a particular user. A matching user is when he has same age, city and country.
    Method: GET
    API: http://localhost:8080/edating/api/users/matches/5

#Validations performed on UserAccount method.
  To check all validations, open UserAccount.java entity. All validations on the fields are listed just above where that field is declared.

#Database-MySQL
  Refer EDatingAPIs.sql to understand all the sql queries and the tables created. Two tables are created:
    1. user_account table: contains all user details
    2. interest table: contains all interest details.
    There exists one to one mapping bw both tables. i.e. a user will have only one interest and vice versa.
