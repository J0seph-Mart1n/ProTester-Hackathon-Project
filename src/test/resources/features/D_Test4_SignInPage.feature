Feature: SignIn

  Scenario: Check for SignIn fail
    Given Open User page
    When Clicked submit without filling phone number
    Then Print the error message shown
