package tk.vivas.adventofcode.year2023.day02;

import java.util.Arrays;
import java.util.List;

class SnowIslandGame {

    private final int gameId;
    private final List<CubeSet> sets;

    SnowIslandGame(String raw) {
        String gameIdString = raw.substring(raw.indexOf(" ") + 1, raw.indexOf(":"));
        gameId = Integer.parseInt(gameIdString);

        String setsString = raw.substring(raw.indexOf(":") + 2);

        sets = Arrays.stream(setsString.split("; ")).map(CubeSet::new).toList();
    }

    boolean isPossible() {
        return sets.stream().allMatch(CubeSet::isPossible);
    }

    public int getGameId() {
        return gameId;
    }
}
