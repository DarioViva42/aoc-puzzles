package tk.vivas.adventofcode.day16;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.util.function.Predicate.not;

class MegaValve {

    private final String id;
    private final int flowRate;
    private final Valve originalValve;
    private final Map<MegaValve, Integer> neighbours;

    public MegaValve(Valve valve) {
        this.id = valve.id();
        this.flowRate = valve.flowRate();
        this.originalValve = valve;
        this.neighbours = new HashMap<>();
    }

    public void attachNeighbours(Map<String, MegaValve> allMegaValves) {
        for (Valve neighbour : originalValve.getNeighbours()) {
            initialGraphTraversing(allMegaValves, originalValve, neighbour, 1);
        }
    }

    private void initialGraphTraversing(Map<String, MegaValve> allMegaValves,
                                        Valve origin, Valve neighbour, int distance) {
        if (allMegaValves.containsKey(neighbour.id())) {
            neighbours.put(allMegaValves.get(neighbour.id()), distance);
            return;
        }
        Valve nextNeighbour = neighbour.getNeighbours().stream()
                .filter(not(valve -> valve.id().equals(origin.id())))
                .findFirst().orElseThrow();
        initialGraphTraversing(allMegaValves, neighbour, nextNeighbour, distance + 1);
    }

    public String id() {
        return id;
    }

    public int flowRate() {
        return flowRate;
    }

    public Set<MegaValve> getNeighbours() {
        return neighbours.keySet();
    }

    public int stepsNeededToNeighbour(MegaValve neighbour) {
        return neighbours.get(neighbour) - 1;
    }

    @Override
    public String toString() {
        return "MegaValve{" +
                "id='" + id + '\'' +
                ", flowRate=" + flowRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MegaValve megaValve = (MegaValve) o;
        return Objects.equals(id, megaValve.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
