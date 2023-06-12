@Smoke
Feature: Smoke Age Determination

  Scenario: User gets correct age result for random date of birth
    Given I am on the age determination website
    When I type random user name
    And I type random date of birth
    And I click submit button
    Then I see the correct age result
