package tk.vivas.adventofcode.year2023.day01;

import java.util.List;

class AmendedCalibrationValue {

    private final List<Integer> digits;

    AmendedCalibrationValue(String amendedCalibrationValue) {
        digits = amendedCalibrationValue.chars()
                .mapToObj(e -> (char) e)
                .map(Object::toString)
                .filter(character -> character.matches("\\d"))
                .map(Integer::valueOf)
                .toList();
    }
    
    int recover() {
        return 10 * digits.getFirst() + digits.getLast();
    }
}
