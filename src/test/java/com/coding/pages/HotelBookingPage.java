package com.coding.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.coding.utilities.WaitConfig;

public class HotelBookingPage extends Base {

	@FindBy(xpath = "//*[@id=\"Home\"]/div/aside[1]/nav/ul[1]/li[2]/a[1]")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	@FindBy(xpath = "//*[@id=\"SearchForm\"]/section[2]/div[1]/dl/dd/div/i")
	private WebElement checkInCalendar;

	@FindBy(xpath = "//*[@id=\"SearchForm\"]/section[2]/div[2]/dl/dd/div/i")
	private WebElement checkOutCalendar;

	public HotelBookingPage(WebDriver driver) {
		super(driver);
	}

	public boolean hotelBooking(String desiredLocality, String checkInYear, String checkInMonth, String checkInDay,
			String checkOutYear, String checkOutMonth, String checkOutDay) {
		try {
			hotelLink.click();
			log.debug("Hotel Category clicked");
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
			setValueToTextField(desiredLocality, localityTextBox);
			log.debug("Entered preferred location");
		//	driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION_DOUBLE, TimeUnit.MILLISECONDS);
			List<WebElement> listOfLocalities = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
			for (int i = 0; i <= listOfLocalities.size(); i++) {
				System.out.println(listOfLocalities.size());
				String localities = listOfLocalities.get(i).getText().replace(",", " ").trim();
				System.out.println(localities);
				String desiredLocation = desiredLocality.replace(",", " ").trim();
				System.out.println(desiredLocation);
				if (localities.contains(desiredLocation)) {
					listOfLocalities.get(i).click();
					log.debug("selected prefered location");
					break;
				}
			}
			// select check-in and check-out date
			selectDate(checkInCalendar, checkInYear, checkInMonth, checkInDay);
			selectDate(checkOutCalendar, checkOutYear, checkOutMonth, checkOutDay);

			new Select(travellerSelection).selectByVisibleText("1 room, 1 adult");
			searchButton.click();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
