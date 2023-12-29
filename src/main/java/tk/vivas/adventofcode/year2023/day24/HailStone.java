package tk.vivas.adventofcode.year2023.day24;

record HailStone(long px, long py, long pz, long vx, long vy, long vz) {

    public static HailStone from(String input) {
        String[] split = input.split(" @ ");
        String[] position = split[0].split(", ");
        String[] velocity = split[1].split(", ");

        long px = Long.parseLong(position[0].strip());
        long py = Long.parseLong(position[1].strip());
        long pz = Long.parseLong(position[2].strip());

        long vx = Long.parseLong(velocity[0].strip());
        long vy = Long.parseLong(velocity[1].strip());
        long vz = Long.parseLong(velocity[2].strip());

        return new HailStone(px, py, pz, vx, vy, vz);
    }

    boolean couldCollideWith(HailStone other, long lowerBound, long upperBound) {
        if (isParallelWith(other) || doesNotMove() || other.doesNotMove()) {
            return false;
        }
        double t = (double) ((other.py - py) * other.vx + (px - other.px) * other.vy) / (vy * other.vx - other.vy * vx);
        double s = (px + vx * t - other.px) / other.vx;
        if (s < 0 || t < 0) {
            return false;
        }
        double collisionX = px + vx * t;
        double collisionY = py + vy * t;
        return collisionX >= lowerBound && collisionX <= upperBound
                && collisionY >= lowerBound && collisionY <= upperBound;
    }

    private boolean doesNotMove() {
        return vx == 0 && vy == 0;
    }

    private boolean isParallelWith(HailStone other) {
        return other.vx * vy == vx * other.vy;
    }

    @Override
    public String toString() {
        return "%s, %s, %s @ %s, %s, %s".formatted(px, py, pz, vx, vy, vz);
    }
}
