package com.coding.tests;

import com.coding.pages.HotelBookingPage;
import com.coding.pages.SignInPage;
import com.coding.testbase.TestBase;
import com.coding.utilities.JSONDataProvider;
import com.sun.javafx.PlatformUtil;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest extends TestBase {

	SignInPage signIn;

	/*@Override
	public String getName() {
		return "Hotel Booking Test";
	}*/

	@BeforeTest
	@Override
	public void setUpPage() {

		signIn = new SignInPage(driver);
		// JSONDataProvider.dataFile = DATA_FILE;

	}

	@Test
	public void tc001_signInTest() {

		log.debug("Inside Sign In Test");
		Assert.assertTrue(signIn.SignIn());
	}

}
