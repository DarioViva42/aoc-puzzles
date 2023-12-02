package tk.vivas.adventofcode.year2023.day02;

import java.util.List;

class SnowIslandGames {

    private final List<SnowIslandGame> games;

    SnowIslandGames(String input) {
        games = input.lines()
                .map(SnowIslandGame::new)
                .toList();
    }

    int sumOfPossibleGameIds() {
        return games.stream()
                .filter(SnowIslandGame::isPossible)
                .mapToInt(SnowIslandGame::getGameId)
                .sum();
    }
}
