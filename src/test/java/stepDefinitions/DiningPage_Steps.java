package stepDefinitions;


import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictDiningSearch;
import io.cucumber.java.en.*;

public class DiningPage_Steps {
	private WebDriver driver;
    private DistrictDiningSearch diningPage;

    @When("The user search for café {string}")
    public void searchCafe(String cafeName) throws InterruptedException {
    	driver = BaseClass.getDriver();
    	driver.navigate().to("https://www.district.in/");
        diningPage = new DistrictDiningSearch(driver);
        diningPage.getCafeInfo(cafeName);
    }

    @Then("Print the café’s name, rate, price, time, and address to console")
    public void printCafeInfo() {
        // All printing is already inside getCafeInfo()
    	BaseClass.setDriver(driver);
    }
}
