package model.filework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import model.AllowedValues;

public final class FileValidator {
    private FileValidator() {
        ;
    }

    public static void validateExtension(String fileName) throws IllegalArgumentException {
        String fileExtension = FileNameAnalyzer.getExtension(fileName);

        if (!AllowedValues.getAllowedExtensionsList().contains(fileExtension)) {
            throw new IllegalArgumentException("Invalid input! Files of type '" + fileExtension + "' are not supported.");
        }
    }

    public static void validateExistance(String fileName) throws FileNotFoundException {
        if (Files.notExists(Paths.get(fileName))) {
            throw new FileNotFoundException("Invalid input! Unable to find '" + fileName + "' file.");
        }
    }
   
}
