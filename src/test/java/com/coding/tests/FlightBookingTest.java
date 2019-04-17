package com.coding.tests;

import com.coding.pages.FlightBookingPage;
import com.coding.testbase.TestBase;
import com.coding.utilities.JSONDataProvider;
import com.coding.utilities.WaitConfig;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

//@Listeners(com.coding.listeners.CustomListeners.class)

public class FlightBookingTest extends TestBase {

	FlightBookingPage flightBooking;

	/*@Override
	public String getName() {
		return "Flight Booking Test";
	}*/

	@BeforeTest
	@Override
	public void setUpPage() {
		flightBooking = new FlightBookingPage(driver);
		JSONDataProvider.dataFile = DATA_FILE;
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class, enabled = true)
	public void tc001_flightBookingTest(String rowID, String description, JSONObject testData)
			throws InterruptedException, IOException, NumberFormatException, ClassNotFoundException {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		log.debug("Inside Flight Booking Test");
		Assert.assertTrue(flightBooking.oneWayJourney(testData.get("ORIGIN").toString(),
				testData.get("DESTINATION").toString(), testData.get("YEAR").toString(),
				testData.get("MONTH").toString(), testData.get("DAY").toString()));
		Reporter.log("FlightBookingTest Over");	
	}
}
