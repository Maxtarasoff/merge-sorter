package org.example;

import org.example.options.OptionParser;
import org.example.options.Options;

public class Main {
    public static void main(String[] args) {
        String[] array = {"-s", "out.txt", "in1.txt", "in2.txt"};
        try {
            Options options = OptionParser.parse(array);
            Sorter sorter = new Sorter(options);
            sorter.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}