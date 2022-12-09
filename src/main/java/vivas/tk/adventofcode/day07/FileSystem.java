package vivas.tk.adventofcode.day07;

import java.util.Comparator;
import java.util.Optional;

public class FileSystem {
    final Folder root = new Folder("/");
    Folder currentDirectory;

    public FileSystem(String input) {
        input.lines()
                .forEach(this::parseLine);
    }

    private void parseLine(String line) {
        String[] tokens = line.split(" ");
        if (tokens[0].equals("$")) {
            if (tokens[1].equals("cd")) {
                changeDirectory(tokens[2]);
            }
        } else {
            parseListOutput(tokens[0], tokens[1]);
        }
    }

    private void parseListOutput(String info, String name) {
        FileSystemEntity entity;
        if (info.equals("dir")) {
            entity = new Folder(currentDirectory, name);
        } else {
            int size = Integer.parseInt(info);
            entity = new File(currentDirectory, name, size);
        }
        currentDirectory.addEntity(entity);
    }

    private void changeDirectory(String targetDirectoryName) {
        currentDirectory = switch (targetDirectoryName) {
            case "/" -> root;
            case ".." -> currentDirectory.parent();
            default -> currentDirectory.findSubFolder(targetDirectoryName).orElseThrow();
        };
    }

    public int countSizeNaiveApproach() {
        return root.getAllFolders()
                .mapToInt(Folder::size)
                .filter(size -> size < 100000)
                .sum();

    }

    public Optional<Folder> findSmallestToDeleteFolder() {
        long totalDiskSpace = 70000000L;
        long updateSize = 30000000L;
        long usedSpace = root.size();
        long unusedSpace = totalDiskSpace - usedSpace;
        long neededSpace = updateSize - unusedSpace;

        return root.getAllFolders()
                .filter(folder -> folder.size() >= neededSpace)
                .min(Comparator.comparingLong(Folder::size));
    }
}
