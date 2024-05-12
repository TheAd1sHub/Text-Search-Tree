package controller.userinput;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.filework.FileValidator;
import view.MessagePrinter;

public final class FileNameInputReader extends BaseUserInputReader {
    private static final Scanner scanner = new Scanner(System.in);

    private FileNameInputReader() {
        ;
    }

    public static String read(boolean validateExistance, boolean validateExtension)
    					throws IOException, FileNotFoundException {
        String fileName = read();

        if (validateExistance) {
            FileValidator.validateExistance(fileName);
        }

        if (validateExtension) {
            FileValidator.validateExtension(fileName);
        }

        return fileName;
    }

    public static String read() throws InputMismatchException {
        MessagePrinter.printFileNameRequest();
        String givenFileName = scanner.nextLine();
        
        return givenFileName;
    }
    
}
