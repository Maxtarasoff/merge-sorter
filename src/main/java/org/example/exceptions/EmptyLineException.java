package org.example;

public class EmptyLineException extends Exception{

    public EmptyLineException(){
        super("Empty line ignored");
    }
}
