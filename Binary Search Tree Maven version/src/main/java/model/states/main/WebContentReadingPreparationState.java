package model.states.main;

import controller.ConsoleInputReader;
import model.constants.MessageTexts;
import model.data.validators.UrlValidator;
import model.input.exceptions.InvalidInputException;
import model.states.exceptions.InternalStateErrorException;
import model.ui.menu.main.SupportedTextSources;
import view.printers.MessagePrinter;

public final class WebContentReadingPreparationState extends MainFSMState
                                                    implements CheckpointState {

    private static WebContentReadingPreparationState instance;

    private SupportedTextSources dataReadingSource;
    private boolean isActive;

    private final MessagePrinter messagePrinter = new MessagePrinter();

    private final ConsoleInputReader inputReader = new ConsoleInputReader();
    private final UrlValidator inputValidator = new UrlValidator();


    private WebContentReadingPreparationState(MainFSM stateMachine) {
        super(stateMachine);
    }

    public static synchronized WebContentReadingPreparationState getInstance() {
        if (instance == null) {
            instance = new WebContentReadingPreparationState(MainFSM.getInstance());
        }

        return instance;
    }


    public void setReadingSource(SupportedTextSources source)
            throws IllegalArgumentException, UnsupportedOperationException {

        if (!source.isWebSource) {
            throw new IllegalArgumentException("Cannot set non-web reading source for this state.");
        }

        if (isActive) {
            throw new UnsupportedOperationException("Cannot change reading source when the state is active.");
        }

        dataReadingSource = source;
    }

    public SupportedTextSources getCurrentReadingSource() {

        return dataReadingSource;
    }

    @Override
    public void enter() {
        isActive = true;

        messagePrinter.print(MessageTexts.webPageUrlAddressRequestText);
    }

    @Override
    public void update() throws InternalStateErrorException {
        messagePrinter.printInputRequest();

        String userInput = inputReader.readLine();

        if (!inputValidator.isValid(userInput)) {
            throw new InternalStateErrorException(new InvalidInputException("Cannot find '" + userInput + "' file."));
        }

        switch (dataReadingSource) {
            case EXTERNAL_FILE:
                stateMachine.setState(ExternalFileReadingPreparationState.getInstance(userInput));
                break;

            case WEB_PAGE_CONTENTS:
                throw new UnsupportedOperationException("Not implemented");

            case WEB_PAGE_CONTENTS_RAW:
                stateMachine.setState(RawWebPageContentsReadingPreparationState.getInstance(userInput));
                break;

            default:
                throw new InternalStateErrorException(new UnsupportedOperationException("This operation is not supported."));
        }
    }

    @Override
    public void exit() {
        isActive = false;
    }
}
