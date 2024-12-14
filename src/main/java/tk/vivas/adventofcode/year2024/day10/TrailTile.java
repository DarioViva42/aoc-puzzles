package tk.vivas.adventofcode.year2024.day10;

import tk.vivas.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class TrailTile {
    private final int height;
    private final List<TrailTile> neighbours;
    private int rating;
    private boolean isRated;
    private Set<Position> reachableSummits;

    private Position position;

    TrailTile(int height) {
        this.height = height - '0';

        neighbours = new ArrayList<>();
        isRated = false;
        rating = 0;
    }

    boolean isTrailHead() {
        return height == 0;
    }

    Set<Position> getReachableSummits() {
        if (reachableSummits != null) {
            return reachableSummits;
        }
        if (height == 9) {
            reachableSummits = Set.of(position);
            return reachableSummits;
        }
        reachableSummits = neighbours.stream()
                .map(TrailTile::getReachableSummits)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        return reachableSummits;
    }

    public int getRating() {
        if (isRated) {
            return rating;
        }
        isRated = true;
        if (height == 9) {
            rating = 1;
            return rating;
        }
        rating = neighbours.stream()
                .mapToInt(TrailTile::getRating)
                .sum();
        return rating;
    }

    void setPosition(Position position) {
        this.position = position;
    }

    void addNeighbour(TrailTile trailTile) {
        if (trailTile.height - height == 1) {
            neighbours.add(trailTile);
        }
    }
}
