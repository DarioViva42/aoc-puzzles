package tk.vivas.adventofcode.year2023.day07;

enum CamelCard {
	C2, C3, C4, C5, C6, C7, C8, C9, CT, CJ, CQ, CK, CA;

	static CamelCard from(Character character) {
		return CamelCard.valueOf("C" + character);
	}
}
