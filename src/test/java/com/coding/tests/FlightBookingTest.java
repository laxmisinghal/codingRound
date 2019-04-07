package com.coding.tests;
import com.coding.pages.FlightBookingLogic;
import com.coding.testbase.TestBase;
import com.coding.utilities.JSONDataProvider;
import com.coding.utilities.WaitConfig;
import com.sun.javafx.PlatformUtil;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class FlightBookingTest extends TestBase{

	FlightBookingLogic flightBooking;
	
	@Override
	public String getName() {
		return "Flight Booking Test";
	}
	
	@BeforeTest
	@Override
	public void setUpPage() {
	
    	flightBooking = new FlightBookingLogic(driver);
		JSONDataProvider.dataFile = DATA_FILE;
		
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class, enabled = true)
	public void tc001_flightBookingTest(String rowID, String description, JSONObject testData)
			throws InterruptedException, IOException, NumberFormatException, ClassNotFoundException {
	    	
    	Assert.assertTrue(flightBooking.oneWayJourneyTest(testData.get("ORIGIN").toString(), testData.get("DESTINATION").toString(), testData.get("YEAR").toString(), testData.get("MONTH").toString(), testData.get("DAY").toString()));

    /*private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }*/

  }
}
