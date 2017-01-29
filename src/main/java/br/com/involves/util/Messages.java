package br.com.involves.util;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {

    public static final String FILE_NOT_FOUND = "fileNotFound";
    public static final String FILE_PATH_EMPTY = "filePathEmpty";
    public static final String FILE_WITHOUT_HEAD = "fileWithoutHead";
    public static final String FILE_EMPTY = "fileEmpty";
    public static final String TOTAL_COUNT = "totalCount";
    public static final String COUNT_DISTINCT = "totalDistinct";

    public static final String PROPERTY_NOT_FOUND = "propertyNotFound";
    public static final String PROPERTY_EMPTY = "propertyEmpty";
    public static final String PROPERTY_VALUE_EMPTY = "propertyValueEmpty";

    public static final String THANK_YOU = "thankYou";
    public static final String COMMANDS_AVAILABLE = "commandsAvailable";
    public static final String TYPE_FILE_PATH = "typeFilePath";
    public static final String COMMAND_NOT_FOUND = "commandNotFound";



    public static String getMessage(String message, Object... parameters) {
        String result = ResourceBundle.getBundle("messages", Locale.getDefault()).getString(message);
        return MessageFormat.format(result, parameters);
    }
}
