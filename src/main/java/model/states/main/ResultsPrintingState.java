package model.states.main;

import model.constants.ExitStatuses;
import model.core.searchtree.SearchResultData;
import model.states.exceptions.InternalStateErrorException;
import view.printers.SearchResultDataPrinter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultsPrintingState extends MainFSMState
                                    implements ProcessingState{

    private final static Map<List<SearchResultData>, ResultsPrintingState> resultsPrintingInstances = new HashMap<>();

    private final List<SearchResultData> data;
    private final SearchResultDataPrinter printer = new SearchResultDataPrinter();


    private ResultsPrintingState(MainFSM stateMachine, List<SearchResultData> data) {
        super(stateMachine);
        this.data = data;
    }

    public static ResultsPrintingState getInstance(List<SearchResultData> data) {

        ResultsPrintingState instance = resultsPrintingInstances.get(data);
        if (instance == null) {
            instance = new ResultsPrintingState(MainFSM.getInstance(), data);
            resultsPrintingInstances.put(data, instance);
        }

        return instance;
    }


    @Override
    public void enter() {
        printer.printAll(data);

        System.exit(ExitStatuses.SUCCESS.code);
    }

    @Override
    public void update() throws InternalStateErrorException {

    }

    @Override
    public void exit() {

    }
}
