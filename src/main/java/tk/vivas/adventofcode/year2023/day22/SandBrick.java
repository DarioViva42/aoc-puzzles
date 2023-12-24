package tk.vivas.adventofcode.year2023.day22;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class SandBrick implements Comparable<SandBrick> {
    private final char id;
    private final int x1;
    private final int y1;
    private int z1;
    private final int x2;
    private final int y2;
    private int z2;
    private List<SandBrick> topBricks;
    private List<SandBrick> bottomBricks;

    public SandBrick(char id, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.id = id;

        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.z1 = Math.min(z1, z2);

        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        this.z2 = Math.max(z1, z2);
    }

    static SandBrick parse(String input, char id) {
        String[] split = input.split("~");
        String[] firstEnd = split[0].split(",");
        String[] secondEnd = split[1].split(",");
        int x1 = Integer.parseInt(firstEnd[0]);
        int y1 = Integer.parseInt(firstEnd[1]);
        int z1 = Integer.parseInt(firstEnd[2]);
        int x2 = Integer.parseInt(secondEnd[0]);
        int y2 = Integer.parseInt(secondEnd[1]);
        int z2 = Integer.parseInt(secondEnd[2]);
        return new SandBrick(id, x1, y1, z1, x2, y2, z2);
    }

    int topZ() {
        return z2;
    }

    int lowerZ() {
        return z1;
    }

    void fallDown(List<SandBrick> sandBricks) {
        int landingHeight = sandBricks.stream()
                .filter(this::overEachOther)
                .mapToInt(SandBrick::topZ)
                .map(z -> z + 1)
                .max().orElse(1);
        fallTo(landingHeight);
    }

    protected void fallTo(int landingHeight) {
        z2 = z2 - z1 + landingHeight;
        z1 = landingHeight;
    }

    boolean overEachOther(SandBrick other) {
        return x1 <= other.x2 && x2 >= other.x1
                && y1 <= other.y2 && y2 >= other.y1;
    }

    public void setTopBricks(List<SandBrick> topBricks) {
        this.topBricks = topBricks;
    }

    public void setBottomBricks(List<SandBrick> bottomBricks) {
        this.bottomBricks = bottomBricks;
    }

    boolean directlyOverOther(SandBrick other) {
        return z1 == other.z2 + 1
                && overEachOther(other);
    }

    boolean directlyUnderOther(SandBrick other) {
        return z2 + 1 == other.z1
                && overEachOther(other);
    }

    boolean canBeRemoved() {
        return topBricks.stream().allMatch(SandBrick::hasMultipleHolders);
    }

    private static boolean hasMultipleHolders(SandBrick topBrick) {
        return topBrick.bottomBricks.size() > 1;
    }

    @Override
    public int compareTo(SandBrick other) {
        return this.lowerZ() - other.lowerZ();
    }

    @Override
    public String toString() {
        String supportsList = topBricks.stream()
                .map(e -> e.id)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        String supportedByList = bottomBricks.stream()
                .map(e -> e.id)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return "%s: %s,%s,%s~%s,%s,%s supports: %s supported by: %s"
                .formatted(id, x1, y1, z1, x2, y2, z2, supportsList, supportedByList);
    }
}
