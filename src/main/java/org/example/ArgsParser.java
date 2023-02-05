package org.example;

import org.example.options.Options;
import org.example.options.types.Datatype;
import org.example.options.types.SortDirection;

import java.io.File;

public class ArgsParser {
    public static Options getOptions(String[] args) {
        Options options = Options.builder()
                .datatype(Datatype.UNKNOWN)
                .sortDirection(SortDirection.ASCENDING)
                .inputFiles(null)
                .outputFile(null)
                .build();
        return options;
    }

    private static boolean isValidOptions(Options options) {
        return true;
    }

    private static boolean isValidFile(File file) {
        return true;
    }
}
