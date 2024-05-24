package model.states.main;

import controller.ConsoleInputReader;
import model.constants.MessageTexts;
import model.data.readers.ExternalTextDataReader;
import model.data.readers.FileDataReader;
import model.data.validators.FileNameValidator;
import model.input.exceptions.InvalidInputException;
import model.states.CheckpointState;
import model.states.exceptions.InternalStateErrorException;
import view.printers.MessagePrinter;

import java.io.IOException;

public final class LocalFileReadingPreparationState extends MainFSMState
                                                    implements CheckpointState {

    private static LocalFileReadingPreparationState instance;

    private final MessagePrinter messagePrinter = new MessagePrinter();

    private final ConsoleInputReader inputReader = new ConsoleInputReader();
    private final FileNameValidator inputValidator = new FileNameValidator();

    //private __ interpreter = new IntegerInputInterpreter();


    private LocalFileReadingPreparationState(MainFSM stateMachine) {
        super(stateMachine);
    }

    public static synchronized LocalFileReadingPreparationState getInstance() {
        if (instance == null) {
            instance = new LocalFileReadingPreparationState(MainFSM.getInstance());
        }

        return instance;
    }


    @Override
    public void enter() {
        messagePrinter.print(MessageTexts.getLocalFilePathRequestText());
    }
    @Override
    public void update() throws InternalStateErrorException {
        messagePrinter.printInputRequest();
        String userInput = inputReader.readLine();

        if (!inputValidator.isValid(userInput)) {
            throw new InternalStateErrorException(new InvalidInputException("The file at '" + userInput + "' does not exist or has unsupported format."));
        }

        try {
            ExternalTextDataReader reader = new FileDataReader(userInput);

            stateMachine.setState(TreeFromStreamConstructionState.getInstance(reader));

        } catch (IOException ex) {
            throw new InternalStateErrorException(new InvalidInputException("Unable to read the file. Please, check your input or try again later."));
        }
    }

    @Override
    public void exit() {

    }
}
