package org.example;

import lombok.RequiredArgsConstructor;
import org.example.options.Options;
import org.example.options.types.Datatype;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class Sorter {

    private final Options options;

    public void execute(){
        System.out.println(options);
//        printType(options.getDatatype());
        mergeFiles(options);

//        List<LinkedList<Integer>> inputLists = fileParser.read(options.getInputFiles());
//        var output = merge(inputLists);
    }

    private void mergeFiles(Options options) {
        if (options.getDatatype().equals(Datatype.INT))
            mergeIntegers(options);
        else
            mergeStrings(options);
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

    private void printType(Datatype datatype){
        System.out.println(datatype == Datatype.INT ? "Integer" : "String");
    }

    private void noCommand() {
    }
}
