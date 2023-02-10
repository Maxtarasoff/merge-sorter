package org.example;

import org.example.options.OptionParser;
import org.example.options.Options;

public class Main {
    public static void main(String[] args) {
        String[] array = {"-s", "out.txt", "in1.txt", "in2.txt"};
        Options options = OptionParser.parse(array);
        Sorter sorter = new Sorter(options);
        sorter.merge();
//        assert options != null;
//        Sorter<?> sorter = options.getDataType().equals(DataType.INT)
//                ? new Sorter<Integer>(options)
//                : new Sorter<String>(options);
    }
}