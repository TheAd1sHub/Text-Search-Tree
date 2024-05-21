package model.states.main;

import controller.ConsoleInputReader;
import model.input.exceptions.InputValueOutOfRangeException;
import model.input.exceptions.InvalidInputException;
import model.input.interpreters.IntegerInputInterpreter;
import model.states.CheckpointState;
import model.states.exceptions.InternalStateErrorException;
import model.ui.menu.main.MainMenu;
import model.ui.menu.main.SupportedTextSources;
import view.printers.MenuPrinter;
import view.printers.MessagePrinter;

import java.util.InputMismatchException;

public final class ChoosingTextSourceState extends MainFSMState
                                            implements CheckpointState {

    private static ChoosingTextSourceState instance;

    private final MenuPrinter menuPrinter = new MenuPrinter();
    private final MessagePrinter messagePrinter = new MessagePrinter();

    private final ConsoleInputReader inputReader = new ConsoleInputReader();

    private final IntegerInputInterpreter interpreter = new IntegerInputInterpreter();


    private ChoosingTextSourceState(MainFSM stateMachine) {
        super(stateMachine);
    }


    public static synchronized ChoosingTextSourceState getInstance() {
        if (instance == null) {
            instance = new ChoosingTextSourceState(MainFSM.getInstance());
        }

        return instance;
    }


    @Override
    public void enter() {
        menuPrinter.print(MainMenu.getInstance());
    }

    @Override
    public void update() throws InternalStateErrorException {
        messagePrinter.printInputRequest();
        String userInput = inputReader.readLine();

        int interpretedUserInput;
        try {
            interpretedUserInput = interpreter.interpret(userInput);
        } catch (InputMismatchException ex) {

            throw new InternalStateErrorException(
                    new InvalidInputException("The given input cannot be interpreted as an option choice.")
            );
        }

        SupportedTextSources readingSource = SupportedTextSources.getFittingEnumValue(interpretedUserInput);
        switch (readingSource) {
            case LOCAL_FILE:
                stateMachine.setState(LocalFileReadingPreparationState.getInstance());
                break;

            case EXTERNAL_FILE:
            case WEB_PAGE_CONTENTS:
            case WEB_PAGE_CONTENTS_RAW:
                WebContentReadingPreparationState nextState = WebContentReadingPreparationState.getInstance();
                nextState.setReadingSource(readingSource);
                stateMachine.setState(nextState);
                break;

            default:
                throw new InternalStateErrorException(
                        new InputValueOutOfRangeException("This option does not exist.")
                );
        }
    }

    @Override
    public void exit() {

    }
}
