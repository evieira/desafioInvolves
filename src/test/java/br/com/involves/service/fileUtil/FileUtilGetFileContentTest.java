package br.com.involves.service.fileUtil;

import br.com.involves.util.FileUtil;
import br.com.involves.util.Messages;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class FileUtilGetFileContentTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void getFileContentCannotReciveANullFilePath() throws IOException {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.FILE_PATH_EMPTY));
        FileUtil.getFileContent(null);
    }

    @Test
    public void getFileContentCannotReciveAnEmptyFilePath() throws IOException {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.FILE_PATH_EMPTY));
        FileUtil.getFileContent("");
    }

    @Test
    public void getFileContentCannotReciveAnInvalidFilePath() throws IOException {
        expectedEx.expect(NoSuchFileException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.FILE_NOT_FOUND));
        FileUtil.getFileContent("/home/file.csv");
    }

    @Test
    public void getFileContentReciveAValidFilePath() throws IOException {
        List<String[]> file = FileUtil.getFileContent(this.getClass().getClassLoader().getResource("cidades_test.csv").getPath());
        assertEquals(696, file.size());
    }
}
