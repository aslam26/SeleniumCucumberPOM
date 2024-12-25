Feature: Login Functionality

  @Valid
  Scenario: Successful login with valid credentials
    Given I am at login page
    When I enter 'standard_user' and 'secret_sauce' and click login button
    Then home page url 'https://www.saucedemo.com/inventory.html' should be displayed

  @Invalid
  Scenario: Login fails when enter invalid credentials
    Given I am at login page
    When I enter 'standarduser' and 'secretsauce' and click login button
    Then an error message 'Epic sadface: Username and password do not match any user in this service' should be displayed

  @EmptyCredentials
  Scenario: Login with empty username and password
    Given I am at login page
    When I enter '' and '' and click login button
    Then an error message 'Epic sadface: Username i required' should be displayed
