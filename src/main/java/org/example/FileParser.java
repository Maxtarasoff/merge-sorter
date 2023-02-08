package org.example;

import java.io.File;
import java.util.LinkedList;
import java.util.List;


public interface FileParser<T> {
    public List<LinkedList<T>> read(List<File> files);
}
