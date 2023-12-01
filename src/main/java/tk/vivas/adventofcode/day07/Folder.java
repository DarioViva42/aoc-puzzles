package tk.vivas.adventofcode.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

final class Folder implements FileSystemEntity {
    private final List<Folder> folders = new ArrayList<>();
    private final List<File> files = new ArrayList<>();
    private final String name;
    private final Folder parent;
    private int cachedSize;

    public Folder(String name) {
        this.name = name;
        this.parent = null;
    }

    public Folder(Folder parent, String name) {
        this.name = name;
        this.parent = parent;
    }

    public void addEntity(FileSystemEntity entity) {
        switch (entity) {
            case Folder folder -> folders.add(folder);
            case File file -> files.add(file);
        }
    }

    public Optional<Folder> findSubFolder(String name) {
        return folders.stream()
                .filter(e -> e.name().equals(name))
                .findAny();
    }

    public Stream<Folder> getAllFolders() {
        Stream<Folder> subFolderStream = folders.stream()
                .flatMap(Folder::getAllFolders);
        return Stream.concat(Stream.of(this), subFolderStream);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int size() {
        if (cachedSize != 0) {
            return cachedSize;
        }
        cachedSize = Stream.concat(folders.stream(), files.stream())
                .mapToInt(FileSystemEntity::size)
                .sum();
        return cachedSize;
    }

    @Override
    public Folder parent() {
        return parent;
    }

    @Override
    public String toString() {
        return "%s (dir)".formatted(name);
    }
}
