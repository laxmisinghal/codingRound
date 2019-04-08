package com.coding.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.coding.utilities.WaitConfig;
import com.sun.javafx.PlatformUtil;

public class FlightBookingPage extends Base {

	@FindBy(id="OneWay")
	private WebElement oneWay;
	@FindBy(id = "FromTag")
	private WebElement fromTag;
	@FindBy(id="ToTag")
	private WebElement toTag;
	@FindBy(id = "SearchBtn")
	private WebElement searchButton;
	@FindBy(className = "searchSummary")
	private WebElement searchSummary;
	@FindBy(id = "//*[@id=\"ResultContainer_1_1\"]/div[2]/div/a")
	private WebElement trySearchingAgain;

	public FlightBookingPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean oneWayJourneyTest(String origin, String destination, String year, String month, String day) {
		try {
			oneWay.click();
			System.out.println("oneWay clicked");
			
			// select origin and wait when element is loading
			setValueToTextField(origin, fromTag);
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
			List<WebElement> originOptionsList = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
			selectPlaceFromDropDown(originOptionsList, fromTag);
			
			// select destination and wait when element is loading
			setValueToTextField(destination, toTag);
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD, TimeUnit.MILLISECONDS);
			List<WebElement> destinationOptionsList = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
			selectPlaceFromDropDown(destinationOptionsList, toTag);
			
			//select depart date
			WebElement calendar = driver.findElement(By.xpath("//*[@id=\"ORtrip\"]/section[2]/div[1]/dl/dd/div/a/i"));
			selectDate(calendar, year, month, day);
			
			// all fields filled in. Now click on search
			searchButton.click();
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION_TRIPLE, TimeUnit.MILLISECONDS);
			 if(isElementPresent(searchSummary)) {
					return true;
			}else if(isElementPresent(trySearchingAgain)) {
					trySearchingAgain.click();
					System.out.println(trySearchingAgain.getText());
					return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
}
