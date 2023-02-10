package org.example.options;

import org.example.exceptions.NotEnoughArgumentsException;
import org.example.options.types.DataType;
import org.example.options.types.SortOrder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OptionParser {
    public static Options parse(String[] args) throws Exception {

        if (args.length < 3) {
            throw new NotEnoughArgumentsException("The number of arguments must be at least 3");
        }

        SortOrder sortOrder = SortOrder.ASCENDING; //default value
        DataType dataType = DataType.UNKNOWN;
        List<File> files = new ArrayList<>();

        for (String arg : args) {
            switch (arg) {
                case "-a" -> sortOrder = SortOrder.ASCENDING;
                case "-d" -> sortOrder = SortOrder.DESCENDING;
                case "-i" -> dataType = DataType.INT;
                case "-s" -> dataType = DataType.STRING;
                default -> files.add(new File(arg));
            }
        }

        Options options = Options.builder()
                .datatype(dataType)
                .sortOrder(sortOrder)
                .outputFile(files.get(0))
                .inputFiles(files.subList(1, files.size()))
                .build();

        check(options);
        return options;
    }

    private static void check(Options options) throws Exception{
        if (options.getDatatype().equals(DataType.UNKNOWN))
            throw new NotEnoughArgumentsException("No data type specified");
        if (options.getInputFiles().size() == 0)
            throw new NotEnoughArgumentsException("No input files specified");
    }

}
