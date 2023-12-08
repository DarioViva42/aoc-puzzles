package tk.vivas.adventofcode.year2023.day06;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class RaceDocument {

    private final List<RaceRecord> raceRecords;
    private final RaceRecord realRaceRecord;

    RaceDocument(String input) {
        List<String> lines = input.lines().toList();
        String timesRaw = lines.getFirst().split(": +")[1];
        List<Integer> times = Arrays.stream(timesRaw.split(" +"))
                .map(Integer::parseInt)
                .toList();
        String distancesRaw = lines.getLast().split(": +")[1];
        List<Integer> distances = Arrays.stream(distancesRaw.split(" +"))
                .map(Integer::parseInt)
                .toList();
        raceRecords = Stream.iterate(0, i -> i < times.size(), i -> i + 1)
                .map(i -> new RaceRecord(times.get(i), distances.get(i)))
                .toList();

        String realTimeRaw = timesRaw.replace(" ", "");
        long realTime = Long.parseLong(realTimeRaw);
        String realDistanceRaw = distancesRaw.replace(" ", "");
        long realDistance = Long.parseLong(realDistanceRaw);
        realRaceRecord = new RaceRecord(realTime, realDistance);
    }

    public long marginOfError() {
        return raceRecords.stream()
                .map(RaceRecord::marginOfError)
                .reduce(1L, (a, b) -> a * b);
    }

    public long realMarginOfError() {
        return realRaceRecord.marginOfError();
    }
}
