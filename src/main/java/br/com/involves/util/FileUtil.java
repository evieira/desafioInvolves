package br.com.involves.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {

    public static List<String[]> getFileContent(String fileName) throws IOException {
        List<String[]> result;
        try {
            validFileName(fileName);
            result = Files.lines(Paths.get(fileName)).map(line -> line.split(",")).collect(Collectors.toList());
        } catch (NoSuchFileException e) {
          throw new NoSuchFileException(Messages.getMessage(Messages.FILE_NOT_FOUND));
        }
        return result;
    }

    public static int findIndexOfProperty(String[] line, String property) {
        validHeadLine(line);
        validProperty(property);
        for (int i = 0; i < line.length; i++) {
            if(line[i].equalsIgnoreCase(property)) {
                return i;
            }
        }
        throw new IndexOutOfBoundsException(Messages.getMessage(Messages.PROPERTY_NOT_FOUND));
    }

    private static void validFileName(String fileName) {
        if(fileName == null) {
            throw new NullPointerException(Messages.getMessage(Messages.FILE_PATH_EMPTY));
        }

        if(fileName.isEmpty()) {
            throw new NullPointerException(Messages.getMessage(Messages.FILE_PATH_EMPTY));
        }
    }

    private static void validHeadLine(String[] line) {
        if(line == null) {
            throw new NullPointerException(Messages.getMessage(Messages.FILE_WITHOUT_HEAD));
        }
        if(line.length == 0) {
            throw new NullPointerException(Messages.getMessage(Messages.FILE_WITHOUT_HEAD));
        }

        for (String column : line) {
            if(column == null) {
                throw new NullPointerException(Messages.getMessage(Messages.FILE_WITHOUT_HEAD));
            }
        }

    }

    private static void validProperty(String property) {
        if(property == null) {
            throw new NullPointerException(Messages.getMessage(Messages.PROPERTY_EMPTY));
        }

        if(property.isEmpty()) {
            throw new NullPointerException(Messages.getMessage(Messages.PROPERTY_EMPTY));
        }
    }
}
