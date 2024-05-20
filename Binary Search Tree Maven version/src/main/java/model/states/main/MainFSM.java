package model.states.main;

import model.constants.MessageTexts;
import model.states.StateMachine;
import model.states.exceptions.InternalStateErrorException;
import view.printers.MessagePrinter;

import java.io.IOException;

public final class MainFSM extends StateMachine<MainFSMState> {

    private static MainFSM instance;

    private static boolean isInitialized = false;

    private MessagePrinter messagePrinter = new MessagePrinter();


    private MainFSM() {
        ;
    }


    public static synchronized MainFSM getInstance() {
        if (instance == null) {
            instance = new MainFSM();
        }

        return instance;
    }

    public boolean isWorking() {

        return isInitialized;
    }

    public void initialize() {
        MainFSMState initialState = ChoosingTextSourceState.getInstance();

        addState(initialState);

        setState(initialState);
        isInitialized = true;
    }


    public void terminate() {
        currentState.exit();
        currentState = null;
        isInitialized = false;
    }

    @Override
    public void update() {
        try {

            super.update();
        } catch (InternalStateErrorException ex) {

            if (ex.getInternalException() != null) {
                messagePrinter.print(ex.getInternalException().getLocalizedMessage());
            } else {
                messagePrinter.print(MessageTexts.unknownErrorNotificationText);
            }
        }
    }

}
