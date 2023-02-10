package org.example.options;

import lombok.Builder;
import lombok.Data;
import org.example.options.types.DataType;
import org.example.options.types.SortOrder;

import java.io.File;
import java.util.List;

@Data
@Builder
public class Options {
    private List<File> inputFiles;
    private DataType dataType;
    private SortOrder sortOrder;
    private File outputFile;

}
