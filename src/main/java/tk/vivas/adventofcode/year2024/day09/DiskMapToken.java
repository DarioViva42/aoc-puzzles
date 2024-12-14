package tk.vivas.adventofcode.year2024.day09;

record DiskMapToken(int fileId, int fileLength, int freeSpaceLength) {
    DiskMapToken withFreeSpace(int freeSpaceLength) {
        return new DiskMapToken(fileId, fileLength, freeSpaceLength);
    }
}
