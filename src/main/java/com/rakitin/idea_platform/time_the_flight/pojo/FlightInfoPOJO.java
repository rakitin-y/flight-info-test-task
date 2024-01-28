package com.rakitin.idea_platform.time_the_flight.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FlightInfoPOJO {

    private String origin;
    @JsonProperty("origin_name")
    private String originName;
    private String destination;
    @JsonProperty("destination_name")
    private String destinationName;
    @JsonProperty("departure_date")
    private String departureDate;
    @JsonProperty("departure_time")
    private String departureTime;
    @JsonProperty("arrival_date")
    private String arrivalDate;
    @JsonProperty("arrival_time")
    private String arrivalTime;
    private String carrier;
    private int stops;
    private int price;

}