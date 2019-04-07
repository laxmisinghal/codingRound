package com.coding.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitConfig {

	
	public static final int PAGE_LOAD_DURATION = 5000;
	public static final int PAGE_LOAD = 2000;
	public static final int PAGE_LOAD_DURATION_DOUBLE = 10000;
	public static final int PAGE_LOAD_DURATION_TRIPLE = 30000;
	

	public static void waitForPageToLoad(WebDriver driver, WebElement id) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(id));
	}

	public static void waitForPageToLoad(WebDriver driver, WebElement id, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(id));
	}
}
