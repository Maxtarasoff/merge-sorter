package org.example.Sorter;

import org.example.exceptions.EmptyLineException;
import org.example.exceptions.ExceptionPrinter;
import org.example.exceptions.SortOrderBrokenException;
import org.example.options.Options;
import org.example.options.types.SortOrder;

import java.io.*;
import java.util.*;

public abstract class ReadersQueue<T extends Comparable<T>> {
    private final Queue<Map.Entry<T, BufferedReader>> readersQueue;
    private final Options options;
    private T previousElement = null;

    public ReadersQueue(Options options){
        this.options = options;
        Comparator<Map.Entry<T, BufferedReader>> sortComparator = (options.getSortOrder().equals(SortOrder.ASCENDING))
                ? Map.Entry.comparingByKey()
                : (o1, o2) -> -o1.getKey().compareTo(o2.getKey());
        this.readersQueue = new PriorityQueue<>(this.options.getInputFiles().size(), sortComparator);
        this.initQueue();
    }

    private void initQueue(){
        for (File file : options.getInputFiles()){
            try {
                offerNextElement(new BufferedReader(new FileReader(file)));
            }
            catch (IOException e){
                ExceptionPrinter.print(e);
            }
        }
    }

    private void offerNextElement(BufferedReader reader){
        try {
            String nextLine = reader.readLine();
            if (nextLine != null) {
                if (nextLine.isEmpty()) throw new EmptyLineException();
                T element = parse(nextLine);
                readersQueue.offer(new AbstractMap.SimpleImmutableEntry<>(element, reader));
            }
        } catch (NumberFormatException | EmptyLineException | IOException e) {
            ExceptionPrinter.print(e);
            offerNextElement(reader);
        }
    }

    public Optional<String> pollNextElement() throws SortOrderBrokenException, IOException {
        Map.Entry<T, BufferedReader> entry = readersQueue.poll();
        if (entry == null) return Optional.empty();

        T nextElement = entry.getKey();
        BufferedReader reader = entry.getValue();
        offerNextElement(reader);

        if (nextElement == null) {
            reader.close();
        } else {
            if (isCorrectOrder(previousElement, nextElement)) {
                return Optional.of(nextElement.toString());
            } else {
                throw new SortOrderBrokenException();
            }
        }

        return Optional.empty();
    }

    private boolean isCorrectOrder(T previousElement, T nextElement) {
        if (previousElement == null) return true;
        int compareResult = previousElement.compareTo(nextElement);
        if (options.getSortOrder().equals(SortOrder.DESCENDING)) compareResult = -compareResult;
        return compareResult < 0;
    }

    public boolean isEmpty() {
        return readersQueue.isEmpty();
    }

    protected abstract T parse(String string) throws NumberFormatException;

    public void setPreviousElement(String line) {
        previousElement = parse(line);
    }
}
