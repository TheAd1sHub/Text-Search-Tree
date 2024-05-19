package model.data.validators;

import java.nio.file.Files;
import java.nio.file.Paths;

import model.constants.AllowedExtensions;
import model.data.parsers.FileNameParser;

public final class FileNameValidator
        extends Validator<String> {

    private final FileNameParser parser;


    public FileNameValidator() {
        this.parser = new FileNameParser();
    }


    @Override
    public boolean isValid(String fileName) {
        try {

            return exists(fileName) && isExtensionValid(fileName);
        } catch (Exception ex) {

            return false;
        }
    }


    private boolean isExtensionValid(String fileName) {
        String fileExtension = parser.getExtension(fileName);

        return AllowedExtensions.getAllowedExtensionsList().contains(fileExtension);
    }

    private boolean exists(String fileName) {

        return Files.exists(Paths.get(fileName));
    }

}
