1. PREREQUISITES

  A. FOR EXECUTION
    i. JRE 8 or higher.
   ii. MySQL 4 or higher.
  iii. MySQL database for this application.

  B. FOR DEVELOPMENT
    i. JDK 8 or higer.
   ii. Apache Maven 3 or higher.
  iii. MySQL 4 or higher.
   iv. MySQL database for this application.

2. INSTALLATION

  i. Unzip car-rental-service-0.0.1.RELEASE.
 ii. Edit application.properties file located inside conf folder.
iii. Update "spring.datasource.url" with your database connection url.
 iv. Update "spring.datasource.username" with your database username.
  v. Update "spring.datasource.password" with your database password.
 vi. If app need to run in different port, then update "server.port". Default is 8080.
vii. Other configurations are not recommended to change.

3. EXECUTION

  i. Execute service.sh (./service.sh start) file located inside bin folder.
 ii. Other stop and restart options are also avialable.

4. USAGE

This service also provides an API document (web application) where clients can use it as a reference as well as testing point.
It can be accessed after service execution visiting given url: <protocol>://<domain-name>:<port>/api/doc (eg. http://localhost:8080/api/doc)

5. TROUBLESHOOTING

  i. For troubleshooting, logs will be available inside logs folder.
 ii. For further assitance, please do not hesitate to contact me at: rai.mani.37@gmail.com

Note: Source code is also included in src folder.

