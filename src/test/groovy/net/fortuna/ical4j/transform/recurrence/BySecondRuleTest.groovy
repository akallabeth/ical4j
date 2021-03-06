package net.fortuna.ical4j.transform.recurrence

import net.fortuna.ical4j.model.DateList
import net.fortuna.ical4j.model.DateTime
import net.fortuna.ical4j.model.NumberList
import net.fortuna.ical4j.model.parameter.Value
import spock.lang.Specification

import static net.fortuna.ical4j.model.Recur.Frequency.DAILY
import static net.fortuna.ical4j.model.Recur.Frequency.SECONDLY

class BySecondRuleTest extends Specification {

    def 'verify transformations by day'() {
        given: 'a BYSECOND rule'
        BySecondRule rule = [new NumberList(rulePart), frequency]

        and: 'a list of dates'
        DateList dateList = [Value.DATE_TIME]
        dateList.addAll(dates)

        expect: 'the rule transforms the dates correctly'
        rule.transform(dateList) == expectedResult

        where:
        rulePart | frequency       | dates                              | expectedResult
        '1'      | DAILY    | [new DateTime('20150103T000000Z')] | [new DateTime('20150103T000001Z')]
        '1'      | SECONDLY | [new DateTime('20150103T000001Z'), new DateTime('20150103T000002Z')] | [new DateTime('20150103T000001Z')]
    }
}
