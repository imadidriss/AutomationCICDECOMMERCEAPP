

@tag
Feature: Error validation
  I want to use this template for my feature file

 Background:
  Given I landed on Ecommerce Page

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When Logged in width username <name> and  password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name                         | password    |
      | alexandreboireau171@gmail.com| lkedfgh7    |