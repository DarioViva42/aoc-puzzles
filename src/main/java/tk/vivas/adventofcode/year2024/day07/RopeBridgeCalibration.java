package tk.vivas.adventofcode.year2024.day07;

import java.util.List;

class RopeBridgeCalibration {

	private final List<Equation> equations;

	RopeBridgeCalibration(String input) {
		equations = input.lines()
				.map(Equation::new)
				.toList();
	}

	private long totalCalibrationResult(Operator... usedOperators) {
		return equations.stream()
				.filter(equation -> equation.canBeSolved(usedOperators))
				.mapToLong(Equation::getResult)
				.sum();
	}

	long limitedCalibrationResult() {
		return totalCalibrationResult(
				Operator.PLUS, Operator.MULTIPLY);
	}

	long extendedCalibrationResult() {
		return totalCalibrationResult(
				Operator.PLUS, Operator.MULTIPLY, Operator.CONCATENATE);
	}
}
