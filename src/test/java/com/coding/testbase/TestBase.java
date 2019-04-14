package com.coding.testbase;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.coding.pages.Base;
import com.coding.utilities.ExtentManager;
import com.coding.utilities.WaitConfig;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
//import com.sun.javafx.PlatformUtil;
import com.sun.jna.Platform;
import com.sun.jna.platform.unix.X11.Window;


public abstract class TestBase {

	public static WebDriver driver;

	private static Properties prop = new Properties();
	private static InputStream input = null;
	public static String includePattern = null;
	public static final String DATA_FILE = "TestData.json";
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public ExtentReports reports = ExtentManager.getInstance();
	public static ExtentTest test;
	public static ExtentReports extent;

	@BeforeTest
	public abstract void setUpPage();

	public abstract String getName();

	@BeforeSuite
	public void setUpDriver() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		if (Platform.isWindows()) {
			String cwd = System.getProperty("user.dir") + "\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", cwd);
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			options.setExperimentalOption("prefs", prefs);

			driver = new ChromeDriver(options);
			log.debug("Windows Chrome Launched");
		} else if (Platform.isMac()) {
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", "chromedriver");
			DesiredCapabilities caps = DesiredCapabilities.chrome();

			caps.setCapability(ChromeOptions.CAPABILITY, options);
			caps.setCapability("acceptInsecureCerts", true);
			caps.setCapability("acceptSslCerts", true);

			driver = new ChromeDriver(options);
		} else if (Platform.isLinux()) {
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
			DesiredCapabilities caps = DesiredCapabilities.chrome();

			caps.setCapability(ChromeOptions.CAPABILITY, options);
			caps.setCapability("acceptInsecureCerts", true);
			caps.setCapability("acceptSslCerts", true);

			driver = new ChromeDriver(options);

		}

		String URL = "https://www.cleartrip.com/";
		driver.get(URL);
		log.debug("ClearTrip website launched");
		// driver.manage().window().maximize();

	}

	@SuppressWarnings("restriction")
	private void setDriverPath() {
		if (Platform.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (Platform.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (Platform.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.debug("Driver closed");
	}


}