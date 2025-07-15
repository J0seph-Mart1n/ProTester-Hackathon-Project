package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictLogInPage;

public class LogIn_Steps {
	WebDriver driver;
	Properties prop;
	DistrictLogInPage loginAndLogout;
	
	@Given("user is on the signup page")
	public void user_is_on_the_signup_page() throws IOException {
		driver = BaseClass.getDriver();
		driver.navigate().to("https://www.district.in/");
		loginAndLogout = new DistrictLogInPage(driver);
	}

	@When("user clicks on the user profile icon")
	public void user_clicks_on_the_user_profile_icon() {
		loginAndLogout.clickOnUser();
	}

	@When("user enters mobile number from Excel")
	public void user_enters_mobile_number_from_excel() throws Exception {
		loginAndLogout.enterMobNo("9172232178");
	}

	@When("user clicks on continue")
	public void user_clicks_on_continue() {
		loginAndLogout.clickOnContinue();
	}

	@When("user enters OTP manually")
	public void user_enters_otp_manually() {
		loginAndLogout.enterOtp();
	}

	@When("user clicks on login")
	public void user_clicks_on_login() {
		loginAndLogout.clickOnLogin();
		BaseClass.setDriver(driver);
	}


}
