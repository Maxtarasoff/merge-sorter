package org.example;

import org.example.Sorter.Sorter;
import org.example.options.OptionParser;
import org.example.options.Options;

public class Main {
    public static void main(String[] args) {
        Options options = OptionParser.parse(args);
        if (options != null) {
            Sorter sorter = new Sorter(options);
            sorter.merge();
        }
    }
}