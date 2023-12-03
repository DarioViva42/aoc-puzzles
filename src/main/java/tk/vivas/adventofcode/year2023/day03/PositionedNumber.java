package tk.vivas.adventofcode.year2023.day03;

import java.util.regex.MatchResult;

class PositionedNumber {

    private final int xLeft;
    private final int xRight;
    private final int y;
    private final int value;

    PositionedNumber(int y, MatchResult matchResult) {
        this.y = y;
        xLeft = matchResult.start();
        xRight = matchResult.end();
        value = Integer.parseInt(matchResult.group(0));
    }

    int getLeft() {
        return xLeft;
    }

    int getRight() {
        return xRight;
    }

    int getY() {
        return y;
    }

    int getValue() {
        return value;
    }
}
