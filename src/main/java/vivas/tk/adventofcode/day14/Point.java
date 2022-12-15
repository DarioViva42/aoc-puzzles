package vivas.tk.adventofcode.day14;

record Point(int x, int y) {
    public Point nextInDirection(Direction direction) {
        return switch (direction) {
            case UP -> top();
            case DOWN -> bottom();
            case LEFT -> left();
            case RIGHT -> right();
        };
    }

    private Point bottom() {
        return new Point(x, y + 1);
    }

    private Point top() {
        return new Point(x, y - 1);
    }

    private Point left() {
        return new Point(x - 1, y);
    }

    private Point right() {
        return new Point(x + 1, y);
    }
    Direction toPoint(Point other) {
        if (other.x > x) {
            return Direction.RIGHT;
        } else if (other.x < x) {
            return Direction.LEFT;
        } else if (other.y > y) {
            return Direction.DOWN;
        }
        return Direction.UP;
    }

    public static Point parse(String input) {
        String[] split = input.split(",");
        int xParsed = Integer.parseInt(split[0]);
        int yParsed = Integer.parseInt(split[1]);

        return new Point(xParsed, yParsed);
    }
}
