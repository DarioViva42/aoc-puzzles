package tk.vivas.adventofcode.year2024.day13;

import java.util.stream.IntStream;

class ClawConfiguration {

    private final Button a;
    private final Button b;
    private final Price price;

    ClawConfiguration(Button a, Button b, Price price) {
        this.a = a;
        this.b = b;
        this.price = price;
    }

    static ClawConfiguration of(String input) {
        String[] lines = input.lines().toArray(String[]::new);
        Button a = Button.of(lines[0]);
        Button b = Button.of(lines[1]);

        Price price = Price.of(lines[2]);

        return new ClawConfiguration(a, b, price);
    }

    int simulateTokensToWin() {
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

    long calculateTokensToWin() {
        double ad = (price.yPos() - b.moveY() * price.xPos() / (double) b.moveX())
                / (a.moveY() - a.moveX() * b.moveY() / (double) b.moveX());
        double bd = (price.xPos() - a.moveX() * ad) / b.moveX();

        long al = (long) ad;
        long bl = (long) bd;

        boolean xCorrect = al * a.moveX() + bl * b.moveX() == price.xPos();
        boolean yCorrect = al * a.moveY() + bl * b.moveY() == price.yPos();
        if (!xCorrect || !yCorrect || al < 0 || bl < 0) {
            return 0;
        }

        return 3 * al + bl;
    }

    ClawConfiguration fixConversion() {
        return new ClawConfiguration(a, b, price.fixConversion());
    }
}
