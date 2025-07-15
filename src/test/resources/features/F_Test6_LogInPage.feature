Feature: User Login Flow
  
    Scenario: User logs in successfully
      Given user is on the signup page
  		  When user clicks on the user profile icon
      And user enters mobile number from Excel
      And user clicks on continue
      And user enters OTP manually
      And user clicks on login
