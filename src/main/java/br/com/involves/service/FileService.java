package br.com.involves.service;

import java.util.List;

public interface FileService {

    long getCountFromFile(List<String[]> file);
    long getCountDistinctFromFile(List<String[]> file, String[] head, String property);
    List<String[]> getFilteredListFromFile(List<String[]> file, String[] head, String property, String value);
}
