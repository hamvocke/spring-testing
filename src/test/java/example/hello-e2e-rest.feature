Feature: e2e rest

Background:
    * url 'http://localhost:' + karate.properties['karate.server.port']

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