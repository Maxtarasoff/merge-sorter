package org.example.options;

import org.example.exceptions.NotEnoughArgumentsException;
import org.example.options.types.DataType;
import org.example.options.types.SortOrder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class OptionParser {
    public static Options parse(String[] args){
        try {
            verifyArgumentsCount(args.length);

            SortOrder sortOrder = SortOrder.ASCENDING; //default value
            DataType dataType = DataType.UNKNOWN;
            File outputFile;
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

            outputFile = files.get(0);
            files.remove(0);

            return Options.builder()
                    .dataType(getVerifiedDataType(dataType))
                    .sortOrder(sortOrder)
                    .outputFile(outputFile)
                    .inputFiles(getExistedFiles(files))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void verifyArgumentsCount(int length) throws NotEnoughArgumentsException{
        if (length < 3) {
            throw new NotEnoughArgumentsException("The number of arguments must be at least 3");
        }
    }

    private static DataType getVerifiedDataType(DataType dataType) throws Exception{
        if (dataType.equals(DataType.UNKNOWN))
            throw new NotEnoughArgumentsException("No data type specified");
        else return dataType;
    }

    private static List<File> getExistedFiles(List<File> files) throws NotEnoughArgumentsException {
        List<File> result = files.stream().filter(file -> {
            try {
                if (file.exists())
                    return true;
                else
                    throw new FileNotFoundException(file.getName() + " not found");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }).toList();
        if (result.isEmpty())
            throw new NotEnoughArgumentsException("No existed input files specified");
        return result;
    }

}
