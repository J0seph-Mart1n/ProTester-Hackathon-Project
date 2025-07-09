package com.test.objectRepository;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DistrictSignInPage {
	WebDriver driver;
	WebDriverWait wait;
	
	//Constructor
	public DistrictSignInPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	//Locator
	@FindBy(xpath="//a[@href='/profile']") WebElement profileBtn;
	@FindBy(xpath="//button[text()='Continue']") WebElement submitBtn;
	
	//Actions
	public void validateSignIn() {
		wait.until(ExpectedConditions.elementToBeClickable(profileBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
		String errorMsg = wait
			    .until(ExpectedConditions.visibilityOfElementLocated(
			        By.xpath("//div[@class='dds-p-4']/div[1]/div[2]/p")))
			    .getText();
		System.out.println(errorMsg);
	}
	
}
