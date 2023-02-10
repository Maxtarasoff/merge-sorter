package org.example.Sorter;

import org.example.options.Options;

public class StringReadersQueue extends ReadersQueue<String> {
    public StringReadersQueue(Options options) {
        super(options);
    }

    @Override
    protected String parse(String string){
        return string;
    }
}
