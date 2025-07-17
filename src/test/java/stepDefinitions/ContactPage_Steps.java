package stepDefinitions;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictContactPage;
import com.test.utilities.ReadXML;

import io.cucumber.java.en.*;

public class ContactPage_Steps {

    WebDriver driver;
    DistrictContactPage contact;

    String name, email, phone, message;
    String[] formData;

    @Given("the user is on the homepage2")
    public void the_user_is_on_the_homepage2() {
        driver = BaseClass.getDriver();
        contact = new DistrictContactPage(driver);
        System.out.println("User is on homepage2");
    }

    @When("the user scrolls to the bottom of the page")
    public void the_user_scrolls_to_the_bottom_of_the_page() {
        contact.scrollToBottom();
    }

    @And("the user clicks on the Contact link")
    public void the_user_clicks_on_the_contact_link() {
        contact.clickContactLink();
    }

    @And("the user selects an option from the contact dropdown")
    public void the_user_selects_an_option_from_the_dropdown() {
        contact.clickChooseInput();
        contact.clickDropdownOption();
    }
    
    @And("the user initializes the XMLParser using utility file {string}")
    public void initializeXML(String file) throws ParserConfigurationException {
    	ReadXML xml = new ReadXML();
    	if(file.equals("ContactValidForm")) {
    		formData = xml.getContactValidData();
    	}else if(file.equals("ContactInvalidForm")) {
    		formData = xml.getContactInvalidData();
    	}
    }

    @And("the user enters {string} into the Name field")
    public void the_user_enters_in_the_name_field(String name) {
        this.name = formData[0];
    }

    @And("the user enters {string} into the Email field")
    public void the_user_enters_in_the_email_field(String email) {
        this.email = formData[1];
    }

    @And("the user enters {string} into the Phone field")
    public void the_user_enters_in_the_phone_field(String phone) {
        this.phone = formData[2];
    }

    @And("the user enters {string} into the Message field")
    public void the_user_enters_in_the_message_field(String message) {
        this.message = formData[3];
    }

    @And("the user clicks the Submit button")
    public void the_user_clicks_the_submit_button() {
        contact.fillForm(name, email, phone, message);
    }

    @And("the form should return {string}")
    public void the_form_should_be_submitted_successfully(String result) {
        // Add validation logic here, e.g., check for success message
        System.out.println("Form submitted successfully.");
        contact.goToHomePage();
        BaseClass.setDriver(driver);
    }

    @Then("the form should show validation errors")
    public void the_form_should_show_validation_errors() {
        // Add validation logic here, e.g., check for error messages
        System.out.println("Form submission failed due to validation errors.");
        
    }
}
