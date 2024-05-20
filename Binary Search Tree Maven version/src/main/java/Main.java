import debug.logging.MainLogger;
import model.states.main.MainFSM;

public final class Main {

    public static void main(String[] args) {
        MainLogger.logMessage("App initialized");

        init();

        MainLogger.logMessage("App work finished");
    }

    public static void init() {

        final MainFSM APP = MainFSM.getInstance();
        APP.initialize();

        while (APP.isWorking()) {
            APP.update();
        }

    }
}