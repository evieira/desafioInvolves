package br.com.involves.service.fileService;

import br.com.involves.service.FileService;
import br.com.involves.service.impl.FileServiceImpl;
import br.com.involves.util.FileUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class FileServiceGetCountFromFile {

    private static List<String[]> file;
    private static FileService fileService;

    @BeforeClass
    public static void loadFileList() throws IOException {
        file = FileUtil.getFileContent(FileServiceGetCountFromFile.class.getClassLoader().getResource("cidades_test.csv").getPath());
        file.remove(0);
        fileService = new FileServiceImpl();
    }

    @Test
    public void getCountFromFileCannotReciveANullFile() {
        assertEquals(0, fileService.getCountFromFile(null));
    }

    @Test
    public void getCountFromFileReciveAnEmptyList() {
        assertEquals(0, fileService.getCountFromFile(new ArrayList<>()));
    }

    @Test
    public void getCountFromFileReciveANotEmptyList() {
        assertEquals(695, fileService.getCountFromFile(file));
    }

}
