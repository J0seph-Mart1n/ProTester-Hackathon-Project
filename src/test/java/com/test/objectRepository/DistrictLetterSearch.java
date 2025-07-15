package com.test.objectRepository;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class DistrictLetterSearch {
    WebDriver driver;
    Actions actions;

    public DistrictLetterSearch(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(css = ".dds-w-8.dds-h-8.dds-flex.dds-items-center.dds-justify-center")
    WebElement menuButton;

    @FindBy(xpath = "//*[@id='page-content']/div[3]/div/div/div/div/div[2]")
    WebElement hoverSection;

    @FindBy(xpath = "//*[@id='page-content']/div[3]/div/div/div/div/div[2]/div[2]/div[2]")
    WebElement scrollTarget;

    @FindBy(xpath = "//*[@id='page-content']/div[3]/div/div/div/div/div[2]/div[2]/div[1]/button")
    List<WebElement> letterButtons;

    @FindBy(xpath = "//*[@id='page-content']/div[3]/div/div/div/div/div[2]/div[2]/div[2]")
    List<WebElement> resultButtons;

    // Actions
    public void clickMenuButton() throws InterruptedException {
        Thread.sleep(5000);
        menuButton.click();
    }

    public void hoverAndScroll() {
        actions.moveToElement(hoverSection).perform();
        actions.scrollToElement(scrollTarget).perform();
    }

    public void clickLetter(String letter) {
        for (WebElement l : letterButtons) {
            if (l.getText().equalsIgnoreCase(letter)) {
                l.click();
                break;
            }
        }
    }

    public void printAllResultButtons() {
        for (WebElement button : resultButtons) {
            System.out.println(button.getText());
        }
    }
}
