package tk.vivas.adventofcode.year2023.day24;

import java.util.List;

class HailStoneStorm {

    private final List<HailStone> hailStones;

    HailStoneStorm(String input) {
        hailStones = input.lines()
                .map(HailStone::from)
                .toList();
    }

    long countCollisions(long lowerBound, long upperBound) {
        long collisionCount = 0;
        for (int i = 0; i < hailStones.size(); i++) {
            for (int j = 0; j < i; j++) {
                HailStone hailStoneA = hailStones.get(i);
                HailStone hailStoneB = hailStones.get(j);
                if (hailStoneA.couldCollideWith(hailStoneB, lowerBound, upperBound)) {
                    collisionCount++;
                }
            }
        }
        return collisionCount;
    }
}
