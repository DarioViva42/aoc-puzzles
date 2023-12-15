package tk.vivas.adventofcode.year2023.day15;

record RemoveInstruction(String label) implements Instruction {

    @Override
    public void run(LensBox box) {
        box.removeLens(label);
    }
}
