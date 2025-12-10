package tk.vivas.adventofcode.year2025.day10;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ButtonWiringSchematics {

    private final List<Button> buttons;

    public ButtonWiringSchematics(String input) {
        buttons = Arrays.stream(input.split(" "))
                .map(Button::new)
                .toList();
    }

    @Override
    public String toString() {
        return buttons.stream()
                .map(Button::toString)
                .collect(Collectors.joining(" "));
    }
}
