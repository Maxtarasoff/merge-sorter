package org.example;

import org.example.options.Options;
import org.example.options.types.Datatype;

import java.util.LinkedList;
import java.util.List;

public class Sorter {
    public static void execute(Options options){

        printType(options.getDatatype());
        mergeFiles(options);

//        List<LinkedList<Integer>> inputLists = fileParser.read(options.getInputFiles());
//        var output = merge(inputLists);
    }

    private static void mergeFiles(Options options) {
        if (options.getDatatype().equals(Datatype.INT))
            mergeIntegers(options);
        else
            mergeStrings(options);
    }

    private static void mergeStrings(Options options) {
    }

    private static void mergeIntegers(Options options) {
    }

    private static  <T> LinkedList<T> merge(List<LinkedList<T>> inputLists) {
        LinkedList<T> result = new LinkedList<>();
        inputLists.forEach(result::addAll);
        return result;
    }

    private static void printType(Datatype datatype){
        System.out.println(datatype == Datatype.INT ? "Integer" : "String");
    }

    private static void noCommand() {
    }
}
