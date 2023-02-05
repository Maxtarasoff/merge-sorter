package org.example.options;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.options.types.Datatype;
import org.example.options.types.SortDirection;

import java.io.File;
import java.util.List;

@Data
@RequiredArgsConstructor
@Builder
public class Options {
    private List<File> inputFiles;
    private Datatype datatype;
    private SortDirection sortDirection;
    private File outputFile;

}
