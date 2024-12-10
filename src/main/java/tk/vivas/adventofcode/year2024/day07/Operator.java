package tk.vivas.adventofcode.year2024.day07;

enum Operator {
	PLUS, MULTIPLY;

	static Operator from(int option) {
		return switch (option) {
			case '0' -> PLUS;
			case '1' -> MULTIPLY;
			default -> throw new IllegalArgumentException("Unknown option: " + option);
		};
	}
}
