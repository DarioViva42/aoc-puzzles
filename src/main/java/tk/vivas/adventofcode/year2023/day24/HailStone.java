package tk.vivas.adventofcode.year2023.day24;

import tk.vivas.ConsoleColors;

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

    /*
    px + vx * t = other.px + other.vx * s
    py + vy * t = other.py + other.vy * s

    px = a, py = b, vx = c, vy = d
    other.px = e, other.py = f, other.vx = g, other.vy = h

    a + ct = e + gs
    b + dt = f + hs

    s = (a + ct - e) / g
    t = (f + hs - b) / d

    t = (f + h * ((a + ct - e) / g) - b) / d
    dt = f + ha/g + hct/g - he/g - b
    dt - hct/g = f + ha/g - he/g - b
    dgt - hct = fg + ha - he - bg
    (dg - hc) * t = (f-b)*g + (a-e)*h
    t = ((f-b)*g + (a-e)*h) / (dg - hc)
    */
    boolean couldCollideWith(HailStone other, long lowerBound, long upperBound) {
        System.out.println("\nHailstone A: " + this);
        System.out.println("Hailstone B:" + other);
        if (isParallelWith(other) || doesNotMove() || other.doesNotMove()) {
            System.out.println("Hailstones' paths are parallel; they never intersect.");
            return false;
        }
        double t = (double) ((other.py - py) * other.vx + (px - other.px) * other.vy) / (vy * other.vx - other.vy * vx);
        double s = (px + vx * t - other.px) / other.vx;
        if (s < 0 || t < 0) {
            System.out.println("Hailstones' paths crossed in the past");
            return false;
        }
        double collisionX = px + vx * t;
        double collisionY = py + vy * t;
        boolean couldCollide = collisionX >= lowerBound && collisionX <= upperBound
                && collisionY >= lowerBound && collisionY <= upperBound;
        if (couldCollide) {
            System.out.printf("Hailstones' paths will cross %sinside%s the test area (at x=%s, y=%s).%n",
                    ConsoleColors.WHITE_BOLD_BRIGHT, ConsoleColors.RESET, collisionX, collisionY);
        } else {
            System.out.printf("Hailstones' paths will cross outside the test area (at x=%s, y=%s).%n",
                    collisionX, collisionY);
        }
        return couldCollide;
    }

    private boolean doesNotMove() {
        return vx == 0 && vy == 0;
    }

    private boolean isParallelWith(HailStone other) {
        // other.vx / other.vy = vx / vy
        return other.vx * vy == vx * other.vy;
    }

    @Override
    public String toString() {
        return "%s, %s, %s @ %s, %s, %s".formatted(px, py, pz, vx, vy, vz);
    }
}
