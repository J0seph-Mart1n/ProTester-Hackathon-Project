package stepDefinitions;


import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictMovieSeat;
import com.test.utilities.PropertyUtility;

import io.cucumber.java.en.*;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class MovieSeat_Steps {
	WebDriver driver;
    private DistrictMovieSeat moviePage;

    @When("The user chooses seats for a movie")
    public void chooseSeats() throws InterruptedException, IOException {
    	Properties p = PropertyUtility.getMovieSeatProperties();
    	int n = Integer.parseInt(p.getProperty("seatCount"));
    	String movie = p.getProperty("movie");
    	String date = p.getProperty("date");
    	String time = p.getProperty("time");
        System.out.println("→ ENTERING chooseSeats: " + n + ", " + movie + ", " + date + ", " + time);
        driver = BaseClass.getDriver();
        moviePage = new DistrictMovieSeat(driver);
        moviePage.selectSeats(n, movie, date, time);
        System.out.println("→ EXITING chooseSeats");
    }

    @Then("The user should see exact seats selected")
    public void verifySeatCount(int expected) {
        String raw = moviePage.getSeatsCountText(); 
        System.out.println("Seats‐header raw text: \"" + raw + "\"");
        int actual = moviePage.getSelectedSeatsCount(); 
        System.out.println("Parsed actual seat count = " + actual);
        assertEquals(expected, actual,
            "Expected " + expected + " seats but saw " + actual);
        BaseClass.setDriver(driver);
    }

}
