# ContactManager 
### It is SpringBoot Project with Thymeleaf Frontend. Build in Spring Tool Suite.
## It is a Contact Management Application. 

### Application Features:
- Users can manage their contacts
- Users can Add, Delete, Update their contacts.
- Also added forgot password option which uses integrated mail api to send the OTP to the users Email Id (Users who forgotted their password) for resetting the password.
- Added Payment method using Razorpay Payment Integration.

### Technology used in this Project: 
- i) Thymeleaf,CSS : designing page layout. 
- ii) Java : all the logic has been written in java. 
- iii) MySQL: MySQL database has been used as database.
- iv) SpringSecurity: SpringSecurity has been used for authentication.
- v) Hibernate: Hibernate ORM is used.
- vi) Email API: Email API is also used for sending OTP to the users who forgotted their password.


### Software And Tools Required:
- Java JDK 8+ 
- Eclipse EE or Spring Tool Suite
- MySQL

### Steps To Import And Run The Project in Eclipse EE
- In Eclipse or Spring Tool Suite
- Click on File
- Select Import
- Select Projects from Git(with smart import) -> Next
- Select Clone URI -> Next
- In URI paste this url: https://github.com/swapnilbamble1438/ContactManager.git
  -> Next
-  Now in Local Destination

-  proceed -> Next

            Now only select ContactManager/MySpring_Boot_aa17i_Contact_Manager
            -> Finish
   
-  If everything goes right Project will get successfully imported
-  Now wait for few seconds for getting things properly loaded

-  Now open Project > src/main/resources > open application.properties file,
   inside this file look for
   
   spring.datasource.url=jdbc:mysql://localhost:3306/springbootnew?serverTimezone=UTC

   here "springbootnew" is the name of the database.
   
     so

   ## create database name "springbootnew" in MySQL.

    or

   (you can also create the database with different name in MySQL. but the created database
   name in MySQL should match the database name in url in application.properties file.
   so according to created database in MySQL set the database name in url in 
   application.properties 
   file.
   - also in
   - spring.mail.username= Put Your Gmail Id here
   - spring.mail.password= Put Your 16-digit App password for Gmail here
   - Now save the changes.)
  - And Try to Run the Project

  ### If you are using Spring Tool Suite 
 -  Right Click On Project > Run As > Spring Boot App 
 -  Now in Browser Type Url: localhost:8080
 -  Note: In Url put Port according to your application.properties file
 -  or if port is not mention in application.properties you can check with default port.
 -  Application will get Open
   
 ### If you are using Eclipse EE
 - Open Project > open application.properties file >
 
  Now do some changes, Change port number according to your Tomcat Server
  and save the file. 
  
 - Right Click On Project > Run as > Spring Boot App
 - Now in Browser Type Url: localhost:9002
-  Note: In Url put Port according to your application.properties file.
-  or if port is not mention in application.properties you can check with default port.
 -  Application will get Open.

### Some Screenshots of this Project:
![Home Page](a1.png)
==================================================================================================================================================================
![a2](a2.png)
==================================================================================================================================================================
![a3](a3.png)
==================================================================================================================================================================
![a4](a4.png)
==================================================================================================================================================================
![a5](a5.png)
==================================================================================================================================================================
![a6](a6.png)
==================================================================================================================================================================
![a7](a7.png)
==================================================================================================================================================================
![a8](a8.png)
==================================================================================================================================================================
![a9](a9.png)
==================================================================================================================================================================
![a10](a10.png)
==================================================================================================================================================================
![a11](a11.png)
==================================================================================================================================================================
![a13](a13.png)
==================================================================================================================================================================
![a14](a14.png)
==================================================================================================================================================================

### Screenshots of Payment using Razorpay Payment Integration 
![a15](a15.png)
==================================================================================================================================================================
![a16](a16.png)
==================================================================================================================================================================
![a17](a17.png)
==================================================================================================================================================================
![a18](a18.png)
==================================================================================================================================================================
![a19](a19.png)
==================================================================================================================================================================







### Project Creator: Swapnil Bamble


