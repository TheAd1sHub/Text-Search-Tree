package model.states.main;

import debug.logging.MainLogger;
import model.constants.MessageTexts;
import model.data.database.connectors.concrete.SearchTreeDBConnector;
import model.data.database.migrators.concrete.SearchTreeDBMigrator;
import model.states.ProcessingState;
import model.states.StateMachine;
import model.states.exceptions.InternalStateErrorException;
import view.printers.MessagePrinter;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public final class MainFSM extends StateMachine<MainFSMState> {

    public SearchTreeDBConnector database = SearchTreeDBConnector.getInstance();
    private SearchTreeDBMigrator migrator = SearchTreeDBMigrator.getInstance();

    private static MainFSM instance;

    private static boolean isInitialized = false;

    private Deque<MainFSMState> statesStack;

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
        statesStack = new ArrayDeque<>();

        MainFSMState initialState = ChoosingTextSourceState.getInstance();

        Arrays.stream(migrator.getMigrator().getConfiguration().getLocations()).forEach(System.out::println);

        migrator.getMigrator().baseline();
        migrator.getMigrator().migrate();

        try {
            database.init();
        } catch (SQLException ex) {
            // TODO: Add handling logic here
            MainLogger.logSevere(ex);
        }




        addState(initialState);

        setState(initialState);
        isInitialized = true;
    }


    public void terminate() {
        currentState.exit();
        currentState = null;

        try {
            database.closeConnection();
        } catch (SQLException ex) {
            // TODO: Add handling logic here
            throw new RuntimeException("Something's wrong with SQL!\n" + ex.getMessage());
        }

        statesStack = null;

        isInitialized = false;
    }

    // Silent state change is a change without implicit call of the .enter() method of the new state
    public void setPreviousState(boolean setSilently) {
        statesStack.pop(); // Removing the current state
        MainFSMState previousState = statesStack.pop();


        if (setSilently) {
            currentState = previousState;
            statesStack.push(currentState);
        } else {
            setState(previousState);
        }

    }


    @Override
    public void setState(MainFSMState nextState) {
        super.setState(nextState);

        if (isInitialized) {
            statesStack.push(nextState);
        }
    }

    @Override
    public void update() {
        if (!isInitialized) {
            return;
        }

        try {

            super.update();
        } catch (InternalStateErrorException ex) {

            if (ex.getInternalException() != null) {
                messagePrinter.print(ex.getInternalException().getLocalizedMessage());
            } else {
                messagePrinter.print(MessageTexts.getUnknownErrorNotificationText());
            }

            if (currentState instanceof ProcessingState) {
                rollBackToLastCheckpointState();
            }
        }
    }

    private void rollBackToLastCheckpointState() {
        if (statesStack.size() <= 1) {
            throw new UnsupportedOperationException("Cannot roll back when the amount of states is less than 2.");
        }

        while(statesStack.size() > 1 && currentState instanceof ProcessingState) {
            setPreviousState(true);
        }

        if (currentState instanceof ProcessingState) {
            throw new IllegalStateException("The ProcessingState type states could not be found in stack.");
        }

        currentState.enter();
    }
}
