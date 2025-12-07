package tk.vivas.adventofcode.year2025.day07;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

class TachyonManifold {

    private final List<List<Tile>> map;
    private final List<Tile> splitters;

    TachyonManifold(String input) {
        map = input.lines()
                .map(String::chars)
                .map(chars -> chars.mapToObj(c -> new Tile((char) c)))
                .map(Stream::toList)
                .toList();
        walk(input.indexOf('S'), 0, new Tile('^'));

        splitters = map.stream()
                .flatMap(Collection::stream)
                .filter(Tile::isSplitter)
                .toList();
    }

    long usedSplitters() {
        return splitters.stream()
                .filter(Tile::wasVisited)
                .count();
    }

    long timelines() {
        return splitters.getFirst().timelines();
    }

    private void walk(int x, int y, Tile lastSplitter) {
        if (y >= map.size()) {
            return;
        }
        Tile tile = map.get(y).get(x);
        if (!tile.isSplitter()) {
            walk(x, y + 1, lastSplitter);
            return;
        }
        lastSplitter.addChild(tile);
        if (tile.wasVisited()) {
            return;
        }
        tile.visit();
        walk(x - 1, y, tile);
        walk(x + 1, y, tile);
    }
}
