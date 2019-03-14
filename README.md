# Testing Microservices in Spring

[![Build Status](https://travis-ci.org/hamvocke/spring-testing.svg?branch=master)](https://travis-ci.org/hamvocke/spring-testing)

This repository contains a *Spring Boot* application with lots of exemplary tests on different levels of the [Test Pyramid](https://martinfowler.com/bliki/TestPyramid.html). It shows an opinionated way to thoroughly test your spring application by demonstrating different types and levels of testing. You will find that some of the tests are duplicated along the test pyramid -- concepts that have already been tested in lower-level tests will be tested in more high-level tests. This contradicts the premise of the test pyramid. In this case it helps showcasing different kinds of tests which is the main goal of this repository.

## Read the Blog Post
This repository is part of a [series of blog posts](http://www.hamvocke.com/blog/testing-microservices/) I wrote about testing microservices. I highly recommend you read them to get a better feeling for what it takes to test microservices and how you can implement a reliable test suite for a Spring Boot microservice application.

## Get started

### 1. Set an API Key as Environment Variable
In order to run the service, you need to set the `WEATHER_API_KEY` environment variable to a valid API key retrieved from [darksky.net](http://darksky.net).

A simple way is to rename the `env.sample` file to `.env`, fill in your API key from `darksky.net` and source it before running your application:

```bash
source .env
```

### 2. Start a PostgreSQL database
The easiest way is to use the provided `startDatabase.sh` script. This script starts a Docker container which contains a database with the following configuration:
    
  * port: `15432`
  * username: `postgres`
  * password: `password`
  * database name: `postgres`
  
If you don't want to use the script make sure to have a database with the same configuration or modify your `application.properties`.

### 3. Run the Application
Once you've provided the API key and started a PostgreSQL database you can run the application using

```bash
./gradlew bootRun
```

The application will start on port `8080` so you can send a sample request to `http://localhost:8080/hello` to see if you're up and running.


## Application Architecture

```
 ╭┄┄┄┄┄┄┄╮      ┌──────────┐      ┌──────────┐
 ┆   ☁   ┆  ←→  │    ☕     │  ←→  │    💾     │
 ┆  Web  ┆ HTTP │  Spring  │      │ Database │
 ╰┄┄┄┄┄┄┄╯      │  Service │      └──────────┘
                └──────────┘
                     ↑ JSON/HTTP
                     ↓
                ┌──────────┐
                │    ☁     │
                │ Weather  │
                │   API    │
                └──────────┘
```

The sample application is almost as easy as it gets. It stores `Person`s in an in-memory database (using _Spring Data_) and provides a _REST_ interface with three endpoints:

  * `GET /hello`: Returns _"Hello World!"_. Always.
  * `GET /hello/{lastname}`: Looks up the person with `lastname` as its last name and returns _"Hello {Firstname} {Lastname}"_ if that person is found.
  * `GET /weather`: Calls a downstream [weather API](https://darksky.net) via HTTP and returns a summary for the current weather conditions in Hamburg, Germany

### Internal Architecture
The **Spring Service** itself has a pretty common internal architecture:

  * `Controller` classes provide _REST_ endpoints and deal with _HTTP_ requests and responses
  * `Repository` classes interface with the _database_ and take care of writing and reading data to/from persistent storage
  * `Client` classes talk to other APIs, in our case it fetches _JSON_ via _HTTP_ from the darksky.net weather API


  ```
  Request  ┌────────── Spring Service ───────────┐
   ─────────→ ┌─────────────┐    ┌─────────────┐ │   ┌─────────────┐
   ←───────── │  Controller │ ←→ │  Repository │←──→ │  Database   │
  Response │  └─────────────┘    └─────────────┘ │   └─────────────┘
           │         ↓                           │
           │    ┌──────────┐                     │
           │    │  Client  │                     │
           │    └──────────┘                     │
           └─────────│───────────────────────────┘
                     │
                     ↓   
                ┌──────────┐
                │    ☁     │
                │ Weather  │
                │   API    │
                └──────────┘
  ```  

## Test Layers
The example applicationn shows different test layers according to the [Test Pyramid](https://martinfowler.com/bliki/TestPyramid.html).

```
      ╱╲
  End-to-End
    ╱────╲
   ╱ Inte-╲
  ╱ gration╲
 ╱──────────╲
╱   Unit     ╲
──────────────
```

The base of the pyramid is made up of unit tests. They should make the biggest part of your automated test suite.

The next layer, integration tests, test all places where your application serializes or deserializes data. Your service's REST API, Repositories or calling third-party services are good examples. This codebase contains example for all of these tests.

```
 ╭┄┄┄┄┄┄┄╮      ┌──────────┐      ┌──────────┐
 ┆   ☁   ┆  ←→  │    ☕     │  ←→  │    💾     │
 ┆  Web  ┆      │  Spring  │      │ Database │
 ╰┄┄┄┄┄┄┄╯      │  Service │      └──────────┘
                └──────────┘

  │    Controller     │      Repository      │
  └─── Integration ───┴──── Integration ─────┘

  │                                          │
  └────────────── Acceptance ────────────────┘               
```

```
 ┌─────────┐  ─┐
 │    ☁    │   │
 │ Weather │   │
 │   API   │   │
 │  Stub   │   │
 └─────────┘   │ Client
      ↑        │ Integration
      ↓        │ Test
 ┌──────────┐  │
 │    ☕     │  │
 │  Spring  │  │
 │  Service │  │
 └──────────┘ ─┘
```

## Tools
You can find lots of different tools, frameworks and libraries being used in the different examples:

  * **Spring Boot**: application framework
  * **JUnit**: test runner
  * **Hamcrest Matchers**: assertions
  * **Mockito**: test doubles (mocks, stubs)
  * **MockMVC**: testing Spring MVC controllers
  * **RestAssured**: testing the service end to end via HTTP
  * **Wiremock**: provide HTTP stubs for downstream services

