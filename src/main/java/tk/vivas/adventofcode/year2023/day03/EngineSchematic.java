package tk.vivas.adventofcode.year2023.day03;

import java.util.ArrayList;
import java.util.List;
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
                .anyMatch(positionedSymbol ->
                        Math.abs(positionedNumber.getY() - positionedSymbol.getY()) <= 1
                                && positionedNumber.getLeft() - 1 <= positionedSymbol.getX()
                                && positionedNumber.getRight() >= positionedSymbol.getX());
    }
}
