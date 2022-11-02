Feature: Simulations

  @AddSimulation
  Scenario: add a new simulation successfully
    Given I create a new simulation with "<instalments>" "<value>" "<insurance>" with "POST" verb
    When calls "addSimulationAPI" with "POST" verb
    Then the API call got success with status 201
    And returned the new simulation


