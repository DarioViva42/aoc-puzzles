package vivas.tk.adventofcode.day11;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

class Monkey {
    private final Deque<Integer> items;
    private int inspectionCount;
    private final UnaryOperator<Integer> operation;
    private final Predicate<Integer> test;
    private final int trueIndex;
    private final int falseIndex;

    private Monkey trueMonkey;
    private Monkey falseMonkey;

    public Monkey(Deque<Integer> items,
                  UnaryOperator<Integer> operation,
                  Predicate<Integer> test,
                  int trueIndex, int falseIndex) {
        this.items = items;
        this.operation = operation;
        this.test = test;
        this.trueIndex = trueIndex;
        this.falseIndex = falseIndex;
        this.inspectionCount = 0;
    }

    public int getInspectionCount() {
        return inspectionCount;
    }

    public static Monkey parse(String input) {
        List<String> lines = input.lines().toList();

        ArrayDeque<Integer> itemDeque = parseItems(lines.get(1));
        UnaryOperator<Integer> worryOperation = parseOperation(lines.get(2));
        Predicate<Integer> divisionTest = parseTest(lines.get(3));
        int trueIndex = Integer.parseInt(lines.get(4).substring(29));
        int falseIndex = Integer.parseInt(lines.get(5).substring(30));

        return new Monkey(itemDeque, worryOperation, divisionTest, trueIndex, falseIndex);
    }

    private static ArrayDeque<Integer> parseItems(String input) {
        String[] itemArray = input
                .substring(18)
                .split(", ");
        return Arrays.stream(itemArray)
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private static UnaryOperator<Integer> parseOperation(String input) {
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

    private static Predicate<Integer> parseTest(String input) {
        int divisionNumber = Integer.parseInt(input.substring(21));
        return i -> i % divisionNumber == 0;
    }

    public void prepare(List<Monkey> monkeys) {
        trueMonkey = monkeys.get(trueIndex);
        falseMonkey = monkeys.get(falseIndex);
    }

    public void play() {
        while (!items.isEmpty()) {
            inspectionCount++;
            int item = items.removeFirst();
            item = operation.apply(item);
            item /= 3;
            if (test.test(item)) {
                trueMonkey.takeItem(item);
            } else {
                falseMonkey.takeItem(item);
            }
        }
    }

    private void takeItem(int item) {
        items.addLast(item);
    }
}
