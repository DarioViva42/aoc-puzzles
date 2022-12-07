package vivas.tk.adventofcode.day07;

public record File(Folder parent, String name, int size) implements FileSystemEntity {

    @Override
    public String toString() {
        return "%s (file, size=%d)".formatted(name, size);
    }
}
