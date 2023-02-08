package org.example.options;

import org.example.exceptions.NotEnoughArgumentsException;
import org.example.options.types.Datatype;
import org.example.options.types.SortDirection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OptionParser {
    public static Options parse(String[] args) throws Exception {

        if (args.length < 3) {
            throw new NotEnoughArgumentsException("Number of arguments < 3");
        }

        SortDirection sortDirection = SortDirection.ASCENDING; //default value
        Datatype datatype = Datatype.UNKNOWN;
        List<File> files = new ArrayList<>();

        for (String arg : args) {
            switch (arg) {
                case "-a" -> sortDirection = SortDirection.ASCENDING;
                case "-d" -> sortDirection = SortDirection.DESCENDING;
                case "-i" -> datatype = Datatype.INT;
                case "-s" -> datatype = Datatype.STRING;
                default -> files.add(new File(arg));
            }
        }

        Options options = Options.builder()
                .datatype(datatype)
                .sortDirection(sortDirection)
                .outputFile(files.get(0))
                .inputFiles(files.subList(1, files.size()))
                .build();

        check(options);
        return options;
    }

    private static void check(Options options) throws Exception{
        if (options.getDatatype().equals(Datatype.UNKNOWN))
            throw new NotEnoughArgumentsException("No data type specified");
        if (options.getInputFiles().size() == 0)
            throw new NotEnoughArgumentsException("No input files specified");
    }

}
