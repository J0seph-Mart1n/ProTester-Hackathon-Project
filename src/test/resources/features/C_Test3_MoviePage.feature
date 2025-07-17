Feature: MoviesPage

  Scenario: Getting movie languages
    Given Open the movies page
    When Getting all the languages of movies present
    Then print all the languages extracted
    Then Write the list to Excel sheet
