package tk.vivas.adventofcode.year2024.day16;

import tk.vivas.adventofcode.Direction;

record LabyrinthPath(Direction startDirection, Direction endDirection,
                     LabyrinthTile from, LabyrinthTile to, int length) {
    LabyrinthPath invert() {
        return new LabyrinthPath(endDirection.opposite(), startDirection.opposite(), to, from, length);
    }

    ReindeerState createStartState() {
        return from.stateWithDirection(startDirection);
    }
}
