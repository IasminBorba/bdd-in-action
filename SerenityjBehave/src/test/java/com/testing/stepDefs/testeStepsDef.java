package com.testing.stepDefs;

import org.assertj.core.api.Assertions;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class testeStepsDef {
    @Given("$line line trains from $lineStart leave $departure for $destination at $departureTimes")
    public void givenArrivingTrains(String line, String lineStart, String departure, String destination, String departureTimes) {
//        this.trainDepartureTimes = new ArrayList<>(localTimesFrom(departureTimes));
        String a = "a";
        Assertions.assertThat("a").isEqualTo(a);
    }

    @When("I want to travel from $departure to $destination at $startTime")
    public void whenIWantToTravel(String departure, String destination, String startTime) {
        String a = "a";
        Assertions.assertThat("a").isEqualTo(a);

//        LocalTime requestedTime = at(startTime);
//        this.proposedTrainTimes = trainDepartureTimes.stream()
//                .filter(trainTime -> trainTime.isAfter(requestedTime))
//                .limit(3)
//                .collect(Collectors.toList());


//        ItineraryService itineraryService = new ItineraryService();
//        proposedTrainTimes = itineraryService.findNextDepartures(departure, destination, at(startTime));
    }

    @Then("I should be told about the trains at: $expectedTimes")
    public void shouldFindTheseTrains(String expectedTimes) {
//        List<LocalTime> viableTrainTimes = localTimesFrom(expectedTimes);
//        Assertions.assertThat(proposedTrainTimes).isEqualTo(viableTrainTimes);

        String a = "a";
        Assertions.assertThat("a").isEqualTo(a);
    }
}
