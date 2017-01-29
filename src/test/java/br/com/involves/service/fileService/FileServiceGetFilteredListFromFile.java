package br.com.involves.service.fileService;

import br.com.involves.service.FileService;
import br.com.involves.service.impl.FileServiceImpl;
import br.com.involves.util.FileUtil;
import br.com.involves.util.Messages;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class FileServiceGetFilteredListFromFile {

    private static List<String[]> file;
    private static String[] head;
    private static FileService fileService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @BeforeClass
    public static void loadFileList() throws IOException {
        file = FileUtil.getFileContent(FileServiceGetCountFromFile.class.getClassLoader().getResource("cidades_test.csv").getPath());
        head = file.get(0);
        file.remove(0);
        fileService = new FileServiceImpl();
    }

    @Test
    public void getFilteredListFromFileCannotReciveANullFile() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.FILE_EMPTY));
        fileService.getFilteredListFromFile(null, head, "uf", "sc");
    }

    @Test
    public void getFilteredListFromFileReciveAnEmptyFile() {
        assertEquals(new ArrayList<String[]>(), fileService.getFilteredListFromFile(new ArrayList<>(), head, "uf", "sc"));
    }

    @Test
    public void getFilteredListFromFileReciveAFile() throws IOException {
        List<String[]> resultList = fileService.getFilteredListFromFile(file, head, "uf", "sc");
        assertEquals(293, resultList.size());
    }

    @Test
    public void getFilteredListFromFileReciveANullHead() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.FILE_WITHOUT_HEAD));
        fileService.getFilteredListFromFile(file, null, "uf", "sc");
    }

    @Test
    public void getFilteredListFromFileReciveANullProperty() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.PROPERTY_EMPTY));
        fileService.getFilteredListFromFile(file, head, null, "sc");
    }

    @Test
    public void getFilteredListFromFileReciveAInvalidProperty() {
        expectedEx.expect(IndexOutOfBoundsException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.PROPERTY_NOT_FOUND));
        fileService.getFilteredListFromFile(file, head, "property", "sc");
    }

    @Test
    public void getFilteredListFromFileReciveAnInvalidValue() {
        assertEquals(0, fileService.getFilteredListFromFile(file, head, "uf", "value").size());
    }
}
