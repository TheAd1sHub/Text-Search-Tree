package model.states.main;

import model.constants.SavePaths;
import model.data.loaders.StringToFileLoader;
import model.data.parsers.HtmlBodyParser;
import model.data.readers.ExternalTextDataReader;
import model.data.readers.FileDataReader;
import model.data.readers.RawUrlDataReader;
import model.input.exceptions.InvalidInputException;
import model.states.ProcessingState;
import model.states.exceptions.InternalStateErrorException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class WebPageContentsReadingPreparationState extends MainFSMState
                                                        implements ProcessingState {

    private final static Map<String, WebPageContentsReadingPreparationState> urlReadingInstances = new HashMap<>();

    private final String targetUrl;


    private WebPageContentsReadingPreparationState(MainFSM stateMachine, String targetUrl) {
        super(stateMachine);
        this.targetUrl = targetUrl;
    }

    public static synchronized WebPageContentsReadingPreparationState getInstance(String targetUrl) {
        WebPageContentsReadingPreparationState instance = urlReadingInstances.get(targetUrl);
        if (instance == null) {
            instance = new WebPageContentsReadingPreparationState(MainFSM.getInstance(), targetUrl);
            urlReadingInstances.put(targetUrl, instance);
        }

        return instance;
    }


    // TODO: Implement this part or whatever
    @Override
    public void update() throws InternalStateErrorException {
        try {
            URL sourceUrl = new URL(targetUrl);
            HtmlBodyParser parser = new HtmlBodyParser();
            String htmlContentsRaw = sourceUrl.getContent().toString();
            System.out.println(htmlContentsRaw);
            String pageContents = parser.getContents(htmlContentsRaw);
            StringToFileLoader fileWriter = new StringToFileLoader(pageContents);

            fileWriter.loadInto(SavePaths.tempPageContents, true);
            ExternalTextDataReader reader = new FileDataReader(SavePaths.tempPageContents);

            stateMachine.setState(TreeFromStreamConstructionState.getInstance(reader));

        } catch (MalformedURLException ex) {

            throw new InternalStateErrorException(new InvalidInputException("The given URL is malformed or does not exist."));
        } catch (IOException ex) {

            throw new InternalStateErrorException(new InvalidInputException("Unable to parse the contents by given URL."));
        }
    }

}
