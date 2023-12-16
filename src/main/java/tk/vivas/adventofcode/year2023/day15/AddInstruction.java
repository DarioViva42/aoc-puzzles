package tk.vivas.adventofcode.year2023.day15;

record AddInstruction(String label, int focalLength) implements Instruction {

    @Override
    public void run(LensBox box) {
        box.addLens(label, focalLength);
    }
}
