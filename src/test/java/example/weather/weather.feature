Feature: weather consumer contract test

Background:
    * url baseUrl

Scenario: get weather
    * def coordinates = '53.5511,9.9937'
    Given path 'some-test-api-key', coordinates
    When method get
    Then status 200
    And match response.currently.summary == 'Rain'

