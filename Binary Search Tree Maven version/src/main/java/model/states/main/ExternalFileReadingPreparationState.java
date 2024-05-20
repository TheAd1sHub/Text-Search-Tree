package model.states.main;

import model.constants.SavePaths;
import model.data.loaders.UrlFileDownloader;
import model.data.readers.FileDataReader;
import model.input.exceptions.InvalidInputException;
import model.states.exceptions.InternalStateErrorException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public final class ExternalFileReadingPreparationState extends MainFSMState {

    private final static Map<String, ExternalFileReadingPreparationState> urlReadingInstances = new HashMap<>();

    private final String targetUrl;


    private ExternalFileReadingPreparationState(MainFSM stateMachine, String targetUrl) {
        super(stateMachine);
        this.targetUrl = targetUrl;
    }

    public static synchronized ExternalFileReadingPreparationState getInstance(String targetUrl) {
        ExternalFileReadingPreparationState instance = urlReadingInstances.get(targetUrl);
        if (instance == null) {
            instance = new ExternalFileReadingPreparationState(MainFSM.getInstance(), targetUrl);
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
            String tempFilePath = SavePaths.temp;

            UrlFileDownloader downloader = new UrlFileDownloader(sourceUrl, tempFilePath);
            downloader.download();

            FileDataReader reader = new FileDataReader(new File(tempFilePath));

            stateMachine.setState(TreeFromStreamConstructionState.getInstance(reader));

        } catch (MalformedURLException ex) {

            throw new InternalStateErrorException(new InvalidInputException("The given URL is malformed or does not exist."));
        } catch (IOException e) {
            throw new InternalStateErrorException(new InvalidInputException("An error occurred trying to create TEMP file."));
        }

    }

    @Override
    public void exit() {

    }

}
