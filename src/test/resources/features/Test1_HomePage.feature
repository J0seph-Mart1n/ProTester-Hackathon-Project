Feature: HomePage

  Scenario: Change Location
    Given user is on the district home page
    When user clicks the location button
    And clicks the Pune location
    Then the location should be changed to Pune
