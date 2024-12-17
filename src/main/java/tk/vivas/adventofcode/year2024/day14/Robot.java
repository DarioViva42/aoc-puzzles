package tk.vivas.adventofcode.year2024.day14;

import tk.vivas.Position;

class Robot {

    private final int x;
    private final int y;
    private final int vx;
    private final int vy;

    Robot(String line) {
        String[] split = line.split("[=, ]");
        x = Integer.parseInt(split[1]);
        y = Integer.parseInt(split[2]);
        vx = Integer.parseInt(split[4]);
        vy = Integer.parseInt(split[5]);
    }

    Position afterElapsedTime(long time, int xSize, int ySize) {
        int reachedX = Math.floorMod(x + time * vx, xSize);
        int reachedY = Math.floorMod(y + time * vy, ySize);
        return new Position(reachedX, reachedY);
    }
}
