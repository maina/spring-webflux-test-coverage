@ignore
Feature: Posts Database cleaning
Background:
* url demoBaseUrl
* configure logPrettyRequest = true
* configure logPrettyResponse = true
Scenario: clean the database before/after each scenario begins
#Call db clean up endpoint
  Given path 'karate-tests/posts-after-scenario'
  * print '>>>>>>>>>cleandb'
  And request {}
  When method delete
  Then status 204