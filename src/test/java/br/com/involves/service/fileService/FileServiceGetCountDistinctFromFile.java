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
public class FileServiceGetCountDistinctFromFile {

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
    public void getCountDistinctFromFileReciveANullFile() {
        assertEquals(0, fileService.getCountDistinctFromFile(null, head, "uf"));
    }

    @Test
    public void getCountDistinctFromFileReciveAnEmptyFile() {
        assertEquals(0, fileService.getCountDistinctFromFile(new ArrayList<>(), head, "uf"));
    }

    @Test
    public void getCountDistinctFromFileReciveANotEmptyFile() {
        assertEquals(3, fileService.getCountDistinctFromFile(file, head, "uf"));
    }

    @Test
    public void getCountDistictFromFileReciveANullHead() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.FILE_WITHOUT_HEAD));
        fileService.getCountDistinctFromFile(file, null, "uf");
    }

    @Test
    public void getCountDistictFromFileReciveANullProperty() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.PROPERTY_EMPTY));
        fileService.getCountDistinctFromFile(file, head, null);
    }

    @Test
    public void getCountDistictFromFileReciveAInvalidProperty() {
        expectedEx.expect(IndexOutOfBoundsException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.PROPERTY_NOT_FOUND));
        fileService.getCountDistinctFromFile(file, head, "property");
    }
}
