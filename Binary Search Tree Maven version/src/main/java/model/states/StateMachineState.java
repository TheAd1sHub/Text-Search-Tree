package model.states;

import model.states.exceptions.InternalStateErrorException;

public abstract class StateMachineState<TMachine extends StateMachine> {

    protected final TMachine stateMachine;


    public StateMachineState(TMachine stateMachine) {
        this.stateMachine = stateMachine;
    }


    public abstract void enter();

    public abstract void update() throws InternalStateErrorException;

    public abstract void exit();

}
