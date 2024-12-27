package tk.vivas.adventofcode.year2024.day16;

import tk.vivas.Position;
import tk.vivas.adventofcode.Direction;

record ReindeerState(Position position, Direction direction) {
    ReindeerState turnLeft() {
        return new ReindeerState(position, direction.left());
    }

    ReindeerState turnRight() {
        return new ReindeerState(position, direction.right());
    }
}
