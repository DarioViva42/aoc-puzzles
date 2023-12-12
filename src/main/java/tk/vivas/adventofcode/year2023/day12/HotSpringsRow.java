package tk.vivas.adventofcode.year2023.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static tk.vivas.adventofcode.year2023.day12.SpringState.DAMAGED;
import static tk.vivas.adventofcode.year2023.day12.SpringState.OPERATIONAL;
import static tk.vivas.adventofcode.year2023.day12.SpringState.UNKNOWN;

class HotSpringsRow {

    private final List<SpringState> springStates;
    private final List<Integer> expectedGroupSizes;

    HotSpringsRow(List<SpringState> springStates, List<Integer> expectedGroupSizes) {
        this.springStates = springStates;
        this.expectedGroupSizes = expectedGroupSizes;
    }

    static HotSpringsRow from(String raw) {
        String[] split = raw.split(" ", 2);
        List<SpringState> springStates = split[0].chars()
                .mapToObj(character -> (char) character)
                .map(SpringState::from)
                .toList();
        List<Integer> groupSizes = Arrays.stream(split[1].split(","))
                .map(Integer::parseInt)
                .toList();
        return new HotSpringsRow(springStates, groupSizes);
    }

    long countArrangements() {
        List<Integer> unknownPositions = new ArrayList<>();
        for (int i = 0; i < springStates.size(); i++) {
            if (UNKNOWN == springStates.get(i)) {
                unknownPositions.add(i);
            }
        }
        return Stream.iterate(0, index -> index < Math.pow(2, unknownPositions.size()), i -> i + 1)
                .map(index -> createRowWithReplacedPlaceholders(index, unknownPositions))
                .filter(HotSpringsRow::isCorrect)
                .count();
    }

    private HotSpringsRow createRowWithReplacedPlaceholders(Integer lookup, List<Integer> unknownPositions) {
        List<SpringState> createdStates = new ArrayList<>(springStates);
        for (Integer unknownPosition : unknownPositions) {
            SpringState replacement = (lookup % 2 == 0) ? OPERATIONAL : DAMAGED;
            createdStates.set(unknownPosition, replacement);
            lookup >>= 1;
        }
        return new HotSpringsRow(createdStates, expectedGroupSizes);
    }

    private boolean isCorrect() {
        boolean containsUnknowns = springStates.stream().anyMatch(UNKNOWN::equals);
        if (containsUnknowns) {
            return false;
        }

        List<Integer> actualGroupSizes = new ArrayList<>();

        int index = -1;
        boolean shouldBuildNewGroup = true;
        for (SpringState springState : springStates) {
            if (DAMAGED == springState) {
                if (shouldBuildNewGroup) {
                    actualGroupSizes.add(1);
                    shouldBuildNewGroup = false;
                    index++;
                } else {
                    int last = actualGroupSizes.get(index);
                    actualGroupSizes.set(index, last + 1);
                }
            } else {
                shouldBuildNewGroup = true;
            }
        }

        return actualGroupSizes.equals(expectedGroupSizes);
    }

    @Override
    public String toString() {
        String stateString = springStates.stream()
                .map(SpringState::toString)
                .collect(Collectors.joining(""));
        String groupSizeString = expectedGroupSizes.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        return "%s %s".formatted(stateString, groupSizeString);
    }

    HotSpringsRow unfold() {
        return new HotSpringsRow(unfoldSpringStates(), unfoldGroupSizes());
    }

    private List<SpringState> unfoldSpringStates() {
        List<SpringState> unfoldedList = new ArrayList<>(springStates);
        
        for (int i = 0; i < 4; i++) {
            unfoldedList.add(UNKNOWN);
            unfoldedList.addAll(springStates);
        }
        
        return unfoldedList;
    }

    private List<Integer> unfoldGroupSizes() {
        List<Integer> unfoldedList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            unfoldedList.addAll(expectedGroupSizes);
        }
        return unfoldedList;
    }
}
