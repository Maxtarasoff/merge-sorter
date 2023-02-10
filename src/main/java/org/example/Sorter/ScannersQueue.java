package org.example.Sorter;

import org.example.exceptions.EmptyLineException;
import org.example.exceptions.ExceptionPrinter;
import org.example.exceptions.SortOrderBrokenException;
import org.example.options.Options;
import org.example.options.types.SortOrder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public abstract class ScannersQueue<T extends Comparable<T>> {
    private final Queue<Map.Entry<T, Scanner>> scannersQueue;
    private final Options options;
    private T previousElement = null;

    public ScannersQueue(Options options){
        this.options = options;
        Comparator<Map.Entry<T, Scanner>> sortComparator = (options.getSortOrder().equals(SortOrder.ASCENDING))
                ? Map.Entry.comparingByKey()
                : (o1, o2) -> -o1.getKey().compareTo(o2.getKey());
        this.scannersQueue = new PriorityQueue<>(this.options.getInputFiles().size(), sortComparator);
        this.initQueue();
    }

    private void initQueue(){
        for (File file : options.getInputFiles()){
            try {
                offerNextElement(new Scanner(file));
            }
            catch (FileNotFoundException e){
                ExceptionPrinter.print(e);
            }
        }
    }

    private void offerNextElement(Scanner scanner) {
        if (scanner.hasNext()) {
            String nextLine = scanner.nextLine().trim();
            try {
                if (nextLine.isEmpty()) throw new EmptyLineException();
                T element = parse(nextLine);
                scannersQueue.offer(new AbstractMap.SimpleImmutableEntry<>(element, scanner));
            } catch (NumberFormatException | EmptyLineException e) {
                ExceptionPrinter.print(e);
                offerNextElement(scanner);
            }
        } else scanner.close();
    }

    public Optional<String> pollNextElement() throws SortOrderBrokenException {
        Map.Entry<T, Scanner> entry = scannersQueue.poll();

        if (entry != null) {
            T nextElement = entry.getKey();
            Scanner scanner = entry.getValue();
            offerNextElement(scanner);

            if (isCorrectOrder(previousElement,nextElement)) {
                return Optional.of(nextElement.toString());
            } else {
                throw new SortOrderBrokenException();
            }
        } else
            return Optional.empty();
    }

    private boolean isCorrectOrder(T previousElement, T nextElement) {
        if (previousElement == null) return true;
        int compareResult = previousElement.compareTo(nextElement);
        if (options.getSortOrder().equals(SortOrder.DESCENDING)) compareResult = -compareResult;
        return compareResult < 0;
    }

    public boolean isEmpty() {
        return scannersQueue.isEmpty();
    }

    protected abstract T parse(String string) throws NumberFormatException;

    public void setPreviousElement(String line) {
        previousElement = parse(line);
    }
}
