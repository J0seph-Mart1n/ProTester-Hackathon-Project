package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictLogInPage;
import com.test.utilities.PropertyUtility;

public class LogIn_Steps {
	WebDriver driver;
	Properties prop;
	DistrictLogInPage loginAndLogout;
	
	@Given("the user is on the Sign-Up page")
	public void user_is_on_the_signup_page() throws IOException {
		driver = BaseClass.getDriver();
		driver.navigate().to("https://www.district.in/");
		loginAndLogout = new DistrictLogInPage(driver);
	}

	@When("the user clicks the profile icon")
	public void user_clicks_on_the_user_profile_icon() {
		loginAndLogout.clickOnUser();
	}

	@When("the user enters the mobile number from the Excel sheet")
	public void user_enters_mobile_number_from_excel() throws Exception {
		Properties p = PropertyUtility.getLogInProperties();
		String number = p.getProperty("mobileNo");
		loginAndLogout.enterMobNo(number);
	}

	@When("the user clicks Continue")
	public void user_clicks_on_continue() {
		loginAndLogout.clickOnContinue();
	}

	@When("the user enters the OTP manually")
	public void user_enters_otp_manually() {
		loginAndLogout.enterOtp();
	}

	@When("the user clicks Login")
	public void user_clicks_on_login() {
		loginAndLogout.clickOnLogin();
		BaseClass.setDriver(driver);
	}


}
