package org.example.exceptions;

public class IncorrectlySortedException extends Exception{
    public IncorrectlySortedException() {
        super("Sort order broken");
    }
}
