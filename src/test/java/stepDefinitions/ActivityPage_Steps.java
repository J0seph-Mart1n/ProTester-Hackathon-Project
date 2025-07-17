package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictActivitiesPage;
import com.test.resources.Activity;
import com.test.utilities.ExcelReaderWrite;

public class ActivityPage_Steps {
	
	WebDriver driver;
	DistrictActivitiesPage activities;
	List<Activity> activityInfo;
	
	@Given("the user navigates to the Activity page")
	public void open_the_activity_page() {
	    // Write code here that turns the phrase above into concrete actions
		driver = BaseClass.getDriver();
		activities = new DistrictActivitiesPage(driver);
		activities.clickActivitiesPage();
	}
	
	@When("the system fetches all activities scheduled for the weekend and sorts the activities by ascending price")
	public void getting_all_the_activities_coming_in_weekends_and_sort_in_lowest_price_order() {
	    // Write code here that turns the phrase above into concrete actions
		activityInfo = activities.getActivites();
	}
	
	@Then("display the sorted list of weekend activities to the user")
	public void print_all_the_activities_extracted() {
	    // Write code here that turns the phrase above into concrete actions
		activities.printActivites(activityInfo);
		BaseClass.setDriver(driver);
	}
	
	@Then("export the activities to an Excel file")
	public void excelWriteOperation() throws IOException {
		String excelPath  = "src/test/resources/testdata/ExcelData.xlsx";
        String sheetName  = "SortedActivities";

        // write the Activity list into the sheet
        ExcelReaderWrite.writeActivities(excelPath, sheetName, activityInfo);
        System.out.println("✅ Activities written to Excel at " + excelPath);
	}
}
