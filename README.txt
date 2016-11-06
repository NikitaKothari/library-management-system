Library Management System

for dataCleaning:
	MySQL jar : For jdbc connection mysql-connector-java-5.1.40 
	
Software Description:
1. Spring Boot (Framework 1.4.1.RELEASE)
	
2. Java 8 (Back end)

3. Angular JS(Front end)
	AngularJS v1.5.7
	
4. Bootstrap 
	Bootstrap v3.3.7
	
5. Maven (For compilation)
	Maven 3.2.5

To start the library management project, implement following steps:
		Create ‘library’ database manually in MYSQL
		Import create script from ‘\DumpMySQL\Dump20161014-Structure’
		Import book data either from script file  ‘\DumpMySQL\Dump20161014-Dtructure’ or run data cleaning java files ‘\DataCleaning’
		
		Edit application.properties present either in ‘\ProjectJar’ folder or in code (Depends on
		which of the following method you are using) and set database password and
		username. Make sure that jar and application file should be in same folder.

		To run the library management Project, there are two ways:
			Run Project Jar
				open command prompt and run given jar as java -jar library-1.0-SNAPSHOT.jar
				Hit http://localhost:8080/library/#/   
			Java Project
				Copy given code and import in java IDE as Existing maven Project
				Compile given code using maven Command: mvn clean install
				Run Application.java as java application
				Hit http://localhost:8080/library/#/   

