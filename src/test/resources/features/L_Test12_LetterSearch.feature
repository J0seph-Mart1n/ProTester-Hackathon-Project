Feature: Letter-Based Search Interaction

  Scenario: User performs a letter-based search and views results
    Given the user is on the homepage
    When the user waits for the page to load
    And the user clicks on the menu button
    And the user hovers over the search section
    And the user scrolls to the letter search area
    And the user clicks on the letter "A"
    Then the user should see the results for letter "A"
    And the user prints all result buttons
