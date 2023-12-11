package tk.vivas.adventofcode.year2023.day07;

import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static tk.vivas.adventofcode.year2023.day07.CamelCard.JOKER;
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
	private HandType handTypeCache;

	public Hand(int bidAmount, List<CamelCard> cards) {
		this.bidAmount = bidAmount;
		this.cards = cards;
	}

	static Hand of(String raw) {
		String[] split = raw.split(" ");

		List<CamelCard> cards = split[0].chars()
				.mapToObj(c -> (char) c)
				.map(CamelCard::from)
				.toList();

		int bidAmount = Integer.parseInt(split[1]);

		return new Hand(bidAmount, cards);
	}

	public int getBidAmount() {
		return bidAmount;
	}

	private HandType type() {
		if (handTypeCache != null) {
			return handTypeCache;
		}
		Map<CamelCard, Integer> cardMap = getCardMap();
		Collection<Integer> values = cardMap.values();
		HandType handType = switch (values.size()) {
			case 1 -> FIVE_OF_A_KIND;
			case 2 -> values.contains(4) ? FOUR_OF_A_KIND : FULL_HOUSE;
			case 3 -> values.contains(3) ? THREE_OF_A_KIND : TWO_PAIR;
			case 4 -> ONE_PAIR;
			case 5 -> HIGH_CARD;
			default -> throw new IllegalStateException("Unexpected value: " + values.size());
		};
		handTypeCache = upgradeHandType(handType);
		return handTypeCache;
	}

	private HandType upgradeHandType(HandType normalHandType) {
		if (!cards.contains(JOKER)) {
			return normalHandType;
		}
		return switch (normalHandType) {
			case HIGH_CARD -> ONE_PAIR;
			case ONE_PAIR -> THREE_OF_A_KIND;
			case TWO_PAIR -> countJokers() == 2 ? FOUR_OF_A_KIND : FULL_HOUSE;
			case THREE_OF_A_KIND -> FOUR_OF_A_KIND;
			case FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND -> FIVE_OF_A_KIND;
		};
	}

	private long countJokers() {
		return cards.stream()
				.filter(JOKER::equals)
				.count();
	}

	private Map<CamelCard, Integer> getCardMap() {
		Map<CamelCard, Integer> cardMap = new EnumMap<>(CamelCard.class);
		cards.forEach(card -> {
			if (cardMap.containsKey(card)) {
				cardMap.put(card, cardMap.get(card) + 1);
			} else {
				cardMap.put(card, 1);
			}
		});
		return cardMap;
	}

	Hand replaceJackWithJoker() {
		if (!cards.contains(CamelCard.CJ)) {
			return this;
		}
		List<CamelCard> modifiedCards = cards.stream()
				.map(card -> card == CamelCard.CJ ? JOKER : card)
				.toList();

		return new Hand(bidAmount, modifiedCards);
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

	@Override
	public String toString() {
		return "%s %s %s".formatted(
				cards.stream()
						.map(card -> card == JOKER ? "*" : card.name().substring(1))
						.collect(Collectors.joining()),
				type(),
				bidAmount
		);
	}
}
