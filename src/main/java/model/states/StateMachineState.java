package model.states;

import model.states.exceptions.InternalStateErrorException;

public abstract class StateMachineState<TMachine extends StateMachine> {

    protected final TMachine stateMachine;


    public StateMachineState(TMachine stateMachine) {
        this.stateMachine = stateMachine;
    }


    public void enter() { }

    public void update() throws InternalStateErrorException { }

    public void exit() { }

}
