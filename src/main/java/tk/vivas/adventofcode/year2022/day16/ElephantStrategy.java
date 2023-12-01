package tk.vivas.adventofcode.year2022.day16;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ElephantStrategy {
    private final int elfScore;
    private final int elephantScore;
    private final List<MegaValve> openedByElf;
    private final List<MegaValve> openedByElephant;

    private Map<MegaValve, Integer> elfMemory;
    private Map<MegaValve, Integer> elephantMemory;
    private final MegaValve elfValve;
    private final MegaValve elephantValve;
    private final int elfStepsNeeded;
    private final int elephantStepsNeeded;
    private final int leftMinutes;

    public ElephantStrategy(MegaValve valve, int leftMinutes) {
        elfScore = 0;
        elephantScore = 0;
        elfValve = valve;
        elephantValve = valve;
        elfStepsNeeded = 0;
        elephantStepsNeeded = 0;
        openedByElf = new ArrayList<>(0);
        openedByElephant = new ArrayList<>(0);
        this.elfMemory = new HashMap<>(0);
        this.elephantMemory = new HashMap<>(0);
        this.leftMinutes = leftMinutes;
    }

    public ElephantStrategy(int leftMinutes, int elfScore, int elephantScore,
                            MegaValve elfValve, MegaValve elephantValve,
                            int elfStepsNeeded, int elephantStepsNeeded,
                            List<MegaValve> openedByElf, List<MegaValve> openedByElephant,
                            Map<MegaValve, Integer> elfMemory, Map<MegaValve, Integer> elephantMemory) {
        this.elfScore = elfScore;
        this.elephantScore = elephantScore;
        this.elfValve = elfValve;
        this.elephantValve = elephantValve;
        this.elfStepsNeeded = elfStepsNeeded;
        this.elephantStepsNeeded = elephantStepsNeeded;
        this.openedByElf = openedByElf;
        this.openedByElephant = openedByElephant;
        this.elfMemory = elfMemory;
        this.elephantMemory = elephantMemory;
        this.leftMinutes = leftMinutes;
    }

    public Optional<ElephantStrategy> getBestStrategy(Map<PositionPair, List<ElephantStrategy>> saveMap) {
        if (leftMinutes == 0) {
            return Optional.of(this);
        }
        List<ElephantStrategy> strategyList = new ArrayList<>();
        if (elfStepsNeeded > 0 && elephantStepsNeeded > 0) {
            bothHeadOn(strategyList);
        } else if (elfStepsNeeded > 0) {
            saveValveToElephantMemory();

            elephantMoves(strategyList);
            elephantOpensValve(strategyList);
        } else if (elephantStepsNeeded > 0) {
            saveValveToElfMemory();

            elfMoves(strategyList);
            elfOpensValve(strategyList);
        } else {
            bothAreAtVertex(saveMap, strategyList);
        }
        return strategyList.stream()
                .map((ElephantStrategy elephantStrategy) -> elephantStrategy.getBestStrategy(saveMap))
                .flatMap(Optional::stream)
                .max(Comparator.comparing(ElephantStrategy::getScore));
    }

    private void bothAreAtVertex(Map<PositionPair, List<ElephantStrategy>> saveMap, List<ElephantStrategy> strategyList) {
        PositionPair positionPair = this.getPosition();
        if (!saveMap.containsKey(positionPair)) {
            List<ElephantStrategy> strategies = new ArrayList<>();
            strategies.add(this);
            saveMap.put(positionPair, strategies);

            bothAreAtVertex(strategyList);
        } else {
            bothAreAtVertex(saveMap, strategyList, positionPair);
        }
    }

    private void bothAreAtVertex(Map<PositionPair, List<ElephantStrategy>> saveMap, List<ElephantStrategy> strategyList, PositionPair positionPair) {
        List<ElephantStrategy> strategies = saveMap.get(positionPair);
        if (strategies.stream().filter(this::isWorse).count() < strategies.size()) {

            List<ElephantStrategy> removeList = new ArrayList<>();
            strategies.stream()
                    .filter(this::isBetter)
                    .forEach(removeList::add);
            removeList.forEach(strategies::remove);
            strategies.add(this);

            bothAreAtVertex(strategyList);
        }
    }

    private void bothAreAtVertex(List<ElephantStrategy> strategyList) {
        saveValveToElfMemory();
        saveValveToElephantMemory();

        bothMove(strategyList);
        elfOpensValveElephantHeadsOn(strategyList);
        elfHeadsOnElephantOpensValve(strategyList);
        bothOpenValve(strategyList);
    }

    private boolean isBetter(ElephantStrategy other) {
        return getScore() >= other.getScore() && leftMinutes >= other.leftMinutes;
    }

    private boolean isWorse(ElephantStrategy other) {
        return getScore() <= other.getScore() && leftMinutes <= other.leftMinutes;
    }

    private PositionPair getPosition() {
        return new PositionPair(elfValve, elephantValve);
    }

    private void saveValveToElephantMemory() {
        Map<MegaValve, Integer> tempElephantMemory = new HashMap<>(this.elephantMemory.size() + 1);
        tempElephantMemory.putAll(this.elephantMemory);
        tempElephantMemory.put(elephantValve, elephantScore);
        this.elephantMemory = tempElephantMemory;
    }

    private void saveValveToElfMemory() {
        Map<MegaValve, Integer> tempElfMemory = new HashMap<>(this.elfMemory.size() + 1);
        tempElfMemory.putAll(this.elfMemory);
        tempElfMemory.put(elfValve, elfScore);
        this.elfMemory = tempElfMemory;
    }

    private List<MegaValve> addValveToOpenedByElf() {
        List<MegaValve> tempOpenedByElf = new ArrayList<>(openedByElf.size() + 1);
        tempOpenedByElf.addAll(openedByElf);
        tempOpenedByElf.add(elfValve);
        return tempOpenedByElf;
    }

    private List<MegaValve> addValveToOpenedByElephant() {
        List<MegaValve> tempOpenedByElephant = new ArrayList<>(openedByElephant.size() + 1);
        tempOpenedByElephant.addAll(openedByElephant);
        tempOpenedByElephant.add(elephantValve);
        return tempOpenedByElephant;
    }

    private Map<MegaValve, Integer> addScoreToElfMemory(int score) {
        Map<MegaValve, Integer> elfTempMemory = new HashMap<>(this.elfMemory);
        elfTempMemory.putAll(this.elfMemory);
        elfTempMemory.put(elfValve, elfScore + score);
        return elfTempMemory;
    }

    private Map<MegaValve, Integer> addScoreToElephantMemory(int score) {
        Map<MegaValve, Integer> elephantTempMemory = new HashMap<>(this.elephantMemory);
        elephantTempMemory.putAll(this.elephantMemory);
        elephantTempMemory.put(elfValve, elephantScore + score);
        return elephantTempMemory;
    }

    private void bothHeadOn(List<ElephantStrategy> strategyList) {
        ElephantStrategy strategy = new ElephantStrategy(
                leftMinutes - 1, elfScore, elephantScore, elfValve, elephantValve,
                elfStepsNeeded - 1, elephantStepsNeeded - 1,
                openedByElf, openedByElephant, elfMemory, elephantMemory);
        strategyList.add(strategy);
    }

    private void elephantMoves(List<ElephantStrategy> strategyList) {
        List<MegaValve> options = getElephantNeighbourOptions();
        if (options.isEmpty()) {
            ElephantStrategy strategy = new ElephantStrategy(
                    leftMinutes - 1, elfScore, elephantScore, elfValve, elephantValve,
                    elfStepsNeeded - 1, 0,
                    openedByElf, openedByElephant, elfMemory, elephantMemory);
            strategyList.add(strategy);
            return;
        }
        options.stream()
                .map(neighbour -> new ElephantStrategy(
                        leftMinutes - 1, elfScore, elephantScore, elfValve, neighbour,
                        elfStepsNeeded - 1, elephantValve.stepsNeededToNeighbour(neighbour),
                        openedByElf, openedByElephant, elfMemory, elephantMemory))
                .forEach(strategyList::add);
    }

    private void elephantOpensValve(List<ElephantStrategy> strategyList) {
        if (shouldElephantOpenValve()) {
            int score = elephantValve.flowRate() * leftMinutes;

            Map<MegaValve, Integer> elephantTempMemory = addScoreToElephantMemory(score);
            List<MegaValve> tempOpenedByElephant = addValveToOpenedByElephant();

            ElephantStrategy strategy = new ElephantStrategy(
                    leftMinutes - 1, elfScore, elephantScore + score, elfValve, elephantValve,
                    elfStepsNeeded - 1, 0,
                    openedByElf, tempOpenedByElephant, elfMemory, elephantTempMemory);
            strategyList.add(strategy);
        }
    }

    private void elfMoves(List<ElephantStrategy> strategyList) {
        List<MegaValve> options = getElfNeighbourOptions();
        if (options.isEmpty()) {
            ElephantStrategy strategy = new ElephantStrategy(
                    leftMinutes - 1, elfScore, elephantScore, elfValve, elephantValve,
                    0, elephantStepsNeeded - 1,
                    openedByElf, openedByElephant, elfMemory, elephantMemory);
            strategyList.add(strategy);
            return;
        }
        for (MegaValve neighbour : options) {
            ElephantStrategy strategy = new ElephantStrategy(
                    leftMinutes - 1, elfScore, elephantScore, neighbour, elephantValve,
                    elfValve.stepsNeededToNeighbour(neighbour), elephantStepsNeeded - 1,
                    openedByElf, openedByElephant, elfMemory, elephantMemory);
            strategyList.add(strategy);
        }

    }

    private void elfOpensValve(List<ElephantStrategy> strategyList) {
        if (shouldElfOpenValve()) {
            int score = elfValve.flowRate() * leftMinutes;

            Map<MegaValve, Integer> elfTempMemory = addScoreToElfMemory(score);
            List<MegaValve> tempOpenedByElf = addValveToOpenedByElf();

            ElephantStrategy strategy = new ElephantStrategy(
                    leftMinutes - 1, elfScore + score, elephantScore, elfValve, elephantValve,
                    0, elephantStepsNeeded - 1,
                    tempOpenedByElf, openedByElephant, elfTempMemory, elephantMemory);
            strategyList.add(strategy);
        }
    }

    private void elfHeadsOnElephantOpensValve(List<ElephantStrategy> strategyList) {
        if (shouldElephantOpenValve()) {
            int score = elephantValve.flowRate() * leftMinutes;

            Map<MegaValve, Integer> elephantTempMemory = addScoreToElephantMemory(score);
            List<MegaValve> tempOpenedByElephant = addValveToOpenedByElephant();

            List<MegaValve> options = getElfNeighbourOptions();
            if (options.isEmpty()) {
                ElephantStrategy strategy = new ElephantStrategy(
                        leftMinutes - 1, elfScore, elephantScore + score,
                        elfValve, elephantValve, 0, 0,
                        openedByElf, tempOpenedByElephant,
                        elfMemory, elephantTempMemory);
                strategyList.add(strategy);
                return;
            }
            for (MegaValve neighbour : options) {
                ElephantStrategy strategy = new ElephantStrategy(
                        leftMinutes - 1, elfScore, elephantScore + score,
                        neighbour, elephantValve,
                        elfValve.stepsNeededToNeighbour(neighbour), 0,
                        openedByElf, tempOpenedByElephant,
                        elfMemory, elephantTempMemory);
                strategyList.add(strategy);
            }
        }
    }

    private void elfOpensValveElephantHeadsOn(List<ElephantStrategy> strategyList) {
        if (shouldElfOpenValve()) {
            int score = elfValve.flowRate() * leftMinutes;

            Map<MegaValve, Integer> elfTempMemory = addScoreToElfMemory(score);
            List<MegaValve> tempOpenedByElf = addValveToOpenedByElf();

            List<MegaValve> options = getElephantNeighbourOptions();
            if (options.isEmpty()) {
                ElephantStrategy strategy = new ElephantStrategy(
                        leftMinutes - 1, elfScore + score, elephantScore, elfValve, elephantValve,
                        0, 0,
                        tempOpenedByElf, openedByElephant, elfTempMemory, elephantMemory);
                strategyList.add(strategy);
                return;
            }
            for (MegaValve neighbour : options) {
                ElephantStrategy strategy = new ElephantStrategy(
                        leftMinutes - 1, elfScore + score, elephantScore, elfValve, neighbour,
                        0, elephantValve.stepsNeededToNeighbour(neighbour),
                        tempOpenedByElf, openedByElephant, elfTempMemory, elephantMemory);
                strategyList.add(strategy);
            }
        }
    }

    private void bothOpenValve(List<ElephantStrategy> strategyList) {
        if (shouldBothOpenValve()) {
            int addedElfScore = elfValve.flowRate() * leftMinutes;
            int addedElephantScore = elephantValve.flowRate() * leftMinutes;

            Map<MegaValve, Integer> elfTempMemory = addScoreToElfMemory(addedElfScore);
            List<MegaValve> tempOpenedByElf = addValveToOpenedByElf();

            Map<MegaValve, Integer> elephantTempMemory = addScoreToElephantMemory(addedElephantScore);
            List<MegaValve> tempOpenedByElephant = addValveToOpenedByElephant();

            ElephantStrategy strategy = new ElephantStrategy(
                    leftMinutes - 1, elfScore + addedElfScore, elephantScore + addedElephantScore,
                    elfValve, elephantValve, 0, 0, tempOpenedByElf, tempOpenedByElephant,
                    elfTempMemory, elephantTempMemory);
            strategyList.add(strategy);
        }
    }

    private void bothMove(List<ElephantStrategy> strategyList) {
        List<MegaValve> elfOptions = getElfNeighbourOptions();
        List<MegaValve> elephantOptions = getElephantNeighbourOptions();
        if (elfOptions.isEmpty() && elephantOptions.isEmpty()) {
            return;
        }
        if (elfOptions.isEmpty()) {
            for (MegaValve elephantNeighbour : elephantOptions) {
                ElephantStrategy strategy = new ElephantStrategy(
                        leftMinutes - 1, elfScore, elephantScore, elfValve, elephantNeighbour,
                        0, elephantValve.stepsNeededToNeighbour(elephantNeighbour),
                        openedByElf, openedByElephant, elfMemory, elephantMemory);
                strategyList.add(strategy);
            }
            return;
        }
        if (elephantOptions.isEmpty()) {
            for (MegaValve elfNeighbour : elfOptions) {
                ElephantStrategy strategy = new ElephantStrategy(
                        leftMinutes - 1, elfScore, elephantScore, elfNeighbour, elephantValve,
                        elfValve.stepsNeededToNeighbour(elfNeighbour), 0,
                        openedByElf, openedByElephant, elfMemory, elephantMemory);
                strategyList.add(strategy);
            }
            return;
        }
        if (elfValve == elephantValve) {
            List<MegaValve> allOptions = Stream
                    .concat(elfOptions.stream(), elephantOptions.stream())
                    .distinct()
                    .toList();
            for (int i = 0; i < allOptions.size(); i++) {
                for (int j = 0; j <= i; j++) {
                    MegaValve elfNeighbour = allOptions.get(i);
                    MegaValve elephantNeighbour = allOptions.get(j);
                    if (!elfOptions.contains(elfNeighbour) || !elephantOptions.contains(elephantNeighbour)) {
                        continue;
                    }
                    ElephantStrategy strategy = new ElephantStrategy(
                            leftMinutes - 1, elfScore, elephantScore, elfNeighbour, elephantNeighbour,
                            elfValve.stepsNeededToNeighbour(elfNeighbour),
                            elephantValve.stepsNeededToNeighbour(elephantNeighbour),
                            openedByElf, openedByElephant, elfMemory, elephantMemory);
                    strategyList.add(strategy);
                }
            }
            return;
        }
        for (MegaValve elfNeighbour : elfOptions) {
            for (MegaValve elephantNeighbour : elephantOptions) {
                ElephantStrategy strategy = new ElephantStrategy(
                        leftMinutes - 1, elfScore, elephantScore, elfNeighbour, elephantNeighbour,
                        elfValve.stepsNeededToNeighbour(elfNeighbour),
                        elephantValve.stepsNeededToNeighbour(elephantNeighbour),
                        openedByElf, openedByElephant, elfMemory, elephantMemory);
                strategyList.add(strategy);
            }
        }
    }

    private List<MegaValve> getElfNeighbourOptions() {
        return elfValve.getNeighbours().stream()
                .filter(neighbour -> elfScore > elfMemory.getOrDefault(neighbour, -1))
                .toList();
    }

    private List<MegaValve> getElephantNeighbourOptions() {
        return elephantValve.getNeighbours().stream()
                .filter(neighbour -> elephantScore > elephantMemory.getOrDefault(neighbour, -1))
                .toList();
    }

    private boolean shouldElfOpenValve() {
        if (elfValve.flowRate() == 0) {
            return false;
        }
        return !(openedByElf.contains(elfValve) || openedByElephant.contains(elfValve));
    }

    private boolean shouldElephantOpenValve() {
        if (elephantValve.flowRate() == 0) {
            return false;
        }
        return !(openedByElf.contains(elephantValve) || openedByElephant.contains(elephantValve));
    }

    private boolean shouldBothOpenValve() {
        if (elfValve == elephantValve) {
            return false;
        }
        if (elfValve.flowRate() == 0 || elephantValve.flowRate() == 0) {
            return false;
        }
        return !(openedByElf.contains(elfValve) || openedByElephant.contains(elfValve)) &&
                !(openedByElf.contains(elephantValve) || openedByElephant.contains(elephantValve));
    }

    public int getScore() {
        return elfScore + elephantScore;
    }

    private Set<MegaValve> getAllOpenedValves() {
        return Stream
                .concat(openedByElf.stream(), openedByElephant.stream())
                .collect(Collectors.toSet());
    }

    public List<MegaValve> getOpenedByElf() {
        return openedByElf;
    }

    public List<MegaValve> getOpenedByElephant() {
        return openedByElephant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElephantStrategy strategy = (ElephantStrategy) o;

        return elfValve.equals(strategy.elfValve) &&
                elephantValve.equals(strategy.elephantValve) &&
                elfStepsNeeded == strategy.elfStepsNeeded &&
                elephantStepsNeeded == strategy.elephantStepsNeeded &&
                getAllOpenedValves().equals(strategy.getAllOpenedValves()) &&
                getScore() == strategy.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(elfValve, elfStepsNeeded, elephantValve, elephantStepsNeeded,
                getAllOpenedValves(), getScore());
    }
}
