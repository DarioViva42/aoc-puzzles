package tk.vivas.adventofcode.year2023.day10;

import static tk.vivas.adventofcode.year2023.day10.Direction.*;

record PipeTile(int x, int y, Direction enteredFrom) {

    PipeTile north() {
        return new PipeTile(x, y - 1, SOUTH);
    }

    PipeTile east() {
        return new PipeTile(x + 1, y, WEST);
    }

    PipeTile south() {
        return new PipeTile(x, y + 1, NORTH);
    }

    PipeTile west() {
        return new PipeTile(x - 1, y, EAST);
    }

    public PipeTile move(PipeType pipeType) {
        return switch (enteredFrom) {
            case NORTH -> switch (pipeType) {
                case NORTH_EAST -> east();
                case NORTH_SOUTH -> south();
                case NORTH_WEST -> west();
                case EAST_SOUTH, EAST_WEST, SOUTH_WEST, START, EMPTY -> throw createIllegalMoveException();
            };
            case EAST -> switch (pipeType) {
                case NORTH_EAST -> north();
                case EAST_SOUTH -> south();
                case EAST_WEST -> west();
                case NORTH_SOUTH, NORTH_WEST, SOUTH_WEST, START, EMPTY -> throw createIllegalMoveException();
            };
            case SOUTH -> switch (pipeType) {
                case NORTH_SOUTH -> north();
                case EAST_SOUTH -> east();
                case SOUTH_WEST -> west();
                case NORTH_EAST, NORTH_WEST, EAST_WEST, START, EMPTY -> throw createIllegalMoveException();
            };
            case WEST -> switch (pipeType) {
                case NORTH_WEST -> north();
                case EAST_WEST -> east();
                case SOUTH_WEST -> south();
                case NORTH_EAST, NORTH_SOUTH, EAST_SOUTH, START, EMPTY -> throw createIllegalMoveException();
            };
        };
    }

    private static IllegalStateException createIllegalMoveException() {
        return new IllegalStateException("Player ends up in an unexpected situation");
    }
}
