Feature: Basic search flow
  As a MarsAir Sales Director (Mark), I want potential customers to be able to search for flights to Mars
  So that they see what trips are available


  Scenario: Test id 1 - Check the UI of the search form
    When Go to the MarsAir website
    Then There should be departure and return fields on a search form
    And Flights leave every six months, in July and December, both ways
    And Close browser

  Scenario: Test id 2 - Verify searching with valid departing and returning to get search result
    Given Go to the MarsAir website
    When Select a time for departing and returning
    And Click on "Search" button
    Then Verify that resulted searching should be displayed
    And Close browser




