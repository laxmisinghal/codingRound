package com.coding.pages;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.coding.utilities.WaitConfig;
import com.sun.javafx.PlatformUtil;

public class FlightBookingLogic extends Base {

/*	@FindBy(id="OneWay")
	private WebElement oneWay;
	@FindBy(id = "FromTag")
	private WebElement fromTag;
	@FindBy(id="toTag")
	private WebElement toTag;
*/
	
	
	public FlightBookingLogic(WebDriver driver) {
		super(driver);
	}
	
	public boolean oneWayJourneyTest(String origin, String destination, String year, String month, String day) {
		try {
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
		//	oneWay.click();
			driver.findElement(By.id("OneWay"));
			System.out.println("oneWay clicked");
			
			// select origin and wait when element is loading
			WebElement fromTag = driver.findElement(By.id("FromTag"));
			setValueToTextField(origin, fromTag);
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
			List<WebElement> originOptionsList = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
			selectPlaceFromDropDown(originOptionsList, fromTag);
			
			// select destination and wait when element is loading
			//driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
			WebElement toTag = driver.findElement(By.id("ToTag"));
			setValueToTextField(destination, toTag);
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD, TimeUnit.MILLISECONDS);
			List<WebElement> destinationOptionsList = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
			selectPlaceFromDropDown(destinationOptionsList, toTag);
			
			//select depart date
			selectDepartDate(year, month, day);
			
			// all fields filled in. Now click on search
			driver.findElement(By.id("SearchBtn")).click();
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION_TRIPLE, TimeUnit.MILLISECONDS);
			 if(isElementPresent(driver.findElement(By.className("searchSummary")))) {
					return true;
			}else if(isElementPresent(driver.findElement(By.xpath("//*[@id=\"ResultContainer_1_1\"]/div[2]/div/a")))) {
					WebElement trySearchingAgain = driver.findElement(By.xpath("//*[@id=\"ResultContainer_1_1\"]/div[2]/div/a"));
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

	public boolean selectDepartDate(String year, String month, String day) {
		try {
			//click on calendar
			//driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();
			WebElement calendar = driver.findElement(By.xpath("//*[@id=\"ORtrip\"]/section[2]/div[1]/dl/dd/div/a/i"));
			calendar.click();
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD, TimeUnit.MILLISECONDS);
			selectDepartYear(year);
			selectDepartMonth(month);
			selectDepartDay(day);
		}catch (Exception e) {
			System.out.println("Depart date selection failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean selectDepartYear(String year){
		try {
		// Retrieving current year value
		String currentYear = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span[2]")).getText();
		System.out.println("current year is "+currentYear);
		//check whether desired year is valid (matches to current or upcoming year)
			int desiredYearInt = Integer.parseInt(year); //2018, 2019, 2020
			int currentYearInt = Integer.parseInt(currentYear);
			System.out.println("desired year is "+desiredYearInt);
			if(desiredYearInt<currentYearInt)// 2020>2019 / 2019=2019 /  X 2018>=2019 X
			{
				System.out.println("incorrect year");
				return false;
			}else if(desiredYearInt>currentYearInt){ //2019
				int counter = 0;
				//click next untill desiredyear==currentyear
				while(desiredYearInt!=currentYearInt) { //2019
					String presentYear = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span[2]")).getText();
					currentYearInt = Integer.parseInt(presentYear);  //2019 / 2020
					WebElement nextYearBtn = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[2]/div/a"));
					System.out.println("current year is "+presentYear);
					if(desiredYearInt==currentYearInt) {
						return true;
					}
				//	driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
					nextYearBtn.click();
					if(desiredYearInt==currentYearInt) {
						return true;
					}
					counter++;
				}
			}else if(desiredYearInt==currentYearInt) {
				return true;
			}
			return true;
		}catch (Exception e) {
			System.out.println("error in selecting year");
			e.printStackTrace();
			return false;
		}
	}
			
	public boolean selectDepartMonth(String desiredMonth) {
		try {
			// Select desired month after selecting desired year
			String currentMonth = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span[1]")).getText();
			if(currentMonth.contains(desiredMonth)) {
				//select day
				return true;
			}else {
				// click next untill desiredmonth==currentmonth
				while(!(currentMonth.contains(desiredMonth))) {
					//String presentMonth = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span[1]")).getText();
					//currentMonth = presentMonth;
					WebElement nextYearBtn = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[2]/div/a"));
//					if(currentMonth.contains(desiredMonth)) {
//						return true;
//					}
					nextYearBtn.click();
					String presentMonthAfterNext = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span[1]")).getText();
					currentMonth = presentMonthAfterNext;
					if(currentMonth.contains((desiredMonth))) {
						return true;
					}
				}
			}
		}catch (Exception e) {
			System.out.println("error in selecting month");
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean selectDepartDay(String desiredDay) {
		try {
			
		//	List<WebElement> allDatesList=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));
		//	List<WebElement> allDatesList = driver.findElements(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody"));
			List<WebElement> allDatesList = driver.findElements(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr/td"));
			System.out.println("no of dates " +allDatesList.size());
			for(int i=0;i<=allDatesList.size();i++) {
				if(allDatesList.get(i).getText().equals(desiredDay)) {
					allDatesList.get(i).click();
					return true;
				}
			}
			/*List<WebElement> datesListOfDesiredMonth = driver.findElements(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody"));
			for(WebElement eachDay:datesListOfDesiredMonth)
			{
				System.out.println(eachDay.getText());
				if(d.getText().trim().equals(day))
				{
					d.click();
					break;
				}
			}*/
		}catch (Exception e) {
			System.out.println("error in selecting day");
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
