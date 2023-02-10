package org.example.exceptions;

public class ExceptionPrinter {
     public static void print(Exception e) {
         if (e.getClass() == NumberFormatException.class)
             System.out.print("Exception: Integer parsing error");
         else
            System.out.print("Exception: "+ e.getMessage());
         if (e.getClass() == NotEnoughArgumentsException.class)
             printHelp();
         else
             System.out.println(" (details are available in premium version)");
     }

    private static void printHelp() {
        System.out.println("""
                MergeSorter v.1.2
                usage: java -jar merge-sorter.jar [-a/-d] -i/-s output-filename input-filenames
                -a for ascending sort order, -d for descending (not necessary, ascending order is default)
                -i for integer numbers, -s for strings (required)
                output-filename for output file (required)
                input-filenames for input files (required at least one)

                for example:
                java -jar merge-sorter.jar -a -i out.txt in.txt (for integers ascending).
                java -jar merge-sorter.jar -d -s out.txt in1.txt in2.txt (for strings descending).""");
    }
}
