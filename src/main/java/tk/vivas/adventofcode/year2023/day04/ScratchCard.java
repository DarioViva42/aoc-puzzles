package tk.vivas.adventofcode.year2023.day04;

import java.util.Arrays;
import java.util.List;

class ScratchCard {

    private final String cardNumber;
    private final List<String> winningNumbers;
    private final List<String> ownedNumbers;

    ScratchCard(String raw) {
        String[] split = raw.split(": +");
        cardNumber = split[0].substring(5);

        String[] numbers = split[1].split(" \\| +");
        winningNumbers = Arrays.asList(numbers[0].split(" +"));
        ownedNumbers = Arrays.asList(numbers[1].split(" +"));
    }

    int score() {
        long countOwnedWinningNumbers = winningNumbers.stream()
                .filter(ownedNumbers::contains)
                .count();
        if (countOwnedWinningNumbers == 0) {
            return 0;
        }
        return 1 << (countOwnedWinningNumbers - 1);
    }

    String getCardNumber() {
        return cardNumber;
    }
}
