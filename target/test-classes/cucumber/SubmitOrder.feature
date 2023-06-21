@tag
Feature: Purchase order for Ecommerce Website
  I want to use this template for my feature file
  
  Background: 
  Given I landed on Ecommerce Page

  @tag1
  Scenario Outline: Positive Test of Submitting the order
    Given Login with Username<name> and password <Password>
    When  I add the product <ProductName> from cart
    And   Checkout <Productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage
   


    Examples: 
      | name                  |     Password       |    ProductName   |
      | swathiswats0@gmail.com|     Amsani@801     |    ZARA COAT 3   |
      
      
