package tk.vivas.adventofcode.year2025.day07;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

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
}
