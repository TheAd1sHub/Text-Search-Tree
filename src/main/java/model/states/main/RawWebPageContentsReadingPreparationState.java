package model.states.main;

import model.data.readers.ExternalTextDataReader;
import model.data.readers.RawUrlDataReader;
import model.input.exceptions.InvalidInputException;
import model.states.exceptions.InternalStateErrorException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class RawWebPageContentsReadingPreparationState extends MainFSMState
                                                                implements ProcessingState {

    private final static Map<String, RawWebPageContentsReadingPreparationState> urlReadingInstances = new HashMap<>();

    private final String targetUrl;


    private RawWebPageContentsReadingPreparationState(MainFSM stateMachine, String targetUrl) {
        super(stateMachine);
        this.targetUrl = targetUrl;
    }

    public static synchronized RawWebPageContentsReadingPreparationState getInstance(String targetUrl) {
        RawWebPageContentsReadingPreparationState instance = urlReadingInstances.get(targetUrl);
        if (instance == null) {
            instance = new RawWebPageContentsReadingPreparationState(MainFSM.getInstance(), targetUrl);
            urlReadingInstances.put(targetUrl, instance);
        }

        return instance;
    }


    @Override
    public void enter() {

    }

    @Override
    public void update() throws InternalStateErrorException {

        try {
            URL sourceUrl = new URL(targetUrl);
            ExternalTextDataReader reader = new RawUrlDataReader(sourceUrl);

            stateMachine.setState(TreeFromStreamConstructionState.getInstance(reader));

        } catch (MalformedURLException ex) {

            throw new InternalStateErrorException(new InvalidInputException("The given URL is malformed or does not exist."));
        }

    }

    @Override
    public void exit() {

    }
}
