package model.states.main;

import model.core.searchtree.TextBinarySearchTree;
import model.data.parsers.TextParser;
import model.data.readers.ExternalTextDataReader;
import model.states.ProcessingState;
import model.states.exceptions.InternalStateErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TreeFromStreamConstructionState extends MainFSMState
                                                    implements ProcessingState {

    private final static Map<ExternalTextDataReader, TreeFromStreamConstructionState> urlReadingInstances = new HashMap<>();

    private final ExternalTextDataReader reader;
    private final TextBinarySearchTree tree = new TextBinarySearchTree();


    private TreeFromStreamConstructionState(MainFSM stateMachine, ExternalTextDataReader reader) {
        super(stateMachine);
        this.reader = reader;
    }

    public static synchronized TreeFromStreamConstructionState getInstance(ExternalTextDataReader reader) {
        TreeFromStreamConstructionState instance = urlReadingInstances.get(reader);
        if (instance == null) {
            instance = new TreeFromStreamConstructionState(MainFSM.getInstance(), reader);
            urlReadingInstances.put(reader, instance);
        }

        return instance;
    }


    @Override
    public void enter() {

        List<String> words;
        while (reader.hasNext()) {
            String nextLine = reader.next();
            
            if (nextLine != null) {
                words = TextParser.getWords(nextLine);
                tree.insertAll(words);
            }
        }

        System.out.println(tree);
    }

    @Override
    public void update() throws InternalStateErrorException {

        stateMachine.setState(SoughtForTokenRequestState.getInstance(tree));
    }

    @Override
    public void exit() {
        try {
            reader.close();
        } catch (Exception ex) {
            throw new InternalStateErrorException(new RuntimeException(ex));
        }
    }
}
