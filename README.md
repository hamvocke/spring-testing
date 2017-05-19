# Testing Microservices in Spring
This repository contains a *Spring Boot* application with lots of exemplary tests on different levels of the [Test Pyramid](https://martinfowler.com/bliki/TestPyramid.html). It shows an opinionated way to thoroughly test your spring application by demonstrating different types and levels of testing. You will find that some of the tests are duplicated along the test pyramid -- concepts that have already been tested in lower-level tests will be tested in more high-level tests. This contradicts the premise of the test pyramid. In this case it helps showcasing different kinds of tests which is the main goal of this repository.

## Application Architecture

```
 +--------+      +----------+      +----------+
 |   ☁    |  ←→  |    ☕     |  ←→  |    💾    |
 |  HTTP  |      |  Spring  |      | Database |
 +--------+      |  Service |      +----------+
                 +----------+
                      ↑
                      ↓
                 +----------+
                 |    ☁     |
                 | Weather  |
                 |   API    |
                 +----------+
```

The sample application is almost as easy as it gets. It stores `Person`s in an in-memory database (using _Spring Data_) and provides a _REST_ interface with three endpoints:

  * _GET_ `/hello`: Returns _"Hello World!"_. Always.
  * _GET_ `/hello/{lastname}`: Looks up the person with `lastname` as its last name and returns _"Hello {Firstname} {Lastname}"_ if that person is found.
  * _GET_ `/weather`: Calls a downstream [weather API](https://darksky.net) via HTTP and returns a summary for the current weather conditions in Hamburg, Germany

The **Spring Service** itself has a pretty common internal architecture:

  * `Controller` classes provide _REST_ endpoints and deal with _HTTP_ requests and responses
  * `Repository` classes interface with the _database_ and take care of writing and reading data to/from persistent storage
  * `Client` classes talk to other APIs, in our case it fetches _JSON_ via _HTTP_ from the darksky.net weather API

## Test Layers

```

    ☁      ←→     ☕       ←→      💾
   HTTP         Spring         Database
                Service

  |       HTTP      ||    Database     |
  |__ Integration __||__ Integration __|
  |                                    |
  |___________ Acceptance _____________|               
```


## Tools
You can find lots of different tools, frameworks and libraries being used in the different examples:

  * **Spring Boot**: application framework
  * **JUnit**: test runner
  * **Hamcrest Matchers**: assertions
  * **Mockito**: test doubles (mocks, stubs)
  * **RestAssured**: testing the service end to end via HTTP
  * **Wiremock**: provide HTTP stubs for downstream services

## Get started
In order to run the service, you need to set the `WEATHER_API_KEY` environment variable. A simple way is to rename the `env.sample` file to `.env`, fill in your API key from `darksky.net` and source it before running your application using `source .env`.
