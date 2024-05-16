package model.validators;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

import model.AllowedValues;
import model.filework.FileNameAnalyzer;

public final class FileNameValidator
        extends Validator<String> {

    public static void validateExtension(String fileName) throws IllegalArgumentException {
        String fileExtension = FileNameAnalyzer.getExtension(fileName);

        if (!AllowedValues.getAllowedExtensionsList().contains(fileExtension)) {
            throw new IllegalArgumentException("Invalid input! Files of type '" + fileExtension + "' are not supported.");
        }
    }

    public static void validateExistence(String fileName) throws FileNotFoundException {
        if (Files.notExists(Paths.get(fileName))) {
            throw new FileNotFoundException("Invalid input! Unable to find '" + fileName + "' file.");
        }
    }

    @Override
    public boolean isValid(String value) {
        try {
            validateExtension(value);
            validateExistence(value);

        } catch (Exception ex) {
            return false;
        }

        return true;
    }
}
