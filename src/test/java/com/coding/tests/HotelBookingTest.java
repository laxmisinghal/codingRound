package com.coding.tests;

import com.coding.pages.FlightBookingPage;
import com.coding.pages.HotelBookingPage;
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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class HotelBookingTest extends TestBase {

	HotelBookingPage hotelBooking;

	@Override
	public String getName() {
		return "Hotel Booking Test";
	}

	@BeforeTest
	@Override
	public void setUpPage() {

		hotelBooking = new HotelBookingPage(driver);
		JSONDataProvider.dataFile = DATA_FILE;

	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class, enabled = true)
	public void tc001_hotelBookingTest(String rowID, String description, JSONObject testData) {
		
		Boolean returnType = hotelBooking.hotelBooking(testData.get("LOCALITY").toString(),
				testData.get("CHECKINYEAR").toString(), testData.get("CHECKINMONTH").toString(),
				testData.get("CHECKINDAY").toString(), testData.get("CHECKOUTYEAR").toString(),
				testData.get("CHECKOUTMONTH").toString(), testData.get("CHECKOUTDAY").toString());
		
		if(returnType.equals(true)) {
			Assert.assertTrue(true);
		}else
			Assert.assertTrue(false);
	}

}
