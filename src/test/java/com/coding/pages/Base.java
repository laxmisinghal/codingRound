package com.coding.pages;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.coding.utilities.ExtentManager;
import com.coding.utilities.WaitConfig;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.coding.utilities.*;

public class Base {

	@FindBy(xpath = "//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span[2]")
	public WebElement currentYear;
	/*
	 * @FindBy(xpath = "//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span[1]")
	 * protected WebElement currentMonth;
	 */@FindBy(xpath = "//*[@id=\"ui-datepicker-div\"]/div[2]/div/a")
	public WebElement nextYearBtn;

	public static WebDriver driver = null;
	public static Logger log = Logger.getLogger("devpinoyLogger");

	public Base(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.setProperty("file.encoding", "UTF-8");
	}

	public boolean isElementPresent(WebElement element) {
		try {
			if (element.isDisplayed()) {
				return true;
			}
		} catch (NoSuchElementException e) {
			return false;
		}
		return false;
	}

	public void setValueToTextField(String input, WebElement element) {
		try {
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
			element.click();
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
			System.out.println("input is " + input);
			element.sendKeys(input);
		} catch (Exception e) {
			log.error("Error in entering value");
			e.printStackTrace();
		}
	}

	public boolean selectPlaceFromDropDown(List<WebElement> elementList, WebElement ddElement) {
		try {
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD, TimeUnit.MILLISECONDS);
			System.out.println("list size is " + elementList.size());
			for (int i = 1; i <= elementList.size(); i++) {
				for (WebElement eachElement : elementList) {
					System.out.println(eachElement.getText());
					if (eachElement.getText().contains(ddElement.getText())) {
						System.out.println("yes matches");
						eachElement.click();
						System.out.println(eachElement.getText() + " clicked");
						return true;
					}
				}
			}
		} catch (Exception e) {
			log.error("No place found by your search criteria");
			e.printStackTrace();
		}
		return false;
	}

	public boolean selectYear(String year) {
		try {
			System.out.println("current year is " + currentYear);

			int desiredYearInt = Integer.parseInt(year); // 2018, 2019, 2020
			int currentYearInt = Integer.parseInt(currentYear.getText());

			System.out.println("desired year is " + desiredYearInt);
			if (desiredYearInt < currentYearInt) {
				System.out.println("incorrect year");
				return false;
			} else if (desiredYearInt > currentYearInt) {
				int counter = 0;
				// click next untill desiredyear==currentyear
				while (desiredYearInt != currentYearInt) {
					String presentYear = currentYear.getText();
					currentYearInt = Integer.parseInt(presentYear);
					System.out.println("current year is " + presentYear);
					if (desiredYearInt == currentYearInt) {
						return true;
					}
					nextYearBtn.click();
					if (desiredYearInt == currentYearInt) {
						return true;
					}
					counter++;
				}
			} else if (desiredYearInt == currentYearInt) {
				return true;
			}
			return true;
		} catch (Exception e) {
			log.error("error in selecting year");
			e.printStackTrace();
			return false;
		}
	}

	public boolean selectMonth(String month) {
		try {
			// defining currentMonth element explicitely here to handle
			// StaleElementReferenceException
			WebElement currentMonth = driver
					.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span[1]"));
			System.out.println(currentMonth.getText());
			// Select desired month after selecting desired year
			if (currentMonth.getText().contains(month)) {
				return true;
			} else {
				// click next untill desiredmonth==currentmonth
				while (!(currentMonth.getText().contains(month))) {
					System.out.println("current month is " + currentMonth.getText());
					nextYearBtn.click();
					WebElement presentMonthAfterNext = driver
							.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span[1]"));
					currentMonth = presentMonthAfterNext;
					System.out.println("current month after clicking next " + currentMonth.getText());
					if (currentMonth.getText().contains((month))) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			log.error("Error in selecting month");
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean selectDay(String desiredDay) {
		try {
			List<WebElement> allDatesList = driver
					.findElements(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr/td"));
			for (int i = 0; i <= allDatesList.size(); i++) {
				System.out.println(allDatesList.get(i).getText());
				if (allDatesList.get(i).getText().equals(desiredDay)) {
					allDatesList.get(i).click();
					return true;
				}
			}
		} catch (Exception e) {
			log.error("Error in selecting day");
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean selectDate(WebElement calendar, String year, String month, String day) {
		try {
			calendar.click();
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
			selectYear(year);
			selectMonth(month);
			selectDay(day);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}