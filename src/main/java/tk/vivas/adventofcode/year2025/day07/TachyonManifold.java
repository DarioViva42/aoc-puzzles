package tk.vivas.adventofcode.year2025.day07;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

class TachyonManifold {

    private final List<List<Tile>> map;
    private final int startPosition;

    TachyonManifold(String input) {
        map = input.lines()
                .map(String::chars)
                .map(chars -> chars.mapToObj(c -> new Tile((char) c)))
                .map(Stream::toList)
                .toList();
        startPosition = input.indexOf('S');
    }

    long usedSplitters() {
        walk(startPosition, 0);
        return map.stream()
                .flatMap(Collection::stream)
                .filter(Tile::wasVisited)
                .filter(Tile::isSplitter)
                .count();
    }

    private void walk(int x, int y) {
        if (y >= map.size()) {
            return;
        }
        Tile tile = map.get(y).get(x);
        if (tile.wasVisited()) {
            return;
        }
        tile.visit();
        if (tile.isSplitter()) {
            walk(x - 1, y);
            walk(x + 1, y);
        } else {
            walk(x, y + 1);
        }
    }

    long timelines() {
        quantumWalk(startPosition, 0, null);

        Tile originSplitter = map.stream()
                .flatMap(Collection::stream)
                .filter(Tile::isSplitter)
                .filter(not(Tile::hasParents))
                .filter(Tile::hasChildren)
                .findFirst().orElseThrow();

        return originSplitter.timelines();
    }

    private void quantumWalk(int x, int y, Tile lastSplitter) {
        if (y >= map.size()) {
            return;
        }
        Tile tile = map.get(y).get(x);
        if (lastSplitter != null && tile.isSplitter()) {
            tile.addParent(lastSplitter);
            lastSplitter.addChild(tile);
        }
        if (tile.parentCount() > 1) {
            return;
        }
        if (tile.isSplitter()) {
            quantumWalk(x - 1, y, tile);
            quantumWalk(x + 1, y, tile);
        } else {
            quantumWalk(x, y + 1, lastSplitter);
        }
    }
}
