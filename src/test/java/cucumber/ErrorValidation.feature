@tag
Feature: Error Validation
  I want to use this template for my feature file

 
  @ErrorValidation
  Scenario Outline: Positive Test of Submitting the order
  
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed on LoginPage

    Examples:
      | name                       | password    | productName |
      | sravani.chegu003@gmail.com | Ludwik2017 | ZARA COAT 3 |