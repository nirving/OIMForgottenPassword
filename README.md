OIMForgottenPassword
====================
* cd to ```designconsole\lib```
  ```sh
  mvn install:install-file -Dfile=oimclient.jar -DgroupId=com.oracle.oim -DartifactId=client -Dversion=11.1.2.2.0 -Dpackaging=jar
  ```
* cd to ```designconsile\ext```
  ```sh
  mvn install:install-file -Dfile=jrf-api.jar -DgroupId=com.oracle.oim -DartifactId=jrf-api -Dversion=11.1.2.2.0 -Dpackaging=jar
  
  mvn install:install-file -Dfile=wlfullclient.jar -DgroupId=com.oracle.oim -DartifactId=wlfullclient -Dversion=11.1.2.2.0 -Dpackaging=jar
  ```
* copy ```maven\client-11.1.2.2.0.pom``` to ```repository\com\oracle\oim\client\11.1.2.2.0```
* Confirm compilation
  ```sh
  cd OIMForgottenPassword
  mvn install
  ```
* Start Web Service
  ```sh
  cd WSServer
  mvn jetty:run
  ```
  
SOAP UI
=======
* Open OIMForgottenPassword-soapui-project in SOAP UI
* in ```Questions\Request1``` change ```<sch:QuestionsRequest>xxxxxx</sch:QuestionsRequest>``` to include the User Login for the user.
* Submit and the Questions should be returned
* in ```Answers\Request1```  change ```<sch:AnswersRequest uid="xxxxxx">``` to include the User Login for the user.
* Update ```<sch:Answer><sch:id>1</sch:id><sch:question>xxxxxx</sch:question><sch:answer>?</sch:answer></sch:Answer>``` to include the Question 1 text and the answer. Do the same for Question 2 and 3
* Update ```<sch:Password confirm="xxxxxx">xxxxxx</sch:Password>``` to contain the password and the confirm password values
* Submit and it should return ```true``` to indicate all was succesful.