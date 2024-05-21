package model.states;

/**
 * This marker interface is implemented by {@code StateMachineState} objects which are responsible for handling the information,
 * but cannot fix the problems arising in case the provided data is invalid on their own.
 * In this case such methods should throw an exception signalising their {@code StateMachine} that they cannot finish the
 * assigned task with the current data.
 * After receiving such an exception State Machine must roll back to the last {@code CheckpointState} in order to fix the issue.
 */
public interface ProcessingState {
}
