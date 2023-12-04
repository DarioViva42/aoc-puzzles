package tk.vivas.adventofcode.year2023.day04;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class ScratchCardPile {

    private final Map<Integer, ScratchCard> scratchCardMap;

    ScratchCardPile(String input) {
        scratchCardMap = input.lines()
                .map(ScratchCard::new)
                .collect(Collectors.toMap(ScratchCard::getCardNumber, Function.identity()));
    }

    int score() {
        return scratchCardMap.values().stream()
                .mapToInt(ScratchCard::score)
                .sum();
    }

    long countAllCards() {
        CountingMap<Integer> scratchCardCounts = new CountingMap<>();
        scratchCardMap.keySet().forEach(cardNumber -> scratchCardCounts.put(cardNumber, 1));

        for (ScratchCard scratchCard : scratchCardMap.values()) {
            Integer multiplier = scratchCardCounts.get(scratchCard.getCardNumber());
            scratchCard.getWinningCardNumbers()
                    .forEach(winningCard -> scratchCardCounts.add(winningCard, multiplier));
        }
        return scratchCardCounts.values().stream()
                .mapToInt(i -> i)
                .sum();
    }
}
