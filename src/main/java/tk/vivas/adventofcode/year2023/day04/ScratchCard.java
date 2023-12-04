package tk.vivas.adventofcode.year2023.day04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class ScratchCard {

    private final int cardNumber;
    private final long countOwnedWinningNumbers;
    private final List<Integer> winningCardNumbers;

    ScratchCard(String raw) {
        String[] split = raw.split(": +");
        cardNumber = Integer.parseInt(split[0].split(" +")[1]);

        String[] numbers = split[1].split(" \\| +");
        List<String> winningNumbers = Arrays.asList(numbers[0].split(" +"));
        List<String> ownedNumbers = Arrays.asList(numbers[1].split(" +"));

        countOwnedWinningNumbers = countOwnedWinningNumbers(winningNumbers, ownedNumbers);
        winningCardNumbers = Stream.iterate(cardNumber + 1, e -> e <= cardNumber + countOwnedWinningNumbers, e -> e + 1)
                .toList();
    }

    private long countOwnedWinningNumbers(List<String> winningNumbers, List<String> ownedNumbers) {
        return winningNumbers.stream()
                .filter(ownedNumbers::contains)
                .count();
    }

    int score() {
        if (countOwnedWinningNumbers == 0) {
            return 0;
        }
        return 1 << (countOwnedWinningNumbers - 1);
    }

    int getCardNumber() {
        return cardNumber;
    }
    
    List<Integer> getWinningCardNumbers() {
        return winningCardNumbers;
    }
}
