package org.example.Sorter;

import org.example.options.Options;

public class IntegerReadersQueue extends ReadersQueue<Integer> {
    public IntegerReadersQueue(Options options) {
        super(options);
    }

    @Override
    protected Integer parse(String string) throws NumberFormatException {
        return Integer.parseInt(string);
    }
}
