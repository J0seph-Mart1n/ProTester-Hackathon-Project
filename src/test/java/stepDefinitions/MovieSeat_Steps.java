package stepDefinitions;


import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictMovieSeat;
import io.cucumber.java.en.*;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

public class MovieSeat_Steps {
	WebDriver driver;
    private DistrictMovieSeat moviePage;

    @When("The user choose {int} seats for movie {string} on date {string} at time {string}")
    public void chooseSeats(int n, String movie, String date, String time) throws InterruptedException {
        System.out.println("→ ENTERING chooseSeats: " + n + ", " + movie + ", " + date + ", " + time);
        driver = BaseClass.getDriver();
        moviePage = new DistrictMovieSeat(driver);
        moviePage.selectSeats(n, movie, date, time);
        System.out.println("→ EXITING chooseSeats");
    }

    @Then("The user should see exactly {int} seats selected")
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
