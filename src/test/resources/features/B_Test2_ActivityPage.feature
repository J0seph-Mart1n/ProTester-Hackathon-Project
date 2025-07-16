Feature: ActivityPage

  Scenario: Getting Activities
    Given Open the activity page
    When Getting all the activities coming in weekends and sort in lowest price order
    Then Print all the activities extracted
    Then Write all activities in Excel sheet
