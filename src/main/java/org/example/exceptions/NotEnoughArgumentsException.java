package org.example.exceptions;

public class NotEnoughArgumentsException extends Exception{

    public NotEnoughArgumentsException(String s){
        super("Not enough arguments. " + s);
    }
}
