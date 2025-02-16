package calculator.core;

/**
 * Interface for the execution mode
 */
public interface ExecutionMode {
    void handleDigit(State state, String digit);
}
