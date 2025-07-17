Feature: Fetch and sort events by price

  Scenario: List Pune events sorted by ascending price
    When The user navigate to the Events tab
    And The user scroll until all events are loaded
    And The user fetch and sort events by price
    Then Print the sorted event list
    And The user writes sorted events to Excel
