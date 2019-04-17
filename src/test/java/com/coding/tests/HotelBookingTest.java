package com.coding.tests;

import com.coding.pages.HotelBookingPage;
import com.coding.testbase.TestBase;
import com.coding.utilities.JSONDataProvider;
import com.coding.utilities.WaitConfig;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

//@Listeners(com.coding.listeners.CustomListeners.class)

public class HotelBookingTest extends TestBase {

	HotelBookingPage hotelBooking;

	/*@Override
	public String getName() { 
		return "Hotel Booking Test";
	}*/

	@BeforeTest
	@Override
	public void setUpPage() {

		hotelBooking = new HotelBookingPage(driver);
		JSONDataProvider.dataFile = DATA_FILE;
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class, enabled = true)
	public void tc001_hotelBookingTest(String rowID, String description, JSONObject testData) {

		log.debug("Inside Hotel Booking Test");
		Assert.assertTrue(hotelBooking.hotelBooking(testData.get("LOCALITY").toString(),
				testData.get("CHECKINYEAR").toString(), testData.get("CHECKINMONTH").toString(),
				testData.get("CHECKINDAY").toString(), testData.get("CHECKOUTYEAR").toString(),
				testData.get("CHECKOUTMONTH").toString(), testData.get("CHECKOUTDAY").toString()));
		Reporter.log("HotelBookingTest Over");	
	}

}
