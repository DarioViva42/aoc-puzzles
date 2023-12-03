package tk.vivas.adventofcode.year2023.day03;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

class EngineSchematic {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    private static final Pattern SYMBOL_PATTERN = Pattern.compile("[^\\d.]");
    private final List<PositionedNumber> positionedNumberList;
    private final List<PositionedSymbol> positionedSymbolList;

    EngineSchematic(String input) {

        positionedNumberList = new ArrayList<>();
        positionedSymbolList = new ArrayList<>();

        List<String> lines = input.lines().toList();

        for (int i = 0; i < lines.size(); i++) {
            int row = i;
            String line = lines.get(row);
            NUMBER_PATTERN.matcher(line).results()
                    .map(matchResult -> new PositionedNumber(row, matchResult))
                    .forEach(positionedNumberList::add);
            SYMBOL_PATTERN.matcher(line).results()
                    .map(matchResult -> new PositionedSymbol(row, matchResult))
                    .forEach(positionedSymbolList::add);
        }
    }

    int calculatePartNumberSum() {
        return positionedNumberList.stream()
                .filter(this::touchesSymbol)
                .mapToInt(PositionedNumber::getValue)
                .sum();
    }

    private boolean touchesSymbol(PositionedNumber positionedNumber) {
        return positionedSymbolList.stream()
                .anyMatch(positionedSymbol -> doPartsTouch(positionedSymbol, positionedNumber));
    }

    public long calculateGearRatioSum() {
        return positionedSymbolList.stream()
                .filter(PositionedSymbol::isPotentialGear)
                .map(this::touchingPartNumbers)
                .filter(Objects::nonNull)
                .mapToLong(this::calculateGearRatio)
                .sum();
    }

    private long calculateGearRatio(Pair<PositionedNumber, PositionedNumber> partNumberPair) {
        return (long) partNumberPair.getLeft().getValue() * partNumberPair.getRight().getValue();
    }

    private Pair<PositionedNumber, PositionedNumber> touchingPartNumbers(PositionedSymbol potentialGear) {
        List<PositionedNumber> touchingPartNumbers = positionedNumberList.stream()
                .filter(positionedNumber -> doPartsTouch(potentialGear, positionedNumber))
                .toList();
        if (touchingPartNumbers.size() == 2) {
            return Pair.of(touchingPartNumbers.get(0), touchingPartNumbers.get(1));
        }
        return null;
    }

    private static boolean doPartsTouch(PositionedSymbol potentialGear, PositionedNumber positionedNumber) {
        return Math.abs(positionedNumber.getY() - potentialGear.getY()) <= 1
                && positionedNumber.getLeft() - 1 <= potentialGear.getX()
                && positionedNumber.getRight() >= potentialGear.getX();
    }
}
