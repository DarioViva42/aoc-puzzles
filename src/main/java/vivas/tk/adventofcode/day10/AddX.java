package vivas.tk.adventofcode.day10;

record AddX(int x) implements Instruction {

    @Override
    public int runOnCpu(CPU cpu) {
        int strength = cpu.signalStrength();
        cpu.tick();
        strength += cpu.signalStrength();
        cpu.tick();
        cpu.addToRegister(x);
        return strength;
    }

    @Override
    public void draw(CRT crt, CPU cpu) {
        cpu.drawTo(crt);
        cpu.tick();
        cpu.drawTo(crt);
        cpu.tick();
        cpu.addToRegister(x);
    }
}
