package com.coding.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.coding.testbase.TestBase;
import com.coding.utilities.GetScreenShot;
import com.coding.utilities.WaitConfig;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.javafx.PlatformUtil;

public class FlightBookingPage extends Base {

	@FindBy(id = "OneWay")
	private WebElement oneWay;
	@FindBy(id = "FromTag")
	private WebElement fromTag;
	@FindBy(id = "ToTag")
	private WebElement toTag;
	@FindBy(id = "SearchBtn")
	private WebElement searchButton;
	@FindBy(className = "searchSummary")
	private WebElement searchSummary;
	@FindBy(id = "//*[@id=\"ResultContainer_1_1\"]/div[2]/div/a")
	private WebElement trySearchingAgain;
	//@FindBy(id="//*[@id=\"GlobalNav\"]/div/div[1]/a/span")
	@FindBy(className="cleartripLogo")
	private WebElement cleartripHome;
	
	public FlightBookingPage(WebDriver driver) {
		super(driver);
	}

	public boolean oneWayJourney(String origin, String destination, String year, String month, String day) {
		try {
			oneWay.click();
			log.debug("One Way clicked");
			// select origin and wait when element is loading
			setValueToTextField(origin, fromTag);
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
			List<WebElement> originOptionsList = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
			selectPlaceFromDropDown(originOptionsList, fromTag);
			log.debug("Origin place entered");

			// select destination and wait when element is loading
			setValueToTextField(destination, toTag);
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD, TimeUnit.MILLISECONDS);
			List<WebElement> destinationOptionsList = driver.findElement(By.id("ui-id-2"))
					.findElements(By.tagName("li"));
			selectPlaceFromDropDown(destinationOptionsList, toTag);
			log.debug("Destination place entered");

			// select depart date
			WebElement calendar = driver.findElement(By.xpath("//*[@id=\"ORtrip\"]/section[2]/div[1]/dl/dd/div/a/i"));
			selectDate(calendar, year, month, day);
			log.debug("Departure date selected");
			
			// all fields filled in. Now click on search
			searchButton.click();
			WebElement flightDetails = driver.findElement(By.xpath("//*[@id=\"flightForm\"]/section[2]/div[4]/div/nav/ul/li[2]/table"));
			WaitConfig.waitForPageToLoad(driver, flightDetails);
			if (isElementPresent(flightDetails)) {
				//	WaitConfig.waitForPageToLoad(driver, cleartripHome);
				log.debug("Search results displayed");
		//		WaitConfig.waitForPageToLoad(driver, cleartripHome);
				cleartripHome.click();
				return true;
			} else if (isElementPresent(trySearchingAgain)) {
				trySearchingAgain.click();
				log.debug("Way too far date entered, please enter proper date");
				System.out.println(trySearchingAgain.getText());
				return false;
			}
		} catch (Exception e) {
 			e.printStackTrace();
 			return false;
		}
		return false;
	}

}
