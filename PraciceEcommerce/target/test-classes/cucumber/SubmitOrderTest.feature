@tag
Feature: Purchase order from Ecommerce website

Background:
Given I landed on Ecommerce Page

  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username<username> and password<password>
    When I add product<productName> to cart
    And checkout<productName>and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | username               | password         | productName    |
      | reshma98@gmail.com     |     Rs1234!@#$   | ZARA COAT 3     |
    
