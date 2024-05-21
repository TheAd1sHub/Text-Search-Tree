package model.states.main;

import model.core.searchtree.SearchResultData;
import model.core.searchtree.TextBinarySearchTree;
import model.states.ProcessingState;
import model.states.exceptions.InternalStateErrorException;
import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TreeSearchingState extends MainFSMState
                                            implements ProcessingState {

    private final static Map<TreeAndTokenPackage, TreeSearchingState> treeSearchingInstances = new HashMap<>();

    private final TreeAndTokenPackage searchComponents;


    private TreeSearchingState(MainFSM stateMachine, TextBinarySearchTree tree, String soughtForToken) {
        super(stateMachine);

        searchComponents = new TreeAndTokenPackage(tree, soughtForToken);
    }

    public static TreeSearchingState getInstance(TextBinarySearchTree tree, String soughtForToken) {
        TreeAndTokenPackage givenComponents = new TreeAndTokenPackage(tree, soughtForToken);

        TreeSearchingState instance = treeSearchingInstances.get(givenComponents);
        if (instance == null) {
            instance = new TreeSearchingState(MainFSM.getInstance(), tree, soughtForToken);
            treeSearchingInstances.put(givenComponents, instance);
        }

        return instance;
    }


    @Override
    public void update() throws InternalStateErrorException {
        List<SearchResultData> hits = searchComponents.tree.findWith(searchComponents.searchToken);

        stateMachine.setState(ResultsPrintingState.getInstance(hits));
    }


    private record TreeAndTokenPackage(TextBinarySearchTree tree, String searchToken) {

            @Override
            public int hashCode() {
                return tree.hashCode() | searchToken.hashCode();
            }

            @Override
            public boolean equals(Object o) {
                if (o instanceof TreeAndTokenPackage other) {

                    return this.searchToken.equals(other.searchToken) && this.tree.equals(other.tree);
                }

                return false;
            }
        }

}
