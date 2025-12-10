package tk.vivas.adventofcode.year2025.day09;

import tk.vivas.Position;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.ToIntFunction;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class MovieTheater {

    private final List<ModifiablePosition> tilePositions;
    private final List<TileSquare> squareOptions;

    MovieTheater(String input) {
        tilePositions = input.lines()
                .map(this::parsePosition)
                .toList();

        squareOptions = IntStream.range(0, tilePositions.size() - 1)
                .mapToObj(i -> {
                    ModifiablePosition a = tilePositions.get(i);
                    return IntStream.range(i + 1, tilePositions.size())
                            .mapToObj(tilePositions::get)
                            .map(b -> new TileSquare(a, b));
                })
                .flatMap(Function.identity())
                .toList();
    }

    private ModifiablePosition parsePosition(String line) {
        String[] split = line.split(",", 2);
        return new ModifiablePosition(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    long largestRectangle() {
        return squareOptions.stream()
                .mapToLong(TileSquare::area)
                .max().orElseThrow();
    }

    long largestValidRectangle() {
        Position size = squishTiles();

        byte[][] map = createMap(size);

        return squareOptions.stream()
                .filter(option -> option.check(map))
                .mapToLong(TileSquare::area)
                .max().orElseThrow();
    }

    private Position squishTiles() {
        int maxX = squishTiles(ModifiablePosition::getX, ModifiablePosition::setX);
        int maxY = squishTiles(ModifiablePosition::getY, ModifiablePosition::setY);

        return new Position(maxX, maxY);
    }

    private int squishTiles(
            ToIntFunction<ModifiablePosition> getter,
            ObjIntConsumer<ModifiablePosition> setter) {

        List<ModifiablePosition> sorted = tilePositions.stream()
                .sorted(Comparator.comparingInt(getter))
                .toList();

        int temp = -1;
        int last = Integer.MIN_VALUE;
        for (ModifiablePosition position : sorted) {
            int newX = getter.applyAsInt(position);
            if (newX - last == 1) {
                temp++;
            } else if (newX > last) {
                temp += 2;
            }
            setter.accept(position, temp);
            last = newX;
        }
        return temp + 1;
    }

    /**
     * This method creates a 2D map of tiles.
     *
     * <ul>
     *     <li> Empty tiles are marked with 0
     *     <li> Red tiles are marked with 1
     *     <li> Green tiles on an edge are marked with 2
     *     <li> Other green tiles are marked with 3
     * </ul>
     *
     * @param size Position marking the bottom-right corner
     * @return A map of tiles
     */
    private byte[][] createMap(Position size) {
        byte[][] map = new byte[size.y()][size.x()];

        drawLines(map);
        fill(map);

        return map;
    }

    private void drawLines(byte[][] map) {
        Stream.Builder<ModifiablePosition> sb = Stream.builder();
        tilePositions.forEach(sb::add);
        sb.add(tilePositions.getFirst());

        sb.build()
                .gather(Gatherers.windowSliding(2))
                .forEach(positionPair -> {
                    ModifiablePosition a = positionPair.getFirst();
                    ModifiablePosition b = positionPair.getLast();

                    drawLine(map, a, b);
                    map[b.getY()][b.getX()] = 1;
                });
    }

    private void drawLine(byte[][] map, ModifiablePosition a, ModifiablePosition b) {
        if (a.getX() == b.getX()) {
            drawVerticalLine(map, a.getX(), a.getY(), b.getY());
        } else {
            drawHorizontalLine(map, a.getY(), a.getX(), b.getX());
        }
    }

    private void drawVerticalLine(byte[][] map, int x, int start, int end) {
        if (end < start) {
            drawVerticalLine(map, x, end, start);
            return;
        }
        IntStream.range(start + 1, end)
                .forEach(y -> map[y][x] = 2);
    }

    private void drawHorizontalLine(byte[][] map, int y, int start, int end) {
        if (end < start) {
            drawHorizontalLine(map, y, end, start);
            return;
        }
        IntStream.range(start + 1, end)
                .forEach(x -> map[y][x] = 2);
    }

    private void fill(byte[][] map) {

        boolean penDown = false;
        boolean onLine = false;

        // └ = true, ┌ = false
        boolean startCurve = false;

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                byte tile = map[y][x];
                if (penDown && tile == 0) {
                    map[y][x] = 3;
                }
                // on red corner tile
                if (tile == 1) {
                    byte topNeighbour = map[y - 1][x];
                    boolean curve = topNeighbour == 2 || topNeighbour == 1;
                    if (!onLine) {
                        // on left side of line
                        startCurve = curve;
                    } else {
                        // on right side of line
                        if (curve != startCurve) {
                            // └─┐ and ┌─┘ change pen state
                            penDown = !penDown;
                        }
                    }
                    onLine = !onLine;
                }
                // │ changes pen state
                if (tile == 2 && !onLine) {
                    penDown = !penDown;
                }
            }
        }
    }
}
