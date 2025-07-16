package com.test.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverUtil {
	public static WebDriver driver = null;
	
	public static String browser;
	public static String url;
	
	public static WebDriver getUrl(String browser) {
		if(browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		} else if(browser.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
		} else if(browser.equalsIgnoreCase("FireFox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		//driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return driver;
	}
	public static WebDriver getDriver() {
		return driver;
	}
	public static void driverClose() {
		driver.quit();
	}
}
