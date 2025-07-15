package com.test.objectRepository;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DistrictLogInPage{
	
	WebDriver driver;
	// Constructor that initializes the web elements on the HomePage
	public DistrictLogInPage(WebDriver driver) {	
		this.driver = driver; 
		PageFactory.initElements(driver, this); // Re-initialization for local elements

	}

	// Web element for the user logo
	@FindBy(xpath = "//*[@class='dds-cursor-pointer']")
	WebElement userProfile;
	
	
	// Web element for the logged In user
	@FindBy(xpath = "//*[@id='master-header']/div/div[1]/div[2]/div[2]/a/div[contains(text(),'U')]")
	WebElement LoggedInUser;
	
	
	// Web element for mobile number field
	@FindBy(xpath = "//*[@id=\"page-content\"]/div[3]/div/div/div/div/div[2]/div[2]/div[2]/div/input")
	WebElement mobNo;

	// Web element for continue
	@FindBy(xpath = "//*[@id=\"page-content\"]/div[3]/div/div/div/div/div[2]/button")
	WebElement continueBtn;

	// Web element for login
	@FindBy(xpath = "//*[@id=\"page-content\"]/div[3]/div/div/div/div/div[2]/button")
	WebElement loginBtn;

	// Web element for logout

	@FindBy(xpath = "//*[@id=\"page-content\"]/section/div/div/div[2]/button")
	WebElement logoutBtn;
	
	@FindBy(xpath = "//*[@id=\"page-content\"]/div[3]/div/div/div/div/div[2]/label")
	WebElement enterOtpText;
	
	// Click on user Profile
	public void clickOnUser() {
		userProfile.click();
	}

	// Clicks the Continue button
	public void clickOnContinue() {
		continueBtn.click();
	}

	// Click on login
	public void clickOnLogin() {
		loginBtn.click();
	}

	// Click on logout
	public void clickOnLogout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
	}
	
	
	public void enterMobNo(String Mob) {
		mobNo.sendKeys(Mob);
	}
	
	public boolean isLoginDisabled() {
		if(loginBtn.isEnabled()) {
			return false;
		}
		return true;
	}
	
	
	
//	public void clearOtp() {
//		for(int i=1;i<=6;i++) {
//			String xpath = "//*[@id='page-content']/div[3]/div/div/div/div/div[2]/div[2]/div/input[" + i + "]";
//			WebElement inputField = driver.findElement(By.xpath(xpath));
//			inputField.clear();
//		}
//	}
	
	public void LoginDisabledHandled() {
		System.out.println("Incorrect OTP...Please enter correct OTP");

	}
	
	public boolean isLoginButtonPresent() {
//		if(loginBtn.isDisplayed()) {
//			return true;
//		}
//		return false;
		
		if(driver.findElement(By.xpath("//*[@id='page-content']/div[3]/div/div/div/div/div[2]/button")).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isUserLoggedIn() {
		if(enterOtpText.isDisplayed()) {
			return false;
		}
		return true;
	}
	
	//enter OTP on console
	public void enterOtp() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("First Enter OTP then enter 'ok' to continue ");
		String code = "";
		int cnt = 0;

		while (!code.equalsIgnoreCase("ok")) {
			if (cnt > 0) {
				System.out.println("Enter 'ok' to continue");
			}
			code = scanner.nextLine();
			// excutes if otp code is 6 digit and between 0-9
			if (code.length() == 6 && code.matches(("\\d+")) && cnt == 0) {
				for (int i = 1; i <= 6; i++) {

					char digit = code.charAt(i - 1); // Get each digit

					String xpath = "//*[@id='page-content']/div[3]/div/div/div/div/div[2]/div[2]/div/input[" + i + "]";
					WebElement inputField = driver.findElement(By.xpath(xpath));
					inputField.clear();
					inputField.sendKeys(Character.toString(digit));
					cnt++;
				}
				System.out.println("Waiting... Type 'ok' to proceed.");
				code = scanner.nextLine();
			}
			else if (cnt == 0) {
				System.out.println("Enter the Correct OTP");
			}
		}
		scanner.close();
	}
}
