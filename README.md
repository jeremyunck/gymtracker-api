# gymtracker-api
This is the API for the GymTracker app. It is a RESTful API built with Spring Boot, Java 17, and MySQL.
The purpose of this API is to provide a backend for the GymTracker app. The API is hosted on AWS through elastic beanstalk 
and the database it connects to is hosted on AWS RDS.

## Local Setup
1. Clone the repository
2. Open the project in your IDE of choice
3. Create a MySQL database locally and create a database called `gymtrackerdb`
4. Set the following environment variables:
    - `DATASOURCE_URL` - The URL of the database you created in step 3 (e.g. `jdbc:mysql://localhost:3306/gymtrackerdb`)
    - `DATASOURCE_USERNAME` - The username of the database you created in step 3 (e.g. `root`)
    - `DATASOURCE_PASSWORD` - The password of the database you created in step 3 (e.g. `password`)
5. Run the application

The project uses JPA to set up the tables for you. The API should now be running on `localhost:8080`. 
You can test it through the swagger UI at http://localhost:8080/api/v1/swagger-ui/index.html#/.