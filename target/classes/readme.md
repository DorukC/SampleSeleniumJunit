***USEFULL COMMANDS***

mvn clean test -> runs all tests in the tests folder
mvn -Dtest=TEST_CLASS_NAME test -> runs the test class which is defined as TEST_CLASS_NAME
mvn -Dtest=TEST_CLASS_NAME#TEST_NAME test -> runs the specified test with TEST_NAME which is defined in TEST_CLASS_NAME clas.
mvn allure:report -> generates the allure report ( you need the open report manelly from the path.)
mvn allure:serve -> generatest the allure report and serve via webserver automatically.
mvn clean test -l LOG_FILE_NAME -> Writes console messages to the specified file.

***PROJECT STRUCTURE***

resouces folder -> this folder is used to store Excel files for Data Driven Tests.

---objectRepository package---

RepositoryBase Class -> It includes Repository Structure Definitions. All object repositories should extends this class.
FirstRepository Class -> It is a sample object repository class.

---utils package---

ExcelReader Class -> All the excel operations (reading, writing) operations are defined in this class.
SeleniumDriverLogger Class -> Extends AbstractWebDriverEventListener class for logging driver operations.

---tests package---
DemoTest Class -> Sample Test class.
RepositoryBasedTest Class -> Sample Test class which uses Repository. (First Repository)
DataDrivenTest -> Sample Test class which iterates tests with the number of data. ( Array List Collection )
ExcelDataDrivenTest -> Sample Test class which iterates tests with the number of excel data. ( row based )






