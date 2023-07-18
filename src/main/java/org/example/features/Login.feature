Feature: Login feature

  Scenario Outline: Successful login
    Given I am on the login page
    When I enter the username "<username>" and password "<password>"
    And I click the login button
    Then I should see the products page with title "<titleName>"

    Examples:
      | username      | password     | titleName |
      | standard_user | secret_sauce | Products  |
      | user2         | pass2        | Inventory |
      | user3         | pass3        | Shop      |