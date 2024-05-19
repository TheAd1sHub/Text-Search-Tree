package model.states;


import java.util.HashMap;
import java.util.Map;

public abstract class StateMachine<TState extends StateMachineState> {

    private TState currentState;
    private Map<Class<TState>, TState> states = new HashMap<>();

    public void addState(TState state) {
        states.put((Class<TState>) state.getClass(), state);
    }

    public void setState(TState nextState) {
        if (currentState != null) {
            currentState.exit();
        }

        currentState = nextState;
        currentState.enter();
    }

    public void update() {
        currentState.update();
    }

}
