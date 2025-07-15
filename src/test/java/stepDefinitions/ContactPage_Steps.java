package stepDefinitions;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictContactPage;
import io.cucumber.java.en.*;

public class ContactPage_Steps {

    WebDriver driver;
    DistrictContactPage contact;

    String name, email, phone, message;

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

    @When("the user enters {string} in the name field")
    public void the_user_enters_in_the_name_field(String name) {
        this.name = name;
    }

    @When("the user enters {string} in the email field")
    public void the_user_enters_in_the_email_field(String email) {
        this.email = email;
    }

    @When("the user enters {string} in the phone field")
    public void the_user_enters_in_the_phone_field(String phone) {
        this.phone = phone;
    }

    @When("the user enters {string} in the message field")
    public void the_user_enters_in_the_message_field(String message) {
        this.message = message;
    }

    @When("the user clicks the submit button")
    public void the_user_clicks_the_submit_button() {
        contact.fillForm(name, email, phone, message);
    }

    @Then("the form should be submitted successfully")
    public void the_form_should_be_submitted_successfully() {
        // Add validation logic here, e.g., check for success message
        System.out.println("Form submitted successfully.");
        contact.goToHomePage();
    }

    @Then("the form should show validation errors")
    public void the_form_should_show_validation_errors() {
        // Add validation logic here, e.g., check for error messages
        System.out.println("Form submission failed due to validation errors.");
        BaseClass.setDriver(driver);
    }
}
