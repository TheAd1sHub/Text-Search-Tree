package model.states;

/**
 * This marker interface is implemented by {@code StateMachineState} objects which are responsible for handling the information,
 * and are able to fix the problems arising in case the data provided to them or the following {@code ProcessingState} objects is invalid on their own.
 * All the {@code ProcessingState} states sandwiched between this and the next {@code CheckpointState} will roll back to this
 * {@code ProcessingState} in case they will face a problem described above.
 */
public interface CheckpointState {
}
