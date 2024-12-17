

@tag
Feature: Purchase the order  from Ecomerce Website
  I want to use this template for my feature file
  Background:
  Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in width username <name> and  password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "TANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name                         | password    | productName |
      | alexandreboireau171@gmail.com| lkedfgh7B   | ZARA COAT 3 |
      
