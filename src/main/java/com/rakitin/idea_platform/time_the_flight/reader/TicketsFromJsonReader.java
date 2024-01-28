package com.rakitin.idea_platform.time_the_flight.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakitin.idea_platform.time_the_flight.pojo.FlightInfoPOJO;
import com.rakitin.idea_platform.time_the_flight.pojo.TicketPOJO;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TicketsFromJsonReader {
    public static List<FlightInfoPOJO> getFlightInfo() {
        ObjectMapper objectMapper = new ObjectMapper();

        TicketPOJO tickets;
        try {
                 tickets = objectMapper.readValue(new File("tickets.json")
                    , TicketPOJO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return tickets.getTickets();
    }


}
