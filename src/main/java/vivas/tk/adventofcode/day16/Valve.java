package vivas.tk.adventofcode.day16;

import java.util.*;

public class Valve {
    private final String id;
    private final int flowRate;
    private final List<String> neighborIds;
    private List<Valve> neighbours;

    private int bestScore;

    public Valve(String id, int flowRate, List<String> neighborIds) {
        this.id = id;
        this.flowRate = flowRate;
        this.neighborIds = neighborIds;
        this.bestScore = -1;
    }

    public String id() {
        return id;
    }

    public int flowRate() {
        return flowRate;
    }

    public List<Valve> getNeighbours() {
        return neighbours;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void updateBestScore(int score) {
        if (score > bestScore) {
            bestScore = score;
        }
    }

    public static Valve parse(String input) {
        String parsedId = input.substring(6, 8);
        String[] tokens = input.split("[=;]");
        int parsedFlowRate = Integer.parseInt(tokens[1]);
        tokens = tokens[2].substring(19).split(" ", 2);
        tokens = tokens[1].split(", ");
        List<String> parsedNeighbourIds = List.of(tokens);
        return new Valve(parsedId, parsedFlowRate, parsedNeighbourIds);
    }

    public void attachNeighbours(Map<String, Valve> valves) {
        neighbours = neighborIds.stream()
                .map(valves::get)
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Valve valve = (Valve) o;
        return id.equals(valve.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
