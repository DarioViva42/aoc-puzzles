package tk.vivas.adventofcode.year2023.day04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScratchCardPile {

    private final Map<Integer, ScratchCard> scratchCardMap;

    public ScratchCardPile(String input) {
        scratchCardMap = input.lines()
                .map(ScratchCard::new)
                .collect(Collectors.toMap(ScratchCard::getCardNumber, Function.identity()));
    }

    public int score() {
        return scratchCardMap.values().stream()
                .mapToInt(ScratchCard::score)
                .sum();
    }

    public long countAllCards() {
        List<ScratchCard> growingCardList = new ArrayList<>(scratchCardMap.values());
        
        winCards(growingCardList, scratchCardMap.values());
        
        return growingCardList.size();
    }

    private void winCards(List<ScratchCard> growingCardList, Collection<ScratchCard> scratchCards) {
        List<ScratchCard> wonScratchCards = scratchCards.stream()
                .map(ScratchCard::getWinningCardNumbers)
                .flatMap(Collection::stream)
                .map(scratchCardMap::get)
                .toList();
        growingCardList.addAll(wonScratchCards);

        if (!wonScratchCards.isEmpty()) {
            winCards(growingCardList, wonScratchCards);
        }
    }
}
