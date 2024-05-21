package model.states.main;

import model.states.StateMachineState;

public abstract class MainFSMState extends StateMachineState<MainFSM> {

    public MainFSMState(MainFSM stateMachine) {
        super(stateMachine);
    }

}
