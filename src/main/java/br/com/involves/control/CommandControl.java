package br.com.involves.control;

import br.com.involves.service.FileService;
import br.com.involves.service.impl.FileServiceImpl;
import br.com.involves.util.FileUtil;
import br.com.involves.control.enumaration.CommandEnum;
import br.com.involves.util.Messages;

import java.io.IOException;
import java.util.List;

public class CommandControl {

    private List<String[]> file;
    private String[] head;
    private FileService fileService;

    public CommandControl() {
        fileService = new FileServiceImpl();
    }

    public void loadFile(String pathToFile) throws IOException {
        this.file = FileUtil.getFileContent(pathToFile);
        this.head = file.get(0);
        this.file.remove(0);
    }

    public void executeCommand(CommandEnum commandEnum, String... parameters) {

        switch (commandEnum) {
            case COUNT:
                System.out.println(Messages.getMessage(Messages.TOTAL_COUNT, fileService.getCountFromFile(this.file)));
                break;
            case DISTINCT:
                System.out.println(Messages.getMessage(Messages.COUNT_DISTINCT, parameters[0],
                        fileService.getCountDistinctFromFile(this.file, this.head, parameters[0])));
                break;
            case FILTER:
                try{
                    List<String[]> list = fileService.getFilteredListFromFile(this.file, this.head, parameters[0], parameters[1]);
                    printList(head, list);
                    break;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(Messages.getMessage(Messages.PROPERTY_VALUE_EMPTY));
                }
            default: break;
        }
    }

    private void printList(String[] head, List<String[]> list) {
        for (String column : head) {
            System.out.print(column);
            System.out.print(",");
        }
        System.out.println();
        list.stream().forEach(line -> {
            for (String column : line) {
                System.out.print(column);
                System.out.print(",");
            }
            System.out.println();
        });
    }
}
