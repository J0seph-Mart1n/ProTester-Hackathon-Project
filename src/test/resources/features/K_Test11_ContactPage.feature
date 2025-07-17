Feature: Contact Form Submission

  Scenario Outline: User submits the contact form with various inputs
    Given the user is on the homepage2
    When the user scrolls to the bottom of the page
    And the user clicks on the contact link
    And the user selects an option from the dropdown
    And initialize XMLParser from Utility file "<file>"
    And the user enters "name" in the name field
    And the user enters "email" in the email field
    And the user enters "phone" in the phone field
    And the user enters "message" in the message field
    And the user clicks the submit button
    Then the form should "result"

    Examples: 
      | file               |
      | ContactValidForm   |
      | ContactInvalidForm |
