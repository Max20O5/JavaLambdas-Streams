package exo;

import models.Trip;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Partie2 {

    Function<Trip, String> byCity = Trip::city;
    Function<Trip, String> byDriver = Trip::driverId;
    Function<Trip, Double> toPrice = Trip::price;
    Function<Trip, Double> toDuration = Trip::durationMin;

    public Map<String, Long> countByCity(List<Trip> trips) {
        return trips.stream()
                .collect(Collectors.groupingBy(byCity, Collectors.counting()));
    }

    public Map<String, Double> revenueByDriver(List<Trip> trips) {
        return trips.stream()
                .collect(Collectors.groupingBy(
                        byDriver,
                        Collectors.summingDouble(trip -> toPrice.apply(trip))
                ));
    }

    public Map<String, Double> avgDurationByCity(List<Trip> trips) {
        return trips.stream()
                .collect(Collectors.groupingBy(
                        byCity,
                        Collectors.averagingDouble(trip -> toDuration.apply(trip))
                ));
    }
}
