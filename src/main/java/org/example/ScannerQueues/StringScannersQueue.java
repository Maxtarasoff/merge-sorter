package org.example.ScannerQueues;

import org.example.options.Options;

public class StringScannersQueue extends ScannersQueue<String>{
    public StringScannersQueue(Options options) {
        super(options);
    }

    @Override
    protected String convert(String string){
        return string;
    }
}
