// File: DistrictMoviePage.java
package com.test.objectRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Encapsulates movie-booking flow: open Movies, pick a show, select seats.
 */
public class DistrictMovieSeat {
    private WebDriver driver;

    @FindBy(xpath = "//a[text()='Movies']")
    private WebElement moviesMenu;

    @FindBy(xpath = "//*[@class='available']")
    private List<WebElement> availableSeats;

    @FindBy(xpath = "//h3[text()='Seats']")
    private WebElement seatsCountHeader;
    


    public DistrictMovieSeat(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public String getSeatsCountText() {
        String txt = seatsCountHeader.getText();       // e.g. "Seats (5)"
        return txt.replaceAll("\\D+", "");             // strips non‐digits → "5"
    }

    /** Parse that text to an int */
    public int getSelectedSeatsCount() {
        return Integer.parseInt(getSeatsCountText());
    }
    
    /**
     * Selects n random seats for the given movie and time.
     */
    
    public void selectSeats(int n, String movieName,String date, String timeSlot) throws InterruptedException {
        moviesMenu.click();
        Thread.sleep(3000);

        // dynamic movie & time locators
        driver.findElement(By.xpath(String.format("//h5[text()='%s']", movieName))).click();
        driver.findElement(By.xpath(String.format("//div[text()='%s']", date))).click();
        driver.findElement(By.xpath(String.format("//div[text()='%s']", timeSlot))).click();

        System.out.println("Seats available: " + availableSeats.size());
        
	    if (availableSeats.size()>n) {
	        // pick n unique random seats
	        Set<Integer> chosen = new HashSet<>();
	        Random rnd = new Random();
	        while (chosen.size() < n) {
	            int idx = rnd.nextInt(availableSeats.size());
	            if (chosen.add(idx)) {
	                availableSeats.get(idx).click();
	            }
	        }
	
	        // verify
	        String text = seatsCountHeader.getText();
	        if (text.contains(String.valueOf(n))) {
	            System.out.println("Seats displayed are correct");
	        } else {
	            System.out.println("Seats displayed are wrong");
	        }
	    }
        else {
        	System.out.println("Enter valid no of Seats");
        } 
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
    }
}
