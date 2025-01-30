Meta:
Tell travellers when they will arrive at their destination

Narrative:
In order to plan my voyage more effectively
As a commuter
I want to know what time I will arrive at my destination

Scenario: Calculate arrival times
Given I want to go from <departure> to <destination>
And the next train leaves at <departureTime> on <line> line
When I ask for my arrival time
Then the estimated arrival time should be <arrivalTime>

Examples:
|departure|destination|departureTime|line|arrivalTime
|Parramatta|Town Hall|08:02|Western|08:29
|Epping|Central|08:03|Northern|08:48
|Epping|Central|08:07|Newcastle|08:37
|Epping|Central|08:13|Epping|08:51
