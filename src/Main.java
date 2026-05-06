import exo.Partie1;
import exo.Partie2;
import exo.Partie3;
import exo.Partie4;
import factory.TripFactory;
import models.Trip;

import java.util.List;


void main() {

    // Petite liste pour afficher les résultats lisiblement
    List<Trip> trips = TripFactory.generateTrips(10);

    Partie1 p1 = new Partie1();
    Partie2 p2 = new Partie2();
    Partie3 p3 = new Partie3();
    Partie4 p4 = new Partie4();

    // ==============================
    // PARTIE 1 — Filtrage
    // ==============================
    System.out.println("=== PARTIE 1 ===");

    System.out.println("\n[Ex1] Long & expensive (dist>10, prix>20) :");
    p1.longAndExpensiveTrips(trips).forEach(System.out::println);

    System.out.println("\n[Ex2] Bad trips (rating<3) :");
    p1.badTrips(trips).forEach(System.out::println);

    System.out.println("\n[Ex3] Recent trips (aujourd'hui ou hier) :");
    p1.recentTrips(trips).forEach(System.out::println);

    // ==============================
    // PARTIE 2 — Statistiques
    // ==============================
    System.out.println("\n=== PARTIE 2 ===");

    System.out.println("\n[Ex4] Count by city :");
    p2.countByCity(trips).forEach((city, count) -> System.out.println("  " + city + " -> " + count));

    System.out.println("\n[Ex5] Revenue by driver :");
    p2.revenueByDriver(trips).forEach((driver, rev) -> System.out.println("  " + driver + " -> " + String.format("%.2f", rev) + "€"));

    System.out.println("\n[Ex6] Avg duration by city :");
    p2.avgDurationByCity(trips).forEach((city, avg) -> System.out.println("  " + city + " -> " + String.format("%.2f", avg) + " min"));

    // ==============================
    // PARTIE 3 — Tri & recherche
    // ==============================
    System.out.println("\n=== PARTIE 3 ===");

    System.out.println("\n[Ex7] Top 10 expensive trips :");
    p3.top10ExpensiveTrips(trips).forEach(t -> System.out.println("  id=" + t.id() + " | price=" + String.format("%.2f", t.price()) + "€"));

    System.out.println("\n[Ex8] Best trip :");
    p3.bestTrip(trips).ifPresent(t -> System.out.println("  id=" + t.id() + " | rating=" + String.format("%.2f", t.rating())));

    // ==============================
    // PARTIE 4 — Parallèle (gros volume)
    // ==============================
    System.out.println("\n=== PARTIE 4 ===");

    List<Trip> bigList = TripFactory.generateTrips(10_000_000);

    long t0 = System.currentTimeMillis();
    double revSeq = p4.totalRevenueSequential(bigList);
    long t1 = System.currentTimeMillis();
    double revPar = p4.totalRevenueParallel(bigList);
    long t2 = System.currentTimeMillis();

    System.out.println("\n[Ex10] Revenue sequential : " + String.format("%.2f", revSeq) + "€ en " + (t1 - t0) + "ms");
    System.out.println("[Ex11] Revenue parallel   : " + String.format("%.2f", revPar) + "€ en " + (t2 - t1) + "ms");

    System.out.println("\n[Ex12] Count by city (parallel) :");
    p4.countByCityParallel(bigList).forEach((city, count) -> System.out.println("  " + city + " -> " + count));

    System.out.println("\n[Ex13] Premium trips parallel (prix>30, rating>4) : " + p4.premiumTripsParallel(bigList).size() + " trajets");
}