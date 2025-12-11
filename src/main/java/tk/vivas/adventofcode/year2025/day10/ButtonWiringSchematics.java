package tk.vivas.adventofcode.year2025.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ButtonWiringSchematics {

    private final List<Button> buttons;

    public ButtonWiringSchematics(String input) {
        buttons = Arrays.stream(input.split(" "))
                .map(Button::new)
                .toList();
    }

    Stream<List<Button>> allButtonCombinations() {
        return IntStream.rangeClosed(1, buttons.size())
                .mapToObj(this::buttonCombinationsWithSize)
                .flatMap(Function.identity());
    }

    private Stream<List<Button>> buttonCombinationsWithSize(int size) {
        return recursive(List.of(), 0, size);
    }

    Stream<List<Button>> recursive(List<Button> listInProgress, int startIndex, int leftOverCount) {
        if (leftOverCount == 1) {
            return IntStream.rangeClosed(startIndex, buttons.size() - leftOverCount)
                    .mapToObj(buttons::get)
                    .map(button -> concat(listInProgress, button));
        }
        return IntStream.rangeClosed(startIndex, buttons.size() - leftOverCount)
                .boxed()
                .flatMap(index -> {
                    Button button = buttons.get(index);
                    return recursive(concat(listInProgress, button), index + 1, leftOverCount - 1);
                });
    }

    private List<Button> concat(List<Button> list, Button number) {
        List<Button> concatenated = new ArrayList<>(list.size() + 1);
        concatenated.addAll(list);
        concatenated.add(number);
        return concatenated;
    }

    @Override
    public String toString() {
        return buttons.stream()
                .map(Button::toString)
                .collect(Collectors.joining(" "));
    }
}
