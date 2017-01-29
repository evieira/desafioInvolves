package br.com.involves.service.fileUtil;

import br.com.involves.util.FileUtil;
import br.com.involves.util.Messages;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class FileUtilFindIndexOfPropertyTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void findIndexOfPropertyCannotReciveANullLine() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.FILE_WITHOUT_HEAD));
        FileUtil.findIndexOfProperty(null, null);
    }

    @Test
    public void findIndexOfPropertyCannotReciveAnEmptyLine() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.FILE_WITHOUT_HEAD));
        FileUtil.findIndexOfProperty(new String[0], null);
    }

    @Test
    public void findIndexOfPropertyCannotReciveALineWithEmptyColumns() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.FILE_WITHOUT_HEAD));
        FileUtil.findIndexOfProperty(new String[1], null);
    }

    @Test
    public void findIndexOfPropertyCannotReciveANullProperty() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.PROPERTY_EMPTY));
        FileUtil.findIndexOfProperty(getHeadLine(), null);
    }

    @Test
    public void findIndexOfPropertyCannotReciveAnEmptyProperty() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.PROPERTY_EMPTY));
        FileUtil.findIndexOfProperty(getHeadLine(), "");
    }

    @Test
    public void findIndexOfPropertyCannotReciveAnInvalidProperty() {
        expectedEx.expect(IndexOutOfBoundsException.class);
        expectedEx.expectMessage(Messages.getMessage(Messages.PROPERTY_NOT_FOUND));
        FileUtil.findIndexOfProperty(getHeadLine(), "Column2");
    }

    @Test
    public void findIndexOfPropertyReciveValidParameters() {
        assertEquals(0, FileUtil.findIndexOfProperty(getHeadLine(), "Column"));
    }

    private String[] getHeadLine() {
        String[] result = new String[1];
        result[0] = "Column";
        return result;
    }
}
