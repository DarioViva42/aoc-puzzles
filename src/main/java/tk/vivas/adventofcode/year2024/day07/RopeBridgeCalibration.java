package tk.vivas.adventofcode.year2024.day07;

import java.util.List;

class RopeBridgeCalibration {

	private final List<Equation> equations;

	RopeBridgeCalibration(String input) {
		equations = input.lines()
				.map(Equation::new)
				.toList();
	}

	private long totalCalibrationResult(int uniqueOperators) {
		return equations.stream()
				.filter(equation -> equation.canBeSolved(uniqueOperators))
				.mapToLong(Equation::getResult)
				.sum();
	}

	long limitedCalibrationResult() {
		return totalCalibrationResult(2);
	}

	long extendedCalibrationResult() {
		return totalCalibrationResult(3);
	}
}
