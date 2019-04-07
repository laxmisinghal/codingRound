package com.coding.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.coding.utilities.WaitConfig;
import com.sun.javafx.PlatformUtil;

public class Base {

	protected static WebDriver driver = null;

	protected Base(WebDriver driver) {
		this.driver = driver;
	}
	
	protected boolean isElementPresent(WebElement element) {
	     try {
	         if(element.isDisplayed()) {
	         return true;
	         }
	     } catch (NoSuchElementException e) {
	         return false;
	     }
		return false;
	 }
	
	protected void setValueToTextField(String input, WebElement element) {
		try {
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
			element.click();
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION, TimeUnit.MILLISECONDS);
			System.out.println("input is "+input);
			element.sendKeys(input);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean selectPlaceFromDropDown(List<WebElement> elementList, WebElement ddElement) {
		try {
			driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD, TimeUnit.MILLISECONDS);
			System.out.println("list size is "+elementList.size());
			for(int i=1;i<=elementList.size();i++) {
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
		}catch (Exception e) {
			System.out.println("No place found by your search criteria");
			e.printStackTrace();
		}
			return false;
	}
	
}