package exo;

import models.Trip;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Partie4 {

    Function<Trip, String> byCity = Trip::city;
    Function<Trip, Double> toPrice = Trip::price;

    Predicate<Trip> isPremium = trip ->
            trip.price() > 30 && trip.rating() > 4;

    public double totalRevenueSequential(List<Trip> trips) {
        return trips.stream()
                .mapToDouble(trip -> toPrice.apply(trip))
                .sum();
    }

    public double totalRevenueParallel(List<Trip> trips) {
        return trips.parallelStream()
                .mapToDouble(trip -> toPrice.apply(trip))
                .sum();
    }

    public Map<String, Long> countByCityParallel(List<Trip> trips) {
        return trips.parallelStream()
                .collect(Collectors.groupingByConcurrent(byCity, Collectors.counting()));
    }

    public List<Trip> premiumTripsParallel(List<Trip> trips) {
        return trips.parallelStream()
                .filter(isPremium)
                .collect(Collectors.toList());
    }
}
