package stepDefinitions;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictLogInPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogOut_Steps {
	WebDriver driver;
	Properties prop;
	DistrictLogInPage loginAndLogout;
	
	@When("user clicks on the user profile icon again")
	public void user_clicks_on_the_user_profile_icon_again() {
//		int cnt=0;
//		if(loginAndLogout.isUserLoggedIn()) {
//			loginAndLogout.clickOnUser();
//			
//		}
//		else {
//			while(!loginAndLogout.isUserLoggedIn() && cnt<2) {
//				loginAndLogout.enterOtp();
//				loginAndLogout.clickOnLogin();
//				cnt++;
//			}
//		}
		driver = BaseClass.getDriver();
		loginAndLogout = new DistrictLogInPage(driver);
		loginAndLogout.clickOnUser();
	}
 
	@Then("user clicks on logout")
	public void user_clicks_on_logout() {
		loginAndLogout.clickOnLogout();
		BaseClass.setDriver(driver);
	}
}
