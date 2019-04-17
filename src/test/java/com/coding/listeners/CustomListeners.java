package com.coding.listeners;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.coding.pages.Base;
import com.coding.testbase.TestBase;
import com.coding.utilities.ExtentManager;
import com.coding.utilities.GetScreenShot;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName().toUpperCase());
		test = reports.startTest(result.getMethod().getMethodName().toUpperCase());
		System.out.println("testcase "+result.getMethod().getMethodName().toUpperCase()+"started");
	}

	public void onTestSuccess(ITestResult result) {
	/*	test.log(LogStatus.PASS, result.getName().toUpperCase() + " PASS");
		try {
			test.log(LogStatus.PASS, test.addScreenCapture(
					GetScreenShot.capture(driver, Integer.toString(new Random().nextInt(1000000000)))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		reports.endTest(test);
		reports.flush();*/
		try {
			GetScreenShot.captureScreeshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.log(LogStatus.PASS, result.getMethod().getMethodName().toUpperCase()+"PASS");
		test.log(LogStatus.PASS, test.addScreenCapture(GetScreenShot.dest));
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("Click to see screenshots");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+GetScreenShot.screenShotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+GetScreenShot.screenShotName+"><img src="+GetScreenShot.screenShotName+" height=200 width=200> </img></a>");
		
	//		Reporter.log("<a target=\"_blank\" href="+GetScreenShot.captureScreeshot()+"><img src="+GetScreenShot.captureScreeshot()+" height=200 width=200> </img></a>");
		
		reports.endTest(test);
		reports.flush();
	
		
	}

	public void onTestFailure(ITestResult result) {
	/*	test.log(LogStatus.FAIL, result.getName().toUpperCase() + " Failed with exception" + result.getThrowable());
		try {
			test.log(LogStatus.FAIL, test.addScreenCapture(
					GetScreenShot.capture(driver, Integer.toString(new Random().nextInt(1000000000)))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		reports.endTest(test);
		reports.flush();*/
		try {
			GetScreenShot.captureScreeshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, result.getMethod().getMethodName().toUpperCase()+"FAIL");
		test.log(LogStatus.PASS, test.addScreenCapture(GetScreenShot.dest));
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("Click to see screenshots");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+GetScreenShot.screenShotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+GetScreenShot.screenShotName+"><img src="+GetScreenShot.screenShotName+" height=200 width=200> </img></a>");
		
	//		Reporter.log("<a target=\"_blank\" href="+GetScreenShot.captureScreeshot()+"><img src="+GetScreenShot.captureScreeshot()+" height=200 width=200> </img></a>");
		
		
		reports.endTest(test);
		reports.flush();
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		

	}

	public void onFinish(ITestContext context) {
		System.out.println("Passed tests: " + context.getPassedTests());                
		System.out.println("Failed tests:" + context.getFailedTests()); 


	}

	@Override
	public void setUpPage() {
		// TODO Auto-generated method stub
		
	}

/*	@Override
	public String getName() {
		return null;
	}*/

}
