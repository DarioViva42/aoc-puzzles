package tk.vivas.adventofcode.day07;

record File(Folder parent, String name, int size) implements FileSystemEntity {

    @Override
    public String toString() {
        return "%s (file, size=%d)".formatted(name, size);
    }
}
