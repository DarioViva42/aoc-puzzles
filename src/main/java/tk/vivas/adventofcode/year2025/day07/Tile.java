package tk.vivas.adventofcode.year2025.day07;

import java.util.ArrayList;
import java.util.List;

class Tile {
    final TileType tileType;
    final List<Tile> parents;
    final List<Tile> children;

    boolean wasVisited = false;
    long timelineCount = 0;

    Tile(char character) {
        tileType = switch (character) {
            case '.', 'S' -> TileType.EMPTY_SPACE;
            case '^' -> TileType.SPLITTER;
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
        if (tileType == TileType.EMPTY_SPACE) {
            parents = List.of();
            children = List.of();
        } else {
            parents = new ArrayList<>();
            children = new ArrayList<>();
        }
    }

    void visit() {
        wasVisited = true;
    }

    void addParent(Tile parent) {
        parents.add(parent);
    }

    public void addChild(Tile child) {
        children.add(child);
    }

    boolean isSplitter() {
        return TileType.SPLITTER.equals(tileType);
    }

    boolean wasVisited() {
        return wasVisited;
    }

    int parentCount() {
        return parents.size();
    }

    boolean hasParents() {
        return !parents.isEmpty();
    }

    boolean hasChildren() {
        return !children.isEmpty();
    }

    public long timelines() {
        if (timelineCount != 0) {
            return timelineCount;
        }
        long timelineCount = switch (children.size()) {
            case 0 -> 2;
            case 1 -> children.getFirst().timelines() + 1;
            case 2 -> children.getFirst().timelines() + children.getLast().timelines();
            default -> throw new IllegalStateException("Unexpected value: " + children.size());
        };
        this.timelineCount = timelineCount;
        return timelineCount;
    }
}
