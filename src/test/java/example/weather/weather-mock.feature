Feature: weather mock

Scenario: pathMatches('/{api-key}/{coordinates}')
    * def response = read('classpath:weatherApiResponse.json')
