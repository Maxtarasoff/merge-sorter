package org.example.exceptions;

public class SortOrderBrokenException extends Exception{
    public SortOrderBrokenException() {
        super("Sort order broken, line ignored");
    }
}
