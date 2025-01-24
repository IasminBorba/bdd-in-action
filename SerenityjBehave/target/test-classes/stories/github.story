Meta:

Narrative:
As a user
I want to perform a search action in github
So that I can achieve a business goal for validating the correct user

Scenario: Search in github
Given launch github page
When search github <user>
Then validate <username>

Examples:
|user|username|
|iasminborba|Iasmin Borba|
|harish1309|S Harsih|