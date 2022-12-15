package vivas.tk.adventofcode.day15;

record Sensor(int x, int y, int radius) {
    public Range rangeOnLine(int lineNumber) {
        int distanceToLine = Math.abs(y - lineNumber);
        if (distanceToLine > radius) {
            return null;
        }
        int leftOverDistance = radius - distanceToLine;
        return new Range(x - leftOverDistance, x + leftOverDistance);
    }
}
