Feature: http api integration test 
    also re-usable as mock-mvc / servlet test

Background:
    * url baseUrl

Scenario: should return hello world
    Given path 'hello'
    When method get
    Then status 200
    And match response == 'Hello World!'

Scenario: should return greeting
    Given path 'hello', 'Pan'
    When method get
    Then status 200
    And match response == 'Hello Peter Pan!'

Scenario: should return current weather
    Given path 'weather'
    When method get
    Then status 200
    And match response == 'Hamburg, 8°C raining'