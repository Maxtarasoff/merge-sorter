package org.example.ScannerQueues;

import lombok.Data;
import org.example.EmptyLineException;
import org.example.exceptions.IncorrectlySortedException;
import org.example.options.Options;
import org.example.options.types.SortOrder;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Data
public abstract class ScannersQueue<T extends Comparable<T>> {
    final Queue<Map.Entry<T, Scanner>> scannersQueue;
    private final Options options;
    private T previousLine = null;

    public ScannersQueue(Options options){
        this.options = options;
        Comparator<Map.Entry<T, Scanner>> sortComparator = (options.getSortOrder().equals(SortOrder.ASCENDING))
                ? Map.Entry.comparingByKey()
                : (o1, o2) -> -o1.getKey().compareTo(o2.getKey());
        this.scannersQueue = new PriorityQueue<>(this.options.getInputFiles().size(), sortComparator);

        // todo check later
        try {
            this.initQueue(options.getInputFiles());
        } catch (IOException e) {
            System.err.printf("Ошибка при чтении файла: %s%n", e.getMessage());
        }
    }

    private void initQueue(List<File> inputFiles) throws IOException {
//        scannersQueue.clear();
        for (File file : inputFiles){
            Scanner scanner = new Scanner(file);
            addNextElementInQueue(scanner);
        }
    }

    private void addNextElementInQueue(Scanner scanner) {
        if (scanner.hasNext()) {
            String nextLine = scanner.nextLine().trim();
            try {
                if (nextLine.isEmpty()) throw new EmptyLineException();
                T key = convert(nextLine);
                scannersQueue.offer(new AbstractMap.SimpleImmutableEntry<>(key, scanner));
            } catch (NumberFormatException | EmptyLineException e) {
                System.err.println(e.getMessage());
                addNextElementInQueue(scanner);
            }
        }
    }

    public Optional<T> getMinMaxElement() throws IncorrectlySortedException {
        Map.Entry<T, Scanner> entry = scannersQueue.poll();
        if (entry != null) {

            Scanner scanner = entry.getValue();
            T line = entry.getKey();

            addNextElementInQueue(scanner);

            if (line != null) {
                if (isCorrectOrder(previousLine,line)) {
                    return Optional.of(line);
                } else {
                    throw new IncorrectlySortedException();
                }

            } else {
                scanner.close();
            }
        }

        return Optional.empty();
    }

    private boolean isCorrectOrder(T previousLine, T line) {
        if (previousLine == null) return true;
        int reverse = 1;
        if (options.getSortOrder().equals(SortOrder.DESCENDING)) reverse = -1;
        return previousLine.compareTo(line)*reverse < 0;
    }

    public boolean isEmpty() {
        return scannersQueue.isEmpty();
    }

    protected abstract T convert(String string) throws NumberFormatException, EmptyLineException;

    public void setPreviousLine(T previousLine) {
        this.previousLine = previousLine;
    }

}
