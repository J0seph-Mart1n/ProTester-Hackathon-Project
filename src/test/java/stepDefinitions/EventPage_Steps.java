package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictEventsPage;
import com.test.objectRepository.DistrictSocialLinks;

import java.util.List;

public class EventPage_Steps {
    private WebDriver driver;
    private DistrictEventsPage eventsPage;
    private List<DistrictEventsPage.Event> sortedEvents;
    private DistrictSocialLinks districtSocialLinks;

    
    @When("The user navigate to the Events tab")
    public void i_navigate_to_the_events_tab() {
    	driver = BaseClass.getDriver();
        districtSocialLinks = new DistrictSocialLinks(driver);
        driver.navigate().to("https://www.district.in/");
        districtSocialLinks.goToEvents();
    }
    
    @When("The user scroll until all events are loaded")
    public void i_scroll_until_all_events_are_loaded() throws InterruptedException {
        eventsPage = new DistrictEventsPage(driver);
        eventsPage.scrollToEnd();
    }

    @When("The user fetch and sort events by price")
    public void i_fetch_and_sort_events_by_price() throws InterruptedException {
        if (eventsPage == null) {
            eventsPage = new DistrictEventsPage(driver);
        }
        sortedEvents = eventsPage.fetchAndSortByPrice();
    }

    @Then("Print the sorted event list")
    public void i_print_the_sorted_event_list() {
        sortedEvents.forEach(System.out::println);
        BaseClass.setDriver(driver);
    }

}
