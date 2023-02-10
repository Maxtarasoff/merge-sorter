package org.example.Sorter;

import org.example.options.Options;

public class StringScannersQueue extends ScannersQueue<String>{
    public StringScannersQueue(Options options) {
        super(options);
    }

    @Override
    protected String parse(String string){
        return string;
    }
}
