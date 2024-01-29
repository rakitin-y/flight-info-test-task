package com.rakitin.idea_platform.time_the_flight;


import com.rakitin.idea_platform.time_the_flight.calculate.CalculateDiffBetweenAverageAndMedianPrice;
import com.rakitin.idea_platform.time_the_flight.calculate.CalculateFlightTime;

public class Main {
    public static void main(String[] args) {

        System.out.println(
                "Разница между средней ценой и медианой: "
                        + CalculateDiffBetweenAverageAndMedianPrice.calculate());
        CalculateFlightTime.getMinFlightTimeForEachCarrier();
    }
}
