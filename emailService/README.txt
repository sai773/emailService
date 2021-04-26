 Service application

port number: 8887

application path url:/api/v1/ashield/service/email


development environment commands:

1. application compiling:
 	../emailService $ sudo mvn clean install
 	
2. application building: 
	../emailService $ sudo mvn spring-boot: run
	
4. application libraries folder: pom.xml

5. application src files:  src/main/java/com.Ashield.emailServicel/Emailsvc.class

6. application properties: src/main/resources/application.properties

7. email template:  src/main/resources/templates/mail.html


making production war :

1. command is use to clean binary files in appliation:

../emailService $ sudo mvn clean

2. command is use to skip test and generate war file :

../emailService $ sudo mvn install -DskipTests

production environment commands:

1. path: ../emailService $ java -jar <emailService.war>


parameter details:

to_addr(must required)
htmlbody(must required)
subject(must required)
cc(optional)
bcc_list(optional)
button_text(optional)
button_link(optional)

use Emailds with comma seperated for sending to multiple ids

Exmaple payload details:

payload={'to_addr': email,'cc': 'user@email.co', 'bcc_list':user@email.co,  
            'button_name':'conform user', 'button_link':'https://google.com',
             'subject': 'welocme ashield','htmlBody': 'thank you' }

