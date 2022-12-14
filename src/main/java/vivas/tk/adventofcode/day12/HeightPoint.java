package vivas.tk.adventofcode.day12;

import java.util.List;

public class HeightPoint {
    private final int height;
    private final boolean end;
    private final boolean start;
    private int stepCount;

    private List<HeightPoint> neighbours;

    public HeightPoint(int height, boolean start, boolean end) {
        this.height = height;
        this.start = start;
        this.end = end;
        stepCount = Integer.MAX_VALUE;
    }

    public boolean isStart() {
        return start;
    }

    public boolean isEnd() {
        return end;
    }

    public void setNeighbors(List<HeightPoint> neighbors) {
        this.neighbours = neighbors;
    }

    public int getHeight() {
        return height;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public int getStepCount() {
        return stepCount;
    }

    public List<HeightPoint> getNeighbours() {
        return neighbours;
    }

    static HeightPoint parse(char character) {
        HeightPoint memoryPoint;
        if (character == 'S') {
            memoryPoint = new HeightPoint('a', true, false);
        } else if (character == 'E') {
            memoryPoint = new HeightPoint('z', false, true);
        } else {
            memoryPoint = new HeightPoint(character, false, false);
        }
        return memoryPoint;
    }
}
