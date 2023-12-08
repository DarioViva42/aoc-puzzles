package tk.vivas.adventofcode.year2023.day07;

import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static tk.vivas.adventofcode.year2023.day07.HandType.FIVE_OF_A_KIND;
import static tk.vivas.adventofcode.year2023.day07.HandType.FOUR_OF_A_KIND;
import static tk.vivas.adventofcode.year2023.day07.HandType.FULL_HOUSE;
import static tk.vivas.adventofcode.year2023.day07.HandType.HIGH_CARD;
import static tk.vivas.adventofcode.year2023.day07.HandType.ONE_PAIR;
import static tk.vivas.adventofcode.year2023.day07.HandType.THREE_OF_A_KIND;
import static tk.vivas.adventofcode.year2023.day07.HandType.TWO_PAIR;

class Hand implements Comparable<Hand> {

	private final int bidAmount;
	private final List<CamelCard> cards;
	private Map<CamelCard, Integer> cardMapCache;

	Hand(String raw) {
		String[] split = raw.split(" ");

		cards = split[0].chars()
				.mapToObj(c -> (char) c)
				.map(CamelCard::from)
				.toList();

		bidAmount = Integer.parseInt(split[1]);
	}

	public int getBidAmount() {
		return bidAmount;
	}

	private HandType type() {
		Map<CamelCard, Integer> cardMap = getCardMap();
		Collection<Integer> values = cardMap.values();
		return switch (values.size()) {
			case 1 -> FIVE_OF_A_KIND;
			case 2 -> {
				if (values.contains(4)) {
					yield FOUR_OF_A_KIND;
				}
				yield FULL_HOUSE;
			}
			case 3 -> {
				if (values.contains(3)) {
					yield THREE_OF_A_KIND;
				}
				yield TWO_PAIR;
			}
			case 4 -> ONE_PAIR;
			case 5 -> HIGH_CARD;
			default -> throw new IllegalStateException("Unexpected value: " + values.size());
		};
	}

	private Map<CamelCard, Integer> getCardMap() {
		if (cardMapCache != null) {
			return cardMapCache;
		}
		Map<CamelCard, Integer> cardMap = new EnumMap<>(CamelCard.class);
		cards.forEach(card -> {
			if (cardMap.containsKey(card)) {
				cardMap.put(card, cardMap.get(card) + 1);
			} else {
				cardMap.put(card, 1);
			}
		});
		cardMapCache = cardMap;
		return cardMap;
	}

	@Override
	public int compareTo(Hand o) {
		int thisTypeOrdinal = type().ordinal();
		int otherTypeOrdinal = o.type().ordinal();
		if (thisTypeOrdinal != otherTypeOrdinal) {
			return thisTypeOrdinal - otherTypeOrdinal;
		}
		for (int i = 0; i < cards.size(); i++) {
			int compared = cards.get(i).ordinal() - o.cards.get(i).ordinal();
			if (compared != 0) {
				return compared;
			}
		}
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Hand hand = (Hand) o;
		return
				bidAmount == hand.bidAmount
				&& cards.equals(hand.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bidAmount, cards);
	}
}
