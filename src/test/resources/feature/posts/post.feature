Feature: dogs end-point that uses jdbc as part of the test

Background:
* url demoBaseUrl
* configure logPrettyRequest = true
* configure logPrettyResponse = true

Scenario: create and retrieve a post
  * json reqBody = read('classpath:feature/common/post-request.json')

# create a dog
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