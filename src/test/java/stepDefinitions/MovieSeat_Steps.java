//package stepDefinitions;
//
//
//import com.test.factory.BaseClass;
//import com.test.objectRepository.DistrictMovieSeat;
//import com.test.utilities.PropertyUtility;
//
//import io.cucumber.java.en.*;
//
//import static org.testng.Assert.assertEquals;
//
//import java.io.IOException;
//import java.util.Properties;
//
//import org.openqa.selenium.WebDriver;
//
//public class MovieSeat_Steps {
//	WebDriver driver;
//    private DistrictMovieSeat moviePage;
//
//    @When("The user chooses seats for a movie")
//    public void chooseSeats() throws InterruptedException, IOException {
//    	Properties p = PropertyUtility.getMovieSeatProperties();
//    	int n = Integer.parseInt(p.getProperty("seatCount"));
//    	String movie = p.getProperty("movie");
//    	String date = p.getProperty("date");
//    	String time = p.getProperty("time");
//        System.out.println("→ ENTERING chooseSeats: " + n + ", " + movie + ", " + date + ", " + time);
//        driver = BaseClass.getDriver();
//        moviePage = new DistrictMovieSeat(driver);
//        moviePage.selectSeats(n, movie, date, time);
//        System.out.println("→ EXITING chooseSeats");
//    }
//
//    @Then("The user should see exact seats selected")
//    public void verifySeatCount(int expected) {
//        String raw = moviePage.getSeatsCountText(); 
//        System.out.println("Seats‐header raw text: \"" + raw + "\"");
//        int actual = moviePage.getSelectedSeatsCount(); 
//        System.out.println("Parsed actual seat count = " + actual);
//        assertEquals(expected, actual,
//            "Expected " + expected + " seats but saw " + actual);
//        BaseClass.setDriver(driver);
//    }
//
//}

package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictMovieSeat;
import com.test.utilities.PropertyUtility;

import io.cucumber.java.en.*;

import static org.testng.Assert.*;

public class MovieSeat_Steps {
    private WebDriver driver;
    private DistrictMovieSeat moviePage;
    private Properties props;
    private int expectedSeats;

    @Given("the user is on the Home page")
    public void user_on_home_page() throws IOException {
        driver = BaseClass.getDriver();
        props = PropertyUtility.getMovieSeatProperties();
        // optionally navigate to base URL if defined
       //driver.navigateTo()
        String baseUrl = props.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isBlank()) {
        	driver.navigate().to(baseUrl);
        }
        moviePage = new DistrictMovieSeat(driver);
    }

    @When("the user clicks on the Movies menu")
    public void user_clicks_movies_menu() {
    	
        moviePage.openMoviesSection();
    }

    @When("the user selects the movie from the property file")
    public void user_selects_movie_from_property_file() {
        String movie = props.getProperty("movie");
        moviePage.selectMovie(movie);
    }

    @When("the user selects the date from the property file")
    public void user_selects_date_from_property_file() {
        String date = props.getProperty("date");
        moviePage.selectDate(date);
    }

    @When("the user selects the time slot from the property file")
    public void user_selects_time_slot_from_property_file() {
        String time = props.getProperty("time");
        moviePage.selectTimeSlot(time);
    }

    @When("the user selects the number of seats defined in the property file")
    public void user_selects_number_of_seats_from_property_file() {
        expectedSeats = Integer.parseInt(props.getProperty("seatCount"));
        boolean success = moviePage.selectRandomSeats(expectedSeats);
        assertTrue(success, "Failed to select the expected number of seats");
    }

    @Then("the user should see the same number of seats selected as defined in the property file")
    public void user_should_see_same_number_of_seats() {
        int actualSeats = moviePage.getSelectedSeatsCount();
        assertEquals(actualSeats, expectedSeats,
            "The number of selected seats does not match the expected value");
        BaseClass.setDriver(driver);
    }
}
