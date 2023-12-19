package tk.vivas.adventofcode.year2023.day19;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

class WorkflowStep {
    private final Predicate<MachinePart> predicate;
    private final String classificationString;
    private final char rating;
    private final char operator;
    private final int number;

    private Predicate<MachinePart> classification;
    private MachineWorkflow nextWorkflow;

    WorkflowStep(Predicate<MachinePart> predicate, String classificationString,
                 char rating, char operator, int number) {
        this.predicate = predicate;
        this.classificationString = classificationString;
        this.rating = rating;
        this.operator = operator;
        this.number = number;
    }

    WorkflowStep(Predicate<MachinePart> predicate, String classificationString) {
        this.predicate = predicate;
        this.classificationString = classificationString;
        this.rating = (char) 0;
        this.operator = (char) 0;
        this.number = 0;
    }

    static WorkflowStep from(String raw) {
        if (!raw.contains(":")) {
            Predicate<MachinePart> alwaysTrue = part -> true;
            return new WorkflowStep(alwaysTrue, raw);
        }
        String[] split = raw.split(":");
        String predicatePart = split[0];
        char ratingIdentifier = predicatePart.charAt(0);
        ToIntFunction<MachinePart> function = machinePart -> switch (ratingIdentifier) {
            case 'x' -> machinePart.x();
            case 'm' -> machinePart.m();
            case 'a' -> machinePart.a();
            case 's' -> machinePart.s();
            default -> throw new IllegalStateException("Unexpected value: " + ratingIdentifier);
        };
        int number = Integer.parseInt(predicatePart.substring(2));
        char operator = predicatePart.charAt(1);
        Predicate<MachinePart> predicate = machinePart -> switch (operator) {
            case '>' -> function.applyAsInt(machinePart) > number;
            case '<' -> function.applyAsInt(machinePart) < number;
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
        return new WorkflowStep(predicate, split[1], ratingIdentifier, operator, number);
    }

    void init(Map<String, MachineWorkflow> workflowMap) {
        nextWorkflow = workflowMap.get(classificationString);
        classification = switch (classificationString) {
            case "A" -> machinePart -> true;
            case "R" -> machinePart -> false;
            default -> nextWorkflow::classify;
        };
    }

    boolean test(MachinePart part) {
        return predicate.test(part);
    }

    boolean classify(MachinePart part) {
        return classification.test(part);
    }

    char getRating() {
        return rating;
    }

    char getOperator() {
        return operator;
    }

    int getNumber() {
        return number;
    }

    String getClassificationString() {
        return classificationString;
    }

    MachineWorkflow getNextWorkflow() {
        return nextWorkflow;
    }

    @Override
    public String toString() {
        if (rating == 0) {
            return classificationString;
        }
        return "%s%s%s:%s".formatted(rating, operator, number, classificationString);
    }
}
