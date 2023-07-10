Feature: Amazon Product Search
  Scenario: Verify product details
    Given I am on the Amazon homepage
    When I search for "iphone 14"
    And I click on a product
    Then I verify the product details
