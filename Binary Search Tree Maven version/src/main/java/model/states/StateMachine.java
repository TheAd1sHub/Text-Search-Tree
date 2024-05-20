package model.states;


import java.util.HashMap;
import java.util.Map;

public abstract class StateMachine<TState extends StateMachineState> {

    protected TState currentState;
    protected Map<Class<TState>, TState> states = new HashMap<>();

    public void addState(TState state) {
        states.put((Class<TState>) state.getClass(), state);
    }

    public void setState(Class<? extends TState> stateType) {
        if (currentState != null) {
            currentState.exit();
        }

        currentState = states.get(stateType);
        if (currentState == null) {

            throw new IllegalArgumentException("A state of the given type is not registered in the state machine.");
        }

        currentState.enter();
    }

    public void setState(TState nextState) {
        if (currentState != null) {
            currentState.exit();
        }

        //currentState = states.get(nextState.getClass());
        currentState = nextState;
        if (nextState == null) {

            throw new IllegalArgumentException();
        }

        currentState.enter();
    }

    public void update() {
        currentState.update();

    }

}
