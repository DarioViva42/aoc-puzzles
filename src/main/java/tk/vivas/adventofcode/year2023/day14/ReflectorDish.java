package tk.vivas.adventofcode.year2023.day14;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static tk.vivas.adventofcode.year2023.day14.CoverType.EMPTY_SPACE;
import static tk.vivas.adventofcode.year2023.day14.CoverType.ROUNDED_ROCK;

class ReflectorDish {

    private final CoverType[][] dish2dArray;
    private final int dishSizeX;
    private final int dishSizeY;

    ReflectorDish(String input) {
        dish2dArray = input.lines()
                .map(line -> line.chars()
                        .mapToObj(c -> (char) c)
                        .map(CoverType::from)
                        .toArray(CoverType[]::new))
                .toArray(CoverType[][]::new);
        dishSizeX = dish2dArray[0].length;
        dishSizeY = dish2dArray.length;
    }

    long totalLoad() {
        tiltNorth();
        return Stream.iterate(0, i -> i < dishSizeY, i -> i + 1)
                .mapToLong(i -> {
                    int multiplier = dishSizeY - i;
                    long rockCount = Arrays.stream(dish2dArray[i])
                            .filter(ROUNDED_ROCK::equals)
                            .count();
                    return multiplier * rockCount;
                })
                .sum();
    }

    private void tiltNorth() {
        Stream.iterate(dishSizeY - 2, i -> i >= 0, i -> i - 1)
                .flatMap(y -> Stream.iterate(0, i -> i <= y, i -> i + 1))
                .forEach(y -> {
                    CoverType[] northLine = dish2dArray[y];
                    CoverType[] southLine = dish2dArray[y + 1];

                    Stream.iterate(0, i -> i < dishSizeX, i -> i + 1)
                            .filter(x -> (northLine[x] == EMPTY_SPACE && southLine[x] == ROUNDED_ROCK))
                            .forEach(x -> {
                                northLine[x] = ROUNDED_ROCK;
                                southLine[x] = EMPTY_SPACE;
                            });
                });
    }

    @Override
    public String toString() {
        return Arrays.stream(dish2dArray)
                .map(line -> Arrays.stream(line)
                        .map(CoverType::toString)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("\n"));
    }
}
