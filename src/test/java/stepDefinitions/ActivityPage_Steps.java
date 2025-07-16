package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictActivitiesPage;
import com.test.resources.Activity;
import com.test.utilities.DriverSetup;

public class ActivityPage_Steps {
	
	WebDriver driver;
	DistrictActivitiesPage activities;
	List<Activity> activityInfo;
	
	@Given("Open the activity page")
	public void open_the_activity_page() {
	    // Write code here that turns the phrase above into concrete actions
		driver = BaseClass.getDriver();
		activities = new DistrictActivitiesPage(driver);
		activities.clickActivitiesPage();
	}
	
	@When("Getting all the activities coming in weekends and sort in lowest price order")
	public void getting_all_the_activities_coming_in_weekends_and_sort_in_lowest_price_order() {
	    // Write code here that turns the phrase above into concrete actions
		activityInfo = activities.getActivites();
	}
	
	@Then("Print all the activities extracted")
	public void print_all_the_activities_extracted() {
	    // Write code here that turns the phrase above into concrete actions
		activities.printActivites(activityInfo);
		BaseClass.setDriver(driver);
	}
}
