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

    @When("the user clicks on the contact link")
    public void the_user_clicks_on_the_contact_link() {
        contact.clickContactLink();
    }

    @When("the user selects an option from the dropdown")
    public void the_user_selects_an_option_from_the_dropdown() {
        contact.clickChooseInput();
        contact.clickDropdownOption();
    }
    
    @When("initialize XMLParser from Utility file {string}")
    public void initializeXML(String file) throws ParserConfigurationException {
    	ReadXML xml = new ReadXML();
    	if(file.equals("ContactValidForm")) {
    		formData = xml.getContactValidData();
    	}else if(file.equals("ContactInvalidForm")) {
    		formData = xml.getContactInvalidData();
    	}
    }

    @When("the user enters {string} in the name field")
    public void the_user_enters_in_the_name_field(String name) {
        this.name = formData[0];
    }

    @When("the user enters {string} in the email field")
    public void the_user_enters_in_the_email_field(String email) {
        this.email = formData[1];
    }

    @When("the user enters {string} in the phone field")
    public void the_user_enters_in_the_phone_field(String phone) {
        this.phone = formData[2];
    }

    @When("the user enters {string} in the message field")
    public void the_user_enters_in_the_message_field(String message) {
        this.message = formData[3];
    }

    @When("the user clicks the submit button")
    public void the_user_clicks_the_submit_button() {
        contact.fillForm(name, email, phone, message);
    }

    @Then("the form should {string}")
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
