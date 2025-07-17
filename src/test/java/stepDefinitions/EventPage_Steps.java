package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictEventsPage;
import com.test.objectRepository.DistrictSocialLinks;
import com.test.utilities.ExcelReaderWrite;

import java.io.IOException;
import java.util.List;

public class EventPage_Steps {
    private WebDriver driver;
    private DistrictEventsPage eventsPage;
    private List<DistrictEventsPage.Event> sortedEvents;
    private DistrictSocialLinks districtSocialLinks;

    
    @When("the user navigates to the Events tab")
    public void i_navigate_to_the_events_tab() {
    	driver = BaseClass.getDriver();
        districtSocialLinks = new DistrictSocialLinks(driver);
        driver.navigate().to("https://www.district.in/");
        districtSocialLinks.goToEvents();
    }
    
    @And("the user scrolls until all events are loaded")
    public void i_scroll_until_all_events_are_loaded() throws InterruptedException {
        eventsPage = new DistrictEventsPage(driver);
        eventsPage.scrollToEnd();
    }

    @And("the user fetches and sorts the events by ascending price")
    public void i_fetch_and_sort_events_by_price() throws InterruptedException {
        if (eventsPage == null) {
            eventsPage = new DistrictEventsPage(driver);
        }
        sortedEvents = eventsPage.fetchAndSortByPrice();
    }

    @Then("print the sorted list of events")
    public void i_print_the_sorted_event_list() {
        sortedEvents.forEach(System.out::println);
        BaseClass.setDriver(driver);
    }
    
    @And("write the sorted events to an Excel sheet")
    public void the_user_writes_sorted_events_to_excel() throws IOException {
        // define relative path & sheet name
        String excelPath  = "src/test/resources/testdata/ExcelData.xlsx";
        String sheetName  = "SortedEvents";

        // write the sortedEvents list into the sheet
        ExcelReaderWrite.writeEvents(excelPath, sheetName, sortedEvents);
        System.out.println("✅ Events written to Excel at " + excelPath);
    }

}
