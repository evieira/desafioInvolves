package br.com.involves.service.impl;

import br.com.involves.service.FileService;
import br.com.involves.util.FileUtil;
import br.com.involves.util.Messages;

import java.util.List;
import java.util.stream.Collectors;

public class FileServiceImpl implements FileService {

    @Override
    public long getCountFromFile(List<String[]> file) {
        if(file == null) {
            return 0l;
        }
        return file.size();
    }

    @Override
    public long getCountDistinctFromFile(List<String[]> file, String[] head, String property) {
        if(file == null) {
            return 0l;
        }
        int propertyIndex = FileUtil.findIndexOfProperty(head, property);
        return file.stream().map(line -> line[propertyIndex]).distinct().count();
    }

    @Override
    public List<String[]> getFilteredListFromFile(List<String[]> file, String[] head, String property, String value) {
        validFile(file);
        int propertyIndex = FileUtil.findIndexOfProperty(head, property);
        return file.stream().filter(line -> line[propertyIndex].equalsIgnoreCase(value)).collect(Collectors.toList());
    }

    private void validFile(List<String[]> file) {
        if(file == null) {
            throw new NullPointerException(Messages.getMessage(Messages.FILE_EMPTY));
        }
    }
}
