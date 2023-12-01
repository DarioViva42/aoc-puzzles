package tk.vivas.adventofcode.day11;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;

class Monkey {
    private final Deque<Long> items;
    private int inspectionCount;
    private final LongUnaryOperator operation;
    private final int divisionNumber;
    private final int trueIndex;
    private final int falseIndex;

    private Monkey trueMonkey;
    private Monkey falseMonkey;

    public Monkey(Deque<Long> items,
                  LongUnaryOperator operation,
                  int divisionNumber,
                  int trueIndex, int falseIndex) {
        this.items = items;
        this.operation = operation;
        this.divisionNumber = divisionNumber;
        this.trueIndex = trueIndex;
        this.falseIndex = falseIndex;
        this.inspectionCount = 0;
    }

    public void throwItems(LongUnaryOperator relief) {
        while (hasItems()) {
            long item = inspectItem();
            item = relief.applyAsLong(item);
            throwItem(item);
        }
    }

    public int getInspectionCount() {
        return inspectionCount;
    }

    public static Monkey parse(String input) {
        List<String> lines = input.lines().toList();

        ArrayDeque<Long> itemDeque = parseItems(lines.get(1));
        LongUnaryOperator worryOperation = parseOperation(lines.get(2));
        int divisionNumber = Integer.parseInt(lines.get(3).substring(21));
        int trueIndex = Integer.parseInt(lines.get(4).substring(29));
        int falseIndex = Integer.parseInt(lines.get(5).substring(30));

        return new Monkey(itemDeque, worryOperation, divisionNumber, trueIndex, falseIndex);
    }

    private static ArrayDeque<Long> parseItems(String input) {
        String[] itemArray = input
                .substring(18)
                .split(", ");
        return Arrays.stream(itemArray)
                .map(Long::parseLong)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private static LongUnaryOperator parseOperation(String input) {
        char operationSymbol = input.charAt(23);
        String operand = input.substring(25);
        if (operationSymbol == '+') {
            if (operand.equals("old")) {
                return i -> 2 * i;
            }
            int numberOperand = Integer.parseInt(operand);
            return i -> i + numberOperand;
        }
        if (operand.equals("old")) {
            return i -> i * i;
        }
        int numberOperand = Integer.parseInt(operand);
        return i -> i * numberOperand;
    }

    public void prepare(List<Monkey> monkeys) {
        trueMonkey = monkeys.get(trueIndex);
        falseMonkey = monkeys.get(falseIndex);
    }

    public boolean hasItems() {
        return !items.isEmpty();
    }

    public long inspectItem() {
        inspectionCount++;
        long item = items.removeFirst();
        item = operation.applyAsLong(item);
        return item;
    }

    public void throwItem(long item) {
        if (item % divisionNumber == 0) {
            trueMonkey.takeItem(item);
        } else {
            falseMonkey.takeItem(item);
        }
    }

    private void takeItem(long item) {
        items.addLast(item);
    }

    public int getDivisionNumber() {
        return divisionNumber;
    }
}
