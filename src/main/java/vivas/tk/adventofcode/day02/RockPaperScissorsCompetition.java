package vivas.tk.adventofcode.day02;

import java.util.List;

public class RockPaperScissorsCompetition {

    private final List<String> input;

    public RockPaperScissorsCompetition(List<String> input) {
        this.input = input;
    }

    public int calculateScore() {
        return input.stream()
                .mapToInt(round -> {
                    HandShape opponent = HandShape.ofCharacter(round.charAt(0));
                    HandShape me = HandShape.ofCharacter(round.charAt(2));

                    return calculateScore(opponent, me);
                })
                .sum();
    }

    public int calculateCorrectScore() {
        return input.stream()
                .mapToInt(round -> {
                    HandShape opponent = HandShape.ofCharacter(round.charAt(0));
                    Strategy strategy = Strategy.ofCharacter(round.charAt(2));

                    return switch (strategy) {
                        case LOOSE -> opponent.getLoosingHandShape().getScore();
                        case DRAW -> 3 + opponent.getScore();
                        case WIN -> 6 + opponent.getWinningHandShape().getScore();
                    };
                })
                .sum();
    }

    private int calculateScore(HandShape opponent, HandShape me) {
        int score = me.getScore();

        if (opponent.equals(me)) {
            score += 3;
        } else if (me.winsAgainst(opponent)) {
            score += 6;
        }
        return score;
    }
}
