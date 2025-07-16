Feature: Dining Search and Café Info
 
  Scenario: Retrieve details of a specific café
    When The user search for café "Kerala Cafe"
    Then Print the café’s name, rate, price, time, and address to console
    Then Write dining data to Excel sheet