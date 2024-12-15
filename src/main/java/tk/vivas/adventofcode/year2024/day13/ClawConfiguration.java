package tk.vivas.adventofcode.year2024.day13;

import java.util.stream.IntStream;

class ClawConfiguration {

    private final Button a;
    private final Button b;
    private final Price price;

    ClawConfiguration(String input) {
        String[] lines = input.lines().toArray(String[]::new);
        a = Button.of(lines[0]);
        b = Button.of(lines[1]);

        price = Price.of(lines[2]);
    }

    int tokensToWin() {
        return IntStream.range(0, 101).mapToObj(aRepeat -> IntStream.range(0, 101).map(bRepeat ->
                        countTokens(aRepeat, bRepeat)))
                .flatMapToInt(tokenCount -> tokenCount)
                .filter(tokenCount -> tokenCount != 0)
                .findFirst().orElse(0);
    }

    private int countTokens(int aRepeat, int bRepeat) {
        int reachedX = a.moveX() * aRepeat + b.moveX() * bRepeat;
        int reachedY = a.moveY() * aRepeat + b.moveY() * bRepeat;

        if (price.xPos() != reachedX || price.yPos() != reachedY) {
            return 0;
        }
        return 3 * aRepeat + bRepeat;
    }
}
