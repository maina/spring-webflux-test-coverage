Feature: posts end-point that tests posts crud and clean up the db after scenarios

Background:
* url demoBaseUrl
* configure logPrettyRequest = true
* configure logPrettyResponse = true

# the JSON returned from 'karate.info' has the following properties:
#   - featureDir
#   - featureFileName
#   - scenarioName
#   - scenarioType (either 'Scenario' or 'Scenario Outline')
#   - scenarioDescription
#   - errorMessage (will be not-null if the Scenario failed)

* configure afterScenario =
"""
function(){
  var info = karate.info;
  karate.log('after', info.scenarioType + ':', info.scenarioName);
  karate.call('classpath:feature/common/features/posts-after-scenario.feature', { caller: info.featureFileName });
}
"""

Scenario: create and retrieve a post
* json reqBody = read('classpath:feature/common/post-request.json')

# create a post
Given path 'posts'
And set reqBody.title = 'sample post'
And set reqBody.body = 'sample post body'
And request reqBody
When method post
Then status 201
And match response == { id: '#number',"userId": '#number',"createdDate": "#string","lastUpdatedDate": null, title: 'sample post', body: 'sample post body' }

* def id = response.id

# get by id
Given path 'posts', id
When method get
Then status 200
And match response == { id: '#number',"userId": '#number',"createdDate": "#string","lastUpdatedDate": null, title: 'sample post', body: 'sample post body' }

# get all posts
Given path 'posts'
When method get
Then status 200
And match response contains { id: '#number',"userId": '#number',"createdDate": "#string","lastUpdatedDate": null, title: 'sample post', body: 'sample post body' }