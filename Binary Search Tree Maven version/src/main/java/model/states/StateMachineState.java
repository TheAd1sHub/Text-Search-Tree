package model.states;

public abstract class StateMachineState<TMachine extends StateMachine> {

    protected final TMachine stateMachine;


    public StateMachineState(TMachine stateMachine) {
        this.stateMachine = stateMachine;
    }


    public abstract void enter();

    public abstract void update();

    public abstract void exit();

}
