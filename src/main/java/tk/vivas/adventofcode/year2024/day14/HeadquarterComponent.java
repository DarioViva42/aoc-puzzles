package tk.vivas.adventofcode.year2024.day14;

import tk.vivas.Position;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static tk.vivas.adventofcode.year2024.day14.Day14Visualization.SCALE;

class HeadquarterComponent extends JPanel {
    private final BathroomOperation bathroomOperation;
    private final int sizeX;
    private final int sizeY;
    private int time;

    HeadquarterComponent(BathroomOperation bathroomOperation, int sizeX, int sizeY) {
        super();
        this.bathroomOperation = bathroomOperation;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.time = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        printMoment(g, time);
    }

    int incrementTime(int amount) {
        time += amount;
        time = Math.floorMod(time, sizeX * sizeY);
        repaint();
        return time;
    }

    private void printMoment(Graphics graphics, long time) {
        List<Position> positions = bathroomOperation.getPositions(time);

        graphics.clearRect(0, 0, sizeX * SCALE, sizeY * SCALE);
        positions.forEach(position -> graphics.fillRect(
                position.x() * SCALE, position.y() * SCALE, SCALE, SCALE));
    }
}
