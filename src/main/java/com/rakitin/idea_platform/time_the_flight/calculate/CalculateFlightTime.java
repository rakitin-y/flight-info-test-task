package com.rakitin.idea_platform.time_the_flight.calculate;

import com.rakitin.idea_platform.time_the_flight.pojo.FlightInfoPOJO;
import com.rakitin.idea_platform.time_the_flight.validation.ValidateTickets;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class CalculateFlightTime {
    private static final Map<String, List<FlightInfoPOJO>> tickets = ValidateTickets.getTicketsForEachCarrier();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:m dd.MM.yy z");


    private static List<ZonedDateTime> convertDepartureTimeToZDT(List<FlightInfoPOJO> tickets) {
        List<String> departureTimeList = tickets.stream()
                .map(flightInfoPOJO -> flightInfoPOJO.getDepartureTime()
                        + " " + flightInfoPOJO.getDepartureDate()
                        + " Asia/Vladivostok").toList();

        return departureTimeList.stream().map(s -> ZonedDateTime.parse(s, formatter)).toList();

    }

    private static List<ZonedDateTime> convertArrivalTimeToZDT(List<FlightInfoPOJO> tickets) {

        List<String> arrivalTimeList = tickets.stream()
                .map(flightInfoPOJO -> flightInfoPOJO.getArrivalTime()
                        + " " + flightInfoPOJO.getArrivalDate()
                        + " Asia/Jerusalem").toList();

        return arrivalTimeList.stream().map(s -> ZonedDateTime.parse(s, formatter)).toList();
    }

    private static Duration getMinFlightTime(List<ZonedDateTime> arrivalZonedDateTimeList,
                                             List<ZonedDateTime> departureZonedDateTimeList) {
        List<Duration> durations = IntStream.range(0, arrivalZonedDateTimeList.size())
                .mapToObj(i -> Duration.between(departureZonedDateTimeList.get(i), arrivalZonedDateTimeList.get(i)))
                .sorted().toList();

        return durations.getFirst();


    }

    public static void getMinFlightTimeForEachCarrier() {

        for (String carrier : tickets.keySet()) {
            List<ZonedDateTime> departureTime = new ArrayList<>(convertDepartureTimeToZDT(tickets.get(carrier)));
            List<ZonedDateTime> arrivalTime = new ArrayList<>(convertArrivalTimeToZDT(tickets.get(carrier)));

            String timeInFlight = String.valueOf(getMinFlightTime(arrivalTime, departureTime))
                    .replaceAll("PT", "")
                    .replaceAll("H", " Hour(s) ")
                    .replaceAll("M", " Minute(s)");

            System.out.printf("Для перевозчика %s минимальное время %s%n", carrier, timeInFlight);
        }
    }

}
