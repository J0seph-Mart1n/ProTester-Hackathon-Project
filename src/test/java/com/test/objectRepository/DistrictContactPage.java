package com.test.objectRepository;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DistrictContactPage {
    WebDriver driver;
    WebDriverWait wait;

    public DistrictContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath = "//*[@id=\"footer\"]/div[1]/div[2]/a[3]/div")
    WebElement contactLink;

    @FindBy(xpath = "/html/body/div/div/form/div[1]/input")
    WebElement chooseInput;

    @FindBy(xpath = "/html/body/div/div/form/div[1]/div/div[1]")
    WebElement dropdownOption;

    @FindBy(xpath = "/html/body/div/div/form/input[1]")
    WebElement nameInput;

    @FindBy(xpath = "/html/body/div/div/form/input[2]")
    WebElement emailInput;

    @FindBy(xpath = "/html/body/div/div/form/input[3]")
    WebElement phoneInput;

    @FindBy(xpath = "/html/body/div/div/form/div[2]/textarea")
    WebElement messageInput;

    @FindBy(xpath = "/html/body/div/div/form/button")
    WebElement submitButton;
    
    @FindBy(xpath = "//img[@alt='Feedback icon']/following-sibling::h6")
    WebElement feedbackMsg;

    // Actions
    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void clickContactLink() {
        contactLink.click();
    }
    public void clickChooseInput() {
    	chooseInput.click();
    }
    public void clickDropdownOption() {
    	dropdownOption.click();
    }

    public void fillForm(String name, String email, String phone, String message) {
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        phoneInput.sendKeys(phone);
        messageInput.sendKeys(message);
        submitButton.click();
    }
    
    public boolean checkSubmitBtn() {
    	return submitButton.isEnabled();
    }
    
    public String getFeedbackMsg() {
    	return wait.until(ExpectedConditions.elementToBeClickable(feedbackMsg)).getText();
    }
    
    public void goToHomePage() {
    	driver.navigate().to("https://www.district.in/");
    }
}
