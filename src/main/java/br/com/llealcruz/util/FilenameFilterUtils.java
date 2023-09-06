package br.com.llealcruz.util;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterUtils implements FilenameFilter {
    private final String pattern;

    public FilenameFilterUtils(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean accept(File directory, String fileName) {
        return fileName.matches(this.pattern);
    }

}
