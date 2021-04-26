Email Service application

development environment commands:

1. application compiling:
 	../emailService $ sudo mvn clean install
 	
2. application building: 
	../emailService $ sudo mvn spring-boot: run
	
3.	application url for testing:  http://localhost:8080/sendmail?To=saikumar.ms@ashield.co
	
4. application libraries folder: pom.xml

5. application src files:  src/main/java/com.Ashield.emailServicel/Emailsvc.class

6. application properties: src/main/resources/application.properties

7. email template:  src/main/resources/templates/mail.html


production environment commands:


1. path: ../emailService $ java -jar <emailService.war>


