package org.example;

import org.example.options.Options;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        args = (String[]) List.of("-a", "-i", "out.txt", "in1.txt", "in2.txt", "in3.txt").toArray();
        Options options = ArgsParser.getOptions(args);
        Sorter.execute(options);
    }
}