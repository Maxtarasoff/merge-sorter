package org.example;

import lombok.RequiredArgsConstructor;
import org.example.options.Options;
import org.example.options.types.DataType;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class Sorter {

    private final Options options;

    public void execute() throws IOException{
        System.out.println(options);
        optionalString("d");
//        if (options.getInputFiles().size() == 1)
//            copyInputFile();
//        else
//            mergeTwoFiles(options);
    }

    private void copyInputFile() throws IOException{
        FileWriter fileWriter = new FileWriter(options.getOutputFile());
        Scanner scanner = new Scanner(options.getInputFiles().get(0));
        scanner.forEachRemaining(line -> {
            try {
                fileWriter.append(line).append("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        scanner.close();
        fileWriter.close();
    }

    private void mergeTwoFiles(Options options) throws IOException {

        FileWriter fileWriter = new FileWriter(options.getOutputFile());

        Scanner scanner0 = new Scanner(options.getInputFiles().get(0));
        Scanner scanner1 = new Scanner(options.getInputFiles().get(1));
        String s0 = scanner0.next();
        String s1 = scanner1.next();
        String prev;

        do {
            if (s0.compareTo(s1) < 0) {
                fileWriter.append(s0).append("\n");
                prev = s0;
                if (scanner0.hasNext()) {
                    s0 = scanner0.next();
                    if (s0.compareTo(prev) < 0) {
                        System.out.println("[unsorted word]: " + s0);
                        s0 = scanner0.next();
                    }
                }
                else {
                    fileWriter.append(s1).append("\n");
                    break;
                }
            } else {
                fileWriter.append(s1).append("\n");
                prev = s1;
                if (scanner1.hasNext()) {
                    s1 = scanner1.next();
                    if (s1.compareTo(prev) < 0) {
                        System.out.println("[unsorted word]: " + s1);
                        s1 = scanner1.next();
                    }
                }
                else {
                    fileWriter.append(s0).append("\n");
                    break;
                }
            }
        } while (true);

        if (scanner0.hasNext() || scanner1.hasNext()) {
            List<String> tail = new ArrayList<>();
            scanner0.forEachRemaining(tail::add);
            scanner1.forEachRemaining(tail::add);
            tail.forEach(t -> {
                try {
                    fileWriter.append(t).append("\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        System.out.println("Files are sorted.");
        fileWriter.close();
    }

    private void mergeStrings(Options options) {
    }

    private void mergeIntegers(Options options) {
    }

    private <T> LinkedList<T> merge(List<LinkedList<T>> inputLists) {
        LinkedList<T> result = new LinkedList<>();
        inputLists.forEach(result::addAll);
        return result;
    }

    private void printType(DataType datatype){
        System.out.println(datatype == DataType.INT ? "Integer" : "String");
    }

    private String optionalString(String s) {
        Optional<String> optionalS;
        optionalS = (s == null) ? Optional.empty() : Optional.of(s);

        Comparator<Integer> asc = Comparator.naturalOrder();
        Comparator<Integer> desc = Comparator.reverseOrder();
        System.out.println(asc.compare(1,2));
        System.out.println(desc.compare(1,2));
        return optionalS.orElse("null");
    }
}
