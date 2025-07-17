package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictSocialLinks;

public class SocialLinks_Steps {
    private WebDriver driver;
    private DistrictSocialLinks districtSocialLinks;
    
    @Given("the user navigates to the Home page")
    public void navigateToHomePage() {
    	driver = BaseClass.getDriver();
    	driver.navigate().to("https://www.district.in/");
    }
    @When("the user scrolls down to the footer")
    public void i_scroll_to_the_footer() {
        if (districtSocialLinks == null) {
            districtSocialLinks = new DistrictSocialLinks(driver);
        }
        districtSocialLinks.scrollToFooter();
    }

    @And("the user clicks the {string} icon and verifies the page title is {string}")
    public void i_click_and_verify_social_icon(String icon, String expectedTitle) {
        if (districtSocialLinks == null) {
        	districtSocialLinks = new DistrictSocialLinks(driver);
        }
        districtSocialLinks.clickAndVerifySocialIcon(icon, expectedTitle);
    }

    @Then("the user closes the social media window")
    public void i_close_the_social_media_window() {
        // Handled by clickAndVerifySocialIcon()
    	BaseClass.setDriver(driver);
    }

}
