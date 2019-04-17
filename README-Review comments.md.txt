# TestVagrant 

Review comments:
---------------------------------
*This is a review comments file for framework and on given test scenarios on a sample flight/hotel booking website. 

Review Comments:
----------------------------------
**Framework:**

1. The TestNG framework should be modularized.
>> The TestNG framwork has been modularized as per standards. The logic files, testcases files are separated to avoid repeatation of same code. Base.java file is created to have all generic methods/utilities to be used throughout the framework wherever required.
>> The abstract TestBase.java class is created, which is responsible for Webdriver instantiation for each testsuite according to modification in testng.xml
>> Packages are created separately for logic files (methods), test files and utilities with proper naming conventions
>> Custom Listener is implemented which takes care of reports if test case passes or failes
>> Extent reports are implemented which give much clear and crisp reports of the test along with the screenshots.
>> JsonDataProvider is used to pass the test data from .json file instead of hard coded test data
>> Waits are handled properly
2. The Webdriver is getting instantiated in each test case. Hence, created one TestBase file for instantiating the driver for each TestSuite execution through testng.xml.
3. The framework is much maintainable, reusable and stable.

1. Create a GitHub account if not existing already.
2. Fork this repo (DO NOT CLONE).
3. Fix the errors and refactor the code, consider **abstractions, reusability and maintenance.**
4. Make sure you make multiple check-ins in the process, we would love to see your progress bit by bit.
5. Also check-in a separate file where you should list all your code review comments.
6. Send us the link of your GitHub repo to **careers@testvagrant.com**. Also attach your **resume**.

