package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;

import com.test.objectRepository.DistrictHomePage;
import com.test.utilities.DriverSetup;

public class HomePage_Steps {
	
	WebDriver driver;
	DistrictHomePage home;
	
	@Given("user is on the district home page")
	public void user_is_on_the_district_home_page() {
	    // Write code here that turns the phrase above into concrete actions
		driver = DriverSetup.driver;
	    home = new DistrictHomePage(driver);
	}
	
	@When("user clicks the location button")
	public void user_clicks_the_location_button() {
	    // Write code here that turns the phrase above into concrete actions
	    home.clickLocation();
	}
	
	@When("clicks the Pune location")
	public void clicks_the_pune_location() {
	    // Write code here that turns the phrase above into concrete actions
	    home.clickPune();
	}
	
	@Then("the location should be changed to Pune")
	public void the_location_should_be_changed_to_pune() {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("Location changed to Pune");
	    DriverSetup.setDriver(driver);
	}
}
