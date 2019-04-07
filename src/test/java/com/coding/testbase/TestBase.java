package com.coding.testbase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.coding.utilities.WaitConfig;
import com.sun.javafx.PlatformUtil;
import com.sun.jna.platform.unix.X11.Window;


public abstract class TestBase {

	public static WebDriver driver;

	private static Properties prop = new Properties();
	private static InputStream input = null;
	public static String includePattern = null;
	public static final String DATA_FILE = "TestData.json";

	@BeforeTest
	public abstract void setUpPage();
	
	public abstract String getName();

	@BeforeSuite
	public void setUpDriver() throws InterruptedException {
		if(driver==null) {
			setDriverPath();
			driver = new ChromeDriver();
			String URL = "https://www.cleartrip.com/";
			driver.get(URL);
			Thread.sleep(10000); //temp
			//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//driver.manage().timeouts().implicitlyWait(WaitConfig.PAGE_LOAD_DURATION_DOUBLE, TimeUnit.MILLISECONDS);
			//	driver.manage().window().maximize();
			System.out.println("URL opened");
		}
	}
	
	@SuppressWarnings("restriction")
	private void setDriverPath() {
	     if (PlatformUtil.isMac()) {
	         System.setProperty("webdriver.chrome.driver", "chromedriver");
	     }
	     if (PlatformUtil.isWindows()) {
	         System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	     }
	     if (PlatformUtil.isLinux()) {
	         System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
	     }
	 }
	
	
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
			System.out.println("Driver closed");
		}
	}
}
