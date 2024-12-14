package tk.vivas.adventofcode.year2024.day09;

record DiskMapToken(int fileId, int fileLength, int freeSpaceLength) {
    DiskMapToken withFreeSpace(int freeSpaceLength) {
        return new DiskMapToken(fileId, fileLength, freeSpaceLength);
    }

    static DiskMapToken createDiskMapToken(char[] charArray, int i) {
        int fileLength = charArray[i] - '0';
        int freeSpaceLength = charArray[i + 1] - '0';
        return new DiskMapToken(i / 2, fileLength, freeSpaceLength);
    }
}
