Feature: Movie Seat Selection 

 Scenario Outline: Validate seat count after selection
    When The user chooses seats for a movie
    Then The user should see exact seats selected
