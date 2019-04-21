TestVagrant
===========

Review comments old framework code
----------------

\*This is a review comments file for given framework and on given test
scenarios created and executed inside the framework.

Review Comments:
----------------

**Framework:**

The TestNG framework should be modularized.

	>> The logic files & testcases files should be separated to avoid repeatation of same code. 
	>> Generic methods/utilities should be used throughout the framework wherever required. 
	>> Webdriver instantiation code is repeated in each test case. An abstract class should be created for this and inherited in each test case.
	>> Naming conventions should be proper for test cases/generic methods/logic files. Packages naming conventions should also be as per framework standards. Testcases and logic files should be separated.
    >> Custom Listener should be implemented to listen to the status of test execution and print the test Pass/Fail report using Extent Reports/ReportNG etc.
	>> Extent reports should be implemented to give much clear and crisp reports of the test along with the screenshots. 
	>> JsonDataProvider should be used to pass the test data from .json file instead of hard coded test data.
	>> Waits should be handled properly. Thread.sleep() should be avoided wherever possible.
	

Note: To see extent reports, run the test suite from testng.xml
The reports are generated inside Reports/screenshots folder within the project workspace.