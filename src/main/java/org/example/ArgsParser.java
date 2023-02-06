package org.example;

import org.example.options.Options;
import org.example.options.types.Datatype;
import org.example.options.types.SortDirection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArgsParser {
    public static Options getOptions(String[] args) {

        if (args.length < 3) {
            showHelpMessage();
            System.exit(0);
        }

        SortDirection sortDirection = SortDirection.ASCENDING; //default values
        Datatype datatype = Datatype.UNKNOWN;
        List<String> filenames = new ArrayList<>();
        File outFile;
        List<File> inputFiles = new ArrayList<>();

        for (String arg : args) {
            switch (arg) {
                case "-a" -> sortDirection = SortDirection.ASCENDING;
                case "-d" -> sortDirection = SortDirection.DESCENDING;
                case "-i" -> datatype = Datatype.INT;
                case "-s" -> datatype = Datatype.STRING;
                default -> filenames.add(arg);
            }
        }

        outFile = new File(filenames.get(0));
        filenames.remove(0);
        filenames.forEach(f -> inputFiles.add(new File(f)));

        Options options = Options.builder()
                .datatype(datatype)
                .sortDirection(sortDirection)
                .outputFile(outFile)
                .inputFiles(inputFiles)
                .build();

        checkOut(options);
        return options;
    }

    private static void showHelpMessage() {
        System.out.println("Недостаточно аргументов для продолжения работы программы.");
    }

    private static void checkOut(Options options) {
    }

    private static boolean isValidOptions(Options options) {
        return true;
    }

    private static boolean isValidFile(File file) {
        return true;
    }
}
