package tk.vivas.adventofcode.year2023.day07;

import java.util.List;

class CamelCardsGame {

	private final List<Hand> hands;

	CamelCardsGame(String input) {
		hands = input.lines()
				.map(Hand::of)
				.toList();
	}

	long totalWinnings() {
		List<Hand> sortedHands = hands.stream()
				.sorted()
				.toList();

		long totalWinnings = 0L;
		for (int i = 0; i < sortedHands.size(); i++) {
			long rank = i + 1L;
			totalWinnings += sortedHands.get(i).getBidAmount() * rank;
		}
		return totalWinnings;
	}

	long totalWinningsWithJoker() {
		List<Hand> sortedHands = hands.stream()
				.map(Hand::replaceJackWithJoker)
				.sorted()
				.toList();

		long totalWinnings = 0L;
		for (int i = 0; i < sortedHands.size(); i++) {
			long rank = i + 1L;
			totalWinnings += sortedHands.get(i).getBidAmount() * rank;
		}
		return totalWinnings;
	}
}
