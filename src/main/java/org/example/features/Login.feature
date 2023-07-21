Feature: Login feature

  @SmokeTest
  Scenario Outline: Successful login
    Given I am on the login page
    When I enter the username "<username>" and password "<password>"
    Then I should see the products page with title "<titleName>"

    Examples:
      | username      | password     | titleName |
      | standard_user | secret_sauce | Products  |
      | user2         | secret       | Products  |
