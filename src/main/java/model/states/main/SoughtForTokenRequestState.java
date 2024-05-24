package model.states.main;

import controller.ConsoleInputReader;
import model.constants.MessageTexts;
import model.core.searchtree.TextBinarySearchTree;
import model.states.CheckpointState;
import model.states.exceptions.InternalStateErrorException;
import org.jetbrains.annotations.NotNull;
import view.printers.MessagePrinter;

import java.util.HashMap;
import java.util.Map;

public final class SoughtForTokenRequestState extends MainFSMState
                                            implements CheckpointState {

    private final static Map<TextBinarySearchTree, SoughtForTokenRequestState> tokenReceivingInstances = new HashMap<>();


    private final TextBinarySearchTree tree;

    private final MessagePrinter messagePrinter = new MessagePrinter();

    private final ConsoleInputReader inputReader = new ConsoleInputReader();


    private SoughtForTokenRequestState(MainFSM stateMachine, @NotNull TextBinarySearchTree tree) {
        super(stateMachine);
        this.tree = tree;
    }

    public static synchronized SoughtForTokenRequestState getInstance(TextBinarySearchTree tree) {
        SoughtForTokenRequestState instance = tokenReceivingInstances.get(tree);
        if (instance == null) {
            instance = new SoughtForTokenRequestState(MainFSM.getInstance(), tree);
            tokenReceivingInstances.put(tree, instance);
        }

        return instance;
    }


    @Override
    public void enter() {
        messagePrinter.print(MessageTexts.getSoughtForTokenRequestText());
    }

    @Override
    public void update() throws InternalStateErrorException {
        messagePrinter.printInputRequest();
        String userInput = inputReader.readLine();

        stateMachine.setState(TreeSearchingState.getInstance(tree, userInput));
    }

}
