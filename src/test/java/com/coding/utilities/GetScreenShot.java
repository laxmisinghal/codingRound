package com.coding.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.coding.pages.Base;

public class GetScreenShot extends Base {
	public GetScreenShot(WebDriver driver) {
		super(driver);
	}
	
	public static String screenShotName;
	public static String dest;

	public static String captureScreeshot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		/*String destDir = "Reports/screenshots/" + screenShotName + ".png";
		String dest = "screenshots/" + screenShotName + ".png";
		File destination = new File(destDir);
		*/
		
		Random randomNumber = new Random();
		int random = randomNumber.nextInt(100000);
	//	screenShotName = Integer.toString(random)+".png";
		screenShotName = Integer.toString(random);
		System.out.println(System.getProperty("user.dir"));
	    File file = new File(System.getProperty("user.dir")+ "//Reports//screenshot");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
		dest = System.getProperty("user.dir") +"//Reports//screenshot//"+screenShotName+".png";
	//	File destination = new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenShotName+".png");
		File destination = new File(dest);
		System.out.println("destination is    "+destination);
		FileHandler.copy(source, destination);
		System.out.println("//Reports//screenshot//"+screenShotName+".png");
		return dest;
	}

}
