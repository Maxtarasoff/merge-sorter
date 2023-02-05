package org.example;

import org.example.options.Options;

public class Main {
    public static void main(String[] args) {
        Options options = ArgsParser.getOptions(args);
        Sorter.execute(options);
    }
}