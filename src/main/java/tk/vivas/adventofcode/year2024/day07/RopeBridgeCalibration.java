package tk.vivas.adventofcode.year2024.day07;

import java.util.List;

class RopeBridgeCalibration {

	private final List<Equation> equations;

	RopeBridgeCalibration(String input) {
		equations = input.lines()
				.map(Equation::new)
				.toList();
	}

	long totalCalibrationResult() {
		return equations.stream()
				.filter(Equation::canBeSolved)
				.mapToLong(Equation::getResult)
				.sum();
	}
}
