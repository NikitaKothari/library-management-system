# spring-rest-mvc-hibernate-angular

This is a RETful API built using Spring.io with Hibernate for database interaction and an angular front end.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes

### Prerequisities

What things you need to install

```
mvn
java >= 1.8
Access to a MySql Server
```

### Installing

Create a database called tiyssa on your MySql server

```
CREATE DATABASE `tiyssa`;
```

Create employee table inside the newly created employeedb

```
CREATE TABLE `tiyssa`.`employee` (
  `employeeId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `homePhone` varchar(255) DEFAULT NULL,
  `cellPhone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`employeeId`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

Clone this repository to your computer

```
git clone https://github.com/erics273/spring-rest-mvc-hibernate-angular.git;
```

Update the Database Configuration to reflect your database connection information

```
/src/main/java/com/tiyssa/config/DbConfig.java
@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/tiyssa?autoReconnect=true&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("P@ssw0rd");
		return dataSource;
	}
```

Launch the API with with the following command

```
mvn spring-boot:run
```

Verify the API is running by visiting

```
http://localhost:8080/employee
```

### Testing the API with an HTTP client of your choice (e.g https://www.getpostman.com/)

Get all Employees

```
GET http://localhost:8080/employee
```

Get an employee by id

```
GET http://localhost:8080/employee/{id}
```

Add an employee

```
POST http://localhost:8080/employee

JSON sample request
{
  "firstName": "eric",
  "lastName": "yuck",
  "email": "erics273@gmail.com",
  "homePhone": "8888888888",
  "cellPhone": "8888888888",
  "password": "P@ssw0rd1",
  "active": 1
}
```

Update an employee

```
PUT http://localhost:8080/employee/{id}

JSON sample request
{
  "employeeId": 1
  "firstName": "eric",
  "lastName": "yuck",
  "email": "erics273@gmail.com",
  "homePhone": "8888888888",
  "cellPhone": "8888888888",
  "password": "P@ssw0rd1",
  "active": 1
}
```

Delete an employee

```
DELETE http://localhost:8080/employee/{id}
```

Access the AngularJS front end

```
http://localhost:8080/
```


## Things worth mentioning

* **/src/main/java/com/tiyssa/entity/Employee.java** - Your employee model that describes your schema
* **/src/main/java/com/tiyssa/controller/EmployeeController.java** - Home to your route annotations and CRUD logic for an employee
* **/src/main/java/com/tiyssa/dao** - DAO classes for DB communication with Hibernate
* **/src/main/java/com/tiyssa/services** - Services that interact with your DAO class
* **/src/main/java/com/tiyssa/config/** - Home to the MVC and database configuration
