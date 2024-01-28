package com.rakitin.idea_platform.time_the_flight.calculate;

import com.rakitin.idea_platform.time_the_flight.pojo.FlightInfoPOJO;
import com.rakitin.idea_platform.time_the_flight.reader.TicketsFromJsonReader;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

public class CalculateFlightTime {
    private static final List<FlightInfoPOJO> tickets = TicketsFromJsonReader.getFlightInfo();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:m dd.MM.yy z");


    private static List<ZonedDateTime> convertDepartureTimeToZDT() {
        List<String> departureTimeList = tickets.stream()
                .map(flightInfoPOJO -> flightInfoPOJO.getDepartureTime()
                        + " " + flightInfoPOJO.getDepartureDate()
                        + " Asia/Vladivostok").toList();

        return departureTimeList.stream().map(s -> ZonedDateTime.parse(s, formatter)).toList();

    }

    private static List<ZonedDateTime> convertArrivalTimeToZDT() {

        List<String> arrivalTimeList = tickets.stream()
                .map(flightInfoPOJO -> flightInfoPOJO.getArrivalTime()
                        + " " + flightInfoPOJO.getArrivalDate()
                        + " Asia/Jerusalem").toList();

        return arrivalTimeList.stream().map(s -> ZonedDateTime.parse(s, formatter)).toList();
    }

    public static Duration getMinFlightTime() {
        List<ZonedDateTime> arrivalZonedDateTimeList = convertArrivalTimeToZDT();
        List<ZonedDateTime> departureZonedDateTimeList = convertDepartureTimeToZDT();

        List<Duration> durations = IntStream.range(0, arrivalZonedDateTimeList.size())
                .mapToObj(i -> Duration.between(departureZonedDateTimeList.get(i), arrivalZonedDateTimeList.get(i)))
                .sorted().toList();

        return durations.getFirst();


    }

}
