package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictSocialLinks;

public class SocialLinks_Steps {
    private WebDriver driver;
    private DistrictSocialLinks districtSocialLinks;

    
    @When("The user scroll to the footer")
    public void i_scroll_to_the_footer() {
    	driver = BaseClass.getDriver();
    	driver.navigate().to("https://www.district.in/");
        if (districtSocialLinks == null) {
            districtSocialLinks = new DistrictSocialLinks(driver);
        }
        districtSocialLinks.scrollToFooter();
    }

    @When("The user click the {string} icon and verify title {string}")
    public void i_click_and_verify_social_icon(String icon, String expectedTitle) {
        if (districtSocialLinks == null) {
        	districtSocialLinks = new DistrictSocialLinks(driver);
        }
        districtSocialLinks.clickAndVerifySocialIcon(icon, expectedTitle);
    }

    @Then("The user close the social media window")
    public void i_close_the_social_media_window() {
        // Handled by clickAndVerifySocialIcon()
    	BaseClass.setDriver(driver);
    }

}
