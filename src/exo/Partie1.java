package exo;

import models.Trip;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Partie1 {

    Predicate<Trip> isLongAndExpensive = trip ->
            trip.distanceKm() > 10 && trip.price() > 20;

    Predicate<Trip> isBadTrip = trip ->
            trip.rating() < 3;

    Predicate<Trip> isRecent = trip -> {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate tripDate = trip.startTime().toLocalDate();
        return tripDate.equals(today) || tripDate.equals(yesterday);
    };

    public List<Trip> longAndExpensiveTrips(List<Trip> trips) {
        return trips.stream()
                .filter(isLongAndExpensive)
                .collect(Collectors.toList());
    }

    public List<Trip> badTrips(List<Trip> trips) {
        return trips.stream()
                .filter(isBadTrip)
                .collect(Collectors.toList());
    }

    public List<Trip> recentTrips(List<Trip> trips) {
        return trips.stream()
                .filter(isRecent)
                .collect(Collectors.toList());
    }
}
