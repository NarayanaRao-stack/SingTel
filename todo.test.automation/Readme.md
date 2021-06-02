# MVC ToDo Tasks  UI Test Automation Suite
Assumption - Developed the framework to run the tests on Chrome browser


*1) Pre-requisites:
==============================
1. Java8 is installed and JAVA_HOME is added under system environment variables
2. Maven is installed and MAVEN_HOME is added to the system environment variables
3. Download Chrome driver and copy to script directory "\tools\drivers\chrome"
4. To run the tests on different chrome version, please download and place the driver under tools\drivers\chrome


*2) Test Setup Environments:
==============================
1. OS: Windows-10
2. Browser: Chrome (Version 86.0.4240.193 (Official Build) (64-bit))
3. Chrome selenium webdriver: chromedriver
4. Editor tool: Eclipse IDE tool
5. Java version: 15.0.1
6. Apache Maven: 3.6.3
7. Cucumber eclipse plugin: 1.0.0.202

Note: Please make sure all of them are set in environment variable path
	
	
*3) Description:
==============================
This test automation suite is built in Java using the selenium Java Client. The design is based on Page Object Model and Cucumber Framework, with separate classes for Page Objects and Pages. 

Configuration files: src\main\resources\environment.properties
Step definitions: src\test\java\com\test\automation\step_definitions
Feature file: src\test\resources\featurefiles\Add-mvc-todo.feature
tools: tools\drivers\chrome\chromedriver.exe
target: Test Reports


*4) How to Run:
==============================
1. Maven command to run the tests: Goto the "todo.test.automation" directory
$mvn test -Dcucumber.options="--tags @TodoFunctional"


*5) Test Reports:
==============================
1. Cucumber Reports path (Run As 'Maven test'):
todo.test.automation\target\cucumber-html-reports\cucumber-html-reports\overview-failures.html

