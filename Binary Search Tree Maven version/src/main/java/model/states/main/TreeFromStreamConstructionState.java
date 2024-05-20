package model.states.main;

import model.core.searchtree.TextBinarySearchTree;
import model.data.parsers.TextParser;
import model.data.readers.ExternalTextDataReader;
import model.states.exceptions.InternalStateErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TreeFromStreamConstructionState extends MainFSMState {

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
            words = TextParser.getWords(reader.next());
            tree.insertAll(words);
        }
    }

    @Override
    public void update() throws InternalStateErrorException {


    }

    @Override
    public void exit() {
        reader.close();
    }
}
