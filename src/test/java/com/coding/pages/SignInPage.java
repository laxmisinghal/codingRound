package com.coding.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.coding.utilities.WaitConfig;

public class SignInPage extends Base {

	@FindBy(linkText = "Your trips")
	private WebElement yourTrips;

	@FindBy(id = "SignIn")
	private WebElement signInBtn;

//	@FindBy(css = "button#signInButton.primary")
//	@FindBy(id = "signInButton")
	@FindBy(xpath = "//*[@id=\"signInButton\"]")
	private WebElement signInPopUp;

	@FindBy(id = "errors1")
	private WebElement errors;

	@FindBy(xpath = "//*[@id=\"email\"]")
	private WebElement email;

	public SignInPage(WebDriver driver) {
		super(driver);
	}

	public boolean SignIn() {
		try {
			WaitConfig.waitForPageToLoad(driver, yourTrips);
			yourTrips.click();
			log.debug("Selecting Your Trips");
			signInBtn.click();
			log.debug("Sign In");
			
			int size = driver.findElements(By.tagName("iframe")).size();
			
			List<WebElement> nameOfFrame = driver.findElements(By.tagName("iframe"));
			driver.switchTo().frame(1); // since there is only 1 frame, not using iteration
			WaitConfig.waitForPageToLoad(driver, signInPopUp);
			signInPopUp.click();
			isElementPresent(errors);
			driver.switchTo().defaultContent();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
