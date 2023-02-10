package org.example.Sorter;

import org.example.exceptions.ExceptionPrinter;
import org.example.exceptions.SortOrderBrokenException;
import org.example.options.Options;
import org.example.options.types.DataType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class Sorter {

    private final Options options;
    private final ScannersQueue<?> scannersQueue;

    public Sorter(Options options) {
        this.options = options;
        scannersQueue = options.getDataType().equals(DataType.INT)
                ? new IntegerScannersQueue(options)
                : new StringScannersQueue(options);
    }
    public void merge() {
        try (FileWriter writer = new FileWriter(options.getOutputFile())) {
            while (!scannersQueue.isEmpty()) {
                try {
                    Optional<String> nextElement = scannersQueue.pollNextElement();
                    if (nextElement.isPresent()) {
                        writer.write(nextElement.get() + System.lineSeparator());
                        scannersQueue.setPreviousElement(nextElement.get());
                    }
                } catch (SortOrderBrokenException e) {
                    ExceptionPrinter.print(e);
                }
            }
        } catch (IOException e) {
            ExceptionPrinter.print(e);
        }
        if (options.getOutputFile().exists() && options.getOutputFile().length()>0)
            System.out.println("Files merged successfully!");
    }
}
