package tk.vivas.adventofcode.year2023.day03;

import java.util.regex.MatchResult;

class PositionedSymbol {

    private final int x;
    private final int y;

    PositionedSymbol(int y, MatchResult matchResult) {
        this.x = matchResult.start();
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
