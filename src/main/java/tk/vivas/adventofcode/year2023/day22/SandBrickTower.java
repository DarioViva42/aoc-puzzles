package tk.vivas.adventofcode.year2023.day22;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

public class SandBrickTower {

    private final List<SandBrick> sandBricks;

    public SandBrickTower(String input) {
        List<String> lines = input.lines().toList();
        sandBricks = IntStream.range(0, lines.size())
                .mapToObj(i -> SandBrick.parse(lines.get(i), (char) ('a' + i)))
                .sorted()
                .toList();
    }

    public long countDisintegrationCandidates() {
        simulateGravity();
        initTopTouching();
        initBottomTouching();

        return sandBricks.stream()
                .filter(SandBrick::canBeRemoved)
                .count();
    }

    private void initTopTouching() {
        for (SandBrick sandBrick : sandBricks) {
            List<SandBrick> topBricks = sandBricks.stream()
                    .filter(sandBrick::directlyUnderOther)
                    .toList();
            sandBrick.setTopBricks(topBricks);
        }
    }

    private void initBottomTouching() {
        for (SandBrick sandBrick : sandBricks) {
            List<SandBrick> bottomBrick = sandBricks.stream()
                    .filter(sandBrick::directlyOverOther)
                    .toList();
            sandBrick.setBottomBricks(bottomBrick);
        }
    }

    private void simulateGravity() {
        for (int i = 0; i < sandBricks.size(); i++) {
            SandBrick sandBrick = sandBricks.get(i);
            sandBrick.fallDown(sandBricks.subList(0, i));
        }
    }
}
