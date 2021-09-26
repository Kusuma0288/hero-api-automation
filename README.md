# hero-api-automation

The Project is for api automation 

# Installation

Use the maven commands to install the dependencies and

```sh 
mvn clean install
```

To execute the project need to pass the parameters -Denv=<prod> -P<profilename>
For ex:
test = It will run in the test environment (Check the URL under resources/config/test.properties). Currently, this project supports only prod env.

To run the tests

```sh
mvn clean verify -Denv=prod -Pregression
```

To run the tests and include the Request and Response in the report

```sh
mvn clean verify -Denv=prod -Pregression -DsaveRequestResponse=yes
```

# Installing Project Lombok Plugin in Intellij

Go to File > Settings > Plugins
Click on Browse repositories...
Search for Lombok Plugin
Click on Install plugin
Restart IntelliJ IDEA


# Verifying the results

Results are published in the below folder.
herokapp-api-automation\target\cucumber-reports\index.html
