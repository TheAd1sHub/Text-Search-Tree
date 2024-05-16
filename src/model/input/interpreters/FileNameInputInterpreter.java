package model.input.interpreters;

import java.io.File;
import java.io.IOException;

import model.validators.FileNameValidator;

public final class FileNameInputInterpreter extends RawInputInterpreter<File> {

    @Override
    public File interpret(String input) throws IOException {
        FileNameValidator.validateExistence(input);
        FileNameValidator.validateExtension(input);

        return new File(input);
    }
    
}
