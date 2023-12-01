package tk.vivas.adventofcode.year2023.day01;

import java.util.List;

class CalibrationDocument {

    private final List<AmendedCalibrationValue> calibrationValues;

    CalibrationDocument(String input) {
        calibrationValues = input.lines()
                .map(AmendedCalibrationValue::new)
                .toList();
    }

    int recover() {
        return calibrationValues.stream()
                .mapToInt(AmendedCalibrationValue::recover)
                .sum();
    }
}
