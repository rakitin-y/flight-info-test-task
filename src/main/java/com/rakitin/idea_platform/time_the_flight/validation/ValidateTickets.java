package com.rakitin.idea_platform.time_the_flight.validation;

import com.rakitin.idea_platform.time_the_flight.pojo.FlightInfoPOJO;
import com.rakitin.idea_platform.time_the_flight.reader.TicketsFromJsonReader;

import java.util.*;
import java.util.stream.Collectors;

public class ValidateTickets {
    private static final List<FlightInfoPOJO> flightInfo = TicketsFromJsonReader.getFlightInfo();


    public static List<FlightInfoPOJO> getTicketsVvoToTlv() {

        return flightInfo.stream()
                .filter(infoPOJO -> infoPOJO.getOrigin().equals("VVO")
                        && infoPOJO.getDestination().equals("TLV"))
                .toList();
    }

    public static Map<String, List<FlightInfoPOJO>> getTicketsForEachCarrier() {

        List<FlightInfoPOJO> flightInfo = getTicketsVvoToTlv();

        Set<String> carriersNames = flightInfo.stream().map(FlightInfoPOJO::getCarrier).collect(Collectors.toSet());

        return carriersNames.stream()
                .collect(Collectors.toMap(
                        s -> s,
                        s -> flightInfo.stream()
                                .filter(flightInfoPOJO -> flightInfoPOJO.getCarrier().equals(s))
                                .toList()));
    }
}
