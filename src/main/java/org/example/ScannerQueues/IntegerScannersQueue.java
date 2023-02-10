package org.example.ScannerQueues;

import org.example.options.Options;

public class IntegerScannersQueue extends ScannersQueue<Integer>{
    public IntegerScannersQueue(Options options) {
        super(options);
    }

    @Override
    protected Integer convert(String string) throws NumberFormatException {
        return Integer.parseInt(string);
    }
}
