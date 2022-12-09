package vivas.tk.adventofcode.day02;

import com.codepoetics.protonpack.StreamUtils;

import java.util.List;
import java.util.stream.Stream;

public class RockPaperScissorsCompetition {

    private final List<HandShape> opponent;
    private final Stream<HandShape> me;
    private final Stream<Strategy> strategyList;

    public RockPaperScissorsCompetition(String input) {
        opponent = input.lines()
                .map(s -> s.charAt(0))
                .map(HandShape::ofCharacter)
                .toList();
        me = input.lines()
                .map(s -> s.charAt(2))
                .map(HandShape::ofCharacter);
        strategyList = input.lines()
                .map(s -> s.charAt(2))
                .map(Strategy::ofCharacter);
    }

    public int calculateCorrectScore() {
        return StreamUtils
                .zip(opponent.stream(), strategyList, this::calculateScore)
                .reduce(0, Integer::sum);
    }

    private int calculateScore(HandShape opponent, Strategy strategy) {
        return switch (strategy) {
            case LOOSE -> opponent.getLoosingHandShape().getScore();
            case DRAW -> 3 + opponent.getScore();
            case WIN -> 6 + opponent.getWinningHandShape().getScore();
        };
    }

    public int calculateScore() {
        return StreamUtils
                .zip(opponent.stream(), me, this::calculateScore)
                .reduce(0, Integer::sum);
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
