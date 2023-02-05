package org.example;

import org.example.options.Options;
import org.example.options.types.Datatype;

import java.io.BufferedInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

public class Sorter {
    public static void execute(Options options){

        FileParser fileParser = (options.getDatatype().equals(Datatype.STRING))
                ? new StringFileParser()
                : new IntegerFileParser();

        List<LinkedList<Integer>> inputLists = fileParser.read(options.getInputFiles());
        var output = merge(inputLists);

    }

    private static  <T> LinkedList<T> merge(List<LinkedList<T>> inputLists) {
        return new LinkedList<>();
    }
}
