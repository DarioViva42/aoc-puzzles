package tk.vivas.adventofcode.year2024.day07;

import tk.vivas.MathUtils;

import java.util.Arrays;

class Equation {

	private final long result;
	private final long[] components;

	Equation(String line) {
		String[] split = line.split(": ");
		result = Long.parseLong(split[0]);
		components = Arrays.stream(split[1].split(" "))
				.mapToLong(Long::parseLong)
				.toArray();
	}

	boolean canBeSolved(int uniqueOperators) {
		int numberOfNeededOperators = components.length - 1;
		return new OperatorGenerator(numberOfNeededOperators, uniqueOperators).generate()
				.anyMatch(this::isSolved);
	}

	private boolean isSolved(Operator[] operators) {
		long calculatedResult = components[0];
		for (int i = 0; i < operators.length; i++) {
			switch (operators[i]) {
				case PLUS -> calculatedResult += components[i + 1];
				case MULTIPLY -> calculatedResult *= components[i + 1];
				case CONCATENATE -> calculatedResult = MathUtils.concatenate(calculatedResult, components[i + 1]);
			}
		}
		return calculatedResult == result;
	}

	long getResult() {
		return result;
	}
}
