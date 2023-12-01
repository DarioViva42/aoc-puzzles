package tk.vivas.adventofcode.year2022.day15;

record Range(int min, int max) {
    public TouchType toTouchType(Range other) {
        if (min >= other.min && max <= other.max) {
            return TouchType.CONTAINED;
        }
        if (min <= other.min && max >= other.max) {
            return TouchType.CONTAINS;
        }
        if (min <= other.max + 1 && min >= other.min) {
            return TouchType.RIGHT;
        }
        if (max >= other.min - 1 && max <= other.max) {
            return TouchType.LEFT;
        }
        return TouchType.NOT_TOUCHING;
    }

    public Range combine(Range other) {
        return switch (toTouchType(other)) {
            case CONTAINS -> this;
            case CONTAINED -> other;
            case LEFT -> new Range(min, other.max);
            case RIGHT -> new Range(other.min, max);
            case NOT_TOUCHING -> null;
        };
    }

    public int getSize() {
        return max - min + 1;
    }

    public boolean isDisjoint(Range other) {
        return min > other.max + 1 || max < other.min - 1;
    }

    public boolean touches(Range other) {
        return min <= other.max + 1 && max >= other.min - 1;
    }
}
