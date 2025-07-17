package stepDefinitions;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictSignInPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignInPage_Steps {
	
	WebDriver driver;
	DistrictSignInPage signIn;
	
	@Given("the user opens the Sign-In page")
	public void open_user_page() {
	    // Write code here that turns the phrase above into concrete actions
		driver = BaseClass.getDriver();
	    signIn = new DistrictSignInPage(driver);
	    signIn.clickUserIcon();
	}
	
	@When("the user clicks the submit button without entering a phone number")
	public void clicked_submit_without_filling_phone_number() {
	    // Write code here that turns the phrase above into concrete actions
	    signIn.clickSubmitBtn();
	}
	
	@Then("the system displays an error message indicating that the phone number is required")
	public void print_the_error_message_shown() {
	    // Write code here that turns the phrase above into concrete actions
	    signIn.validateSignIn();
	}
}
