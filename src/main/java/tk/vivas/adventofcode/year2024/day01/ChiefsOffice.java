package tk.vivas.adventofcode.year2024.day01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class ChiefsOffice {

    private final List<LocationPair> locationPairs;
    private final List<Integer> sortedLeft;
    private final List<Integer> sortedRight;

    ChiefsOffice(String input) {
        Stream.Builder<Integer> unsortedLeft = Stream.builder();
        Stream.Builder<Integer> unsortedRight = Stream.builder();
        input.lines()
                .map(line -> line.split(" +"))
                .forEach(line -> {
                    int left = Integer.parseInt(line[0]);
                    unsortedLeft.add(left);
                    int right = Integer.parseInt(line[1]);
                    unsortedRight.add(right);
                });
        sortedLeft = unsortedLeft.build().sorted().toList();
        sortedRight = unsortedRight.build().sorted().toList();

        locationPairs = new ArrayList<>();
        Iterator<Integer> leftIterator = sortedLeft.iterator();
        Iterator<Integer> rightIterator = sortedRight.iterator();
        while (leftIterator.hasNext()) {
            LocationPair locationPair = new LocationPair(leftIterator.next(), rightIterator.next());
            locationPairs.add(locationPair);
        }
    }

    int totalDistance() {
        return locationPairs.stream()
                .mapToInt(LocationPair::distance)
                .sum();
    }

    long similarityScore() {
        Map<Integer, Integer> locationCount = new HashMap<>();

        int currentLocationId = 0;
        int currentCount = 0;
        for (Integer number : sortedRight) {
            if (number != currentLocationId) {
                locationCount.put(currentLocationId, currentCount);

                currentLocationId = number;
                currentCount = 0;
            }
            currentCount++;
        }
        return sortedLeft.stream()
                .mapToInt(locationId -> locationId * locationCount.getOrDefault(locationId, 0))
                .sum();
    }
}
