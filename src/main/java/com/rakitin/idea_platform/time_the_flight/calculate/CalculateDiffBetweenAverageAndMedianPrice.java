package com.rakitin.idea_platform.time_the_flight.calculate;

import com.rakitin.idea_platform.time_the_flight.pojo.FlightInfoPOJO;
import com.rakitin.idea_platform.time_the_flight.reader.TicketsFromJsonReader;

import java.util.*;

public class CalculateDiffBetweenAverageAndMedianPrice {

    private static final List<FlightInfoPOJO> flightInfo = TicketsFromJsonReader.getFlightInfo();


    private static double calculateAveragePrice() {

        int sumOfAllPrices = flightInfo.stream().mapToInt(FlightInfoPOJO::getPrice).sum();

        return (sumOfAllPrices * 1.0) / flightInfo.size();

    }

    private static double calculateMedianPrice() {

        List<Integer> prices = new ArrayList<>(flightInfo
                .stream().map(FlightInfoPOJO::getPrice)
                .sorted().toList());

        double medianValue;
        int collectionSize = prices.size();

        if (prices.size() % 2 == 0)
            medianValue = (prices.get(collectionSize / 2 - 1) + prices.get(collectionSize / 2)) / 2.0;
        else medianValue = prices.get(collectionSize / 2);

        return medianValue;

    }

    public static double calculate() {

        return Math.abs(calculateAveragePrice() - calculateMedianPrice());

    }
}
