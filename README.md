# Cafetownsend

This project includes a test for  [https://cafetownsend-angular-rails.herokuapp.com/](https://cafetownsend-angular-rails.herokuapp.com/).

The technology stack that is used:
 * Selenium Webdriver
 * JAVA
 * Maven 
 * Extent Reports
 * WebDriverManager

### Prerequisites

In order to use these tests you need to install following software:


* [Maven](https://maven.apache.org/)
* [Java Development Kit](https://bell-sw.com/pages/java-8u232/) (JDK)


### Installing

After cloning the project from Githab, you can either build it in your IDE (IntelliJ IDEA or Eclipse) or just go to the project folder and type in command line "mvn clean install".

## Parameters
At the catalogue **\src\test\resources** you can find **selenium.properties** file where you can specify base URL of the project, reports folder and browser where the test is executed. 

## Running the tests

You can execute tests either in IDE or just go to the project folder and type in command line "mvn test".
WebDriverManager checks the platform and browsers installed and downloads required driver.  


## Results and reports

After every execution a folder named with timestamp is created where you can find HTML reports along with some statistics and with screenshots in cases of failed tests. 

### Tests Description

###### loginLogoutTest

This test opens Login page, fills username and password fields by values provided, clicks 'Login' button, checks that greeting at Employees page contains the name of the user logged in, and click 'Logout' button.

###### createDeleteEmployeeTest

This test opens Login page, fills username and password fields by values provided, clicks 'Login' button, clicks 'Create' button, fills data of a new employee, clicks 'Add' button, finds the new user in the full list of employees, selects it, clicks 'Delete' button and accept modal window confirmation.


## Author

 **Alexander Ognev** 

