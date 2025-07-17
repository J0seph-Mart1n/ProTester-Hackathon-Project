//// File: DistrictMoviePage.java
//package com.test.objectRepository;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
///**
// * Encapsulates movie-booking flow: open Movies, pick a show, select seats.
// */
//public class DistrictMovieSeat {
//    private WebDriver driver;
//
//    @FindBy(xpath = "//a[text()='Movies']")
//    private WebElement moviesMenu;
//
//    @FindBy(xpath = "//*[@class='available']")
//    private List<WebElement> availableSeats;
//
//    @FindBy(xpath = "//h3[text()='Seats']")
//    private WebElement seatsCountHeader;
//    
//
//
//    public DistrictMovieSeat(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//    
//    public String getSeatsCountText() {
//        String txt = seatsCountHeader.getText();       // e.g. "Seats (5)"
//        return txt.replaceAll("\\D+", "");             // strips non‐digits → "5"
//    }
//
//    /** Parse that text to an int */
//    public int getSelectedSeatsCount() {
//        return Integer.parseInt(getSeatsCountText());
//    }
//    
//    /**
//     * Selects n random seats for the given movie and time.
//     */
//    
//    public void selectSeats(int n, String movieName,String date, String timeSlot) throws InterruptedException {
//        moviesMenu.click();
//        Thread.sleep(3000);
//
//        // dynamic movie & time locators
//        driver.findElement(By.xpath(String.format("//h5[text()='%s']", movieName))).click();
//        driver.findElement(By.xpath(String.format("//div[text()='%s']", date))).click();
//        driver.findElement(By.xpath(String.format("//div[text()='%s']", timeSlot))).click();
//
//        System.out.println("Seats available: " + availableSeats.size());
//        
//	    if (availableSeats.size()>n) {
//	        // pick n unique random seats
//	        Set<Integer> chosen = new HashSet<>();
//	        Random rnd = new Random();
//	        while (chosen.size() < n) {
//	            int idx = rnd.nextInt(availableSeats.size());
//	            if (chosen.add(idx)) {
//	                availableSeats.get(idx).click();
//	            }
//	        }
//	
//	        // verify
//	        String text = seatsCountHeader.getText();
//	        if (text.contains(String.valueOf(n))) {
//	            System.out.println("Seats displayed are correct");
//	        } else {
//	            System.out.println("Seats displayed are wrong");
//	        }
//	    }
//        else {
//        	System.out.println("Enter valid no of Seats");
//        } 
//        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
//    }
//}


// File: DistrictMoviePage.java
package com.test.objectRepository;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object for movie-booking flow:
 * navigate to Movies, choose movie/date/time, select seats, verify count.
 */
public class DistrictMovieSeat {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[text()='Movies'][1]")
    private WebElement moviesMenu;

    @FindBy(xpath = "//*[@class='available']")
    private List<WebElement> availableSeats;

    @FindBy(xpath = "//h3[text()='Seats']")
    private WebElement seatsCountHeader;

    public DistrictMovieSeat(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ----- Navigation & Selection Helpers -----

    public void openMoviesSection() {
        wait.until(ExpectedConditions.elementToBeClickable(moviesMenu)).click();
    }

    public void selectMovie(String movieName) {
    	String movieName_str = "//h5[text()="+movieName+"]";
        By locator = By.xpath(movieName_str);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
    }

    public void selectDate(String date) {
    	String moviedate_str = "//div[text()="+date+"]";
        By locator = By.xpath(moviedate_str);
        //By locator = By.xpath(String.format("//div[text()='%s']", date));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void selectTimeSlot(String timeSlot) {
    	String movieTime_str = "//div[text()="+timeSlot+"]";
        By locator = By.xpath(movieTime_str);
        //By locator = By.xpath(String.format("//div[text()='%s']", timeSlot));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // ----- Seat Counts & Actions -----

    /**
     * @return how many seats are currently selected, parsed from header text.
     */
    public int getSelectedSeatsCount() {
        String raw = seatsCountHeader.getText();    // e.g. "Seats (5)"
        String digits = raw.replaceAll("\\D+", "");
        return Integer.parseInt(digits);
    }

    public int getAvailableSeatsCount() {
        // force evaluation in case the list is lazy
        //wait.until(d -> availableSeats.size() > 0);
        return availableSeats.size();
    }

    /**
     * Randomly picks `n` unique available seats.
     * @param n the number of seats to select
     * @return true if exactly n seats were selected
     */
    public boolean selectRandomSeats(int n) {
        int total = getAvailableSeatsCount();
        if (total < n) {
            throw new IllegalArgumentException(
                String.format("Requested %d seats but only %d available", n, total));
        }

        Set<Integer> chosen = new HashSet<>();
        Random rnd = new Random();
        while (chosen.size() < n) {
            int idx = rnd.nextInt(total);
            if(idx<=total) {
	            if (chosen.add(idx)) {
	                availableSeats.get(idx).click();
	            }
            }
        }

        // verify count
        return getSelectedSeatsCount() == n;
    }

    // ----- High‐Level Flow -----

    /**
     * Complete seat‐selection flow: navigate → choose show → pick seats → verify.
     */
    public void bookRandomSeats(String movie, String date, String time, int seatCount) {
        openMoviesSection();
        selectMovie(movie);
        selectDate(date);
        selectTimeSlot(time);

        System.out.println("Seats available before selection: " + getAvailableSeatsCount());
        boolean ok = selectRandomSeats(seatCount);

        if (ok) {
            System.out.println("✓ Successfully selected " + seatCount + " seats.");
        } else {
            System.err.println("✗ Seat count mismatch: expected " + seatCount +
                               ", but got " + getSelectedSeatsCount());
        }
        System.out.println("---------------------------------------------------");
    }
}
