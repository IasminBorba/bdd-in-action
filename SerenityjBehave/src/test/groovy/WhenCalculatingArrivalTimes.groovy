import spock.lang.Specification

class WhenCalculatingArrivalTimes extends Specification {
    def "should calculate the sum of two numbers"() {
        given: "two numbers"
        int a = 1
        int b = 2
        when: "we add the numbers together"

        int sum = a+b
        then: "the result should be the sum of the two numbers"
        sum == 3
    }
}
