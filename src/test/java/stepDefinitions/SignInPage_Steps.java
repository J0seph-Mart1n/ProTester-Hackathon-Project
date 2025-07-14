package stepDefinitions;

import org.openqa.selenium.WebDriver;

import com.test.objectRepository.DistrictSignInPage;
import com.test.utilities.DriverSetup;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignInPage_Steps {
	
	WebDriver driver;
	DistrictSignInPage signIn;
	
	@Given("Open User page")
	public void open_user_page() {
	    // Write code here that turns the phrase above into concrete actions
		driver = DriverSetup.driver;
	    signIn = new DistrictSignInPage(driver);
	    signIn.clickUserIcon();
	}
	
	@When("Clicked submit without filling phone number")
	public void clicked_submit_without_filling_phone_number() {
	    // Write code here that turns the phrase above into concrete actions
	    signIn.clickSubmitBtn();
	}
	
	@Then("Print the error message shown")
	public void print_the_error_message_shown() {
	    // Write code here that turns the phrase above into concrete actions
	    signIn.validateSignIn();
	}
}
