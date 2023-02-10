package org.example.exceptions;

public class EmptyLineException extends Exception{

    public EmptyLineException(){
        super("Empty line ignored");
    }
}
