package calculator.core;

import calculator.utils.Stack;

/**
 * State of the calculator application.
 */
public class State {
    private Stack<Double> stack;
    private String error;
    private String newValue;
    private String memoryValue;
    private boolean resultDisplayed;
    private ExecutionMode executionMode; // To know the execution mode

    /**
     * Constructor
     * @param executionMode the execution mode
     */
    public State(ExecutionMode executionMode) {
        this.stack = new Stack<>();
        this.newValue = "0";
        this.memoryValue = "0";
        this.error = null;
        this.resultDisplayed = false;
        this.executionMode = executionMode;
    }

    /**
     * Push the value to the stack
     * @param value the value to push
     */
    public void pushValue(Double value) {
        stack.push(value);
    }

    /**
     * Pop the value from the stack
     * @return the value popped from the stack
     */
    public Double popValue() {
        return stack.isEmpty() ? 0.0 : stack.pop();
    }

    /**
     * Clear the error message
     */
    public void clearError() {
        error = null;
        newValue = "0";
    }

    /**
     * Clear the stack and the error message
     */
    public void clear() {
        stack = new Stack<>();
        clearError();
    }

    /**
     * Convert the stack to a string array
     * @return the string array representation of the stack
     */
    public String[] stackToStringArray() {
        String[] result = new String[stack.size()];
        int i = 0;
        for (Double value : stack) {
            result[i++] = value.toString();
        }
        return result;
    }

    /**
     * Get the stack
     * @return the stack
     */
    public Stack<Double> getStack() {
        return stack;
    }

    /**
     * Set the error message
     * @param message the error message
     */
    public void setError(String message) {
        error = message;
    }

    /**
     * Get the error message
     * @return the error message
     */
    public String getError() {
        return error;
    }

    /**
     * Set the memory value
     * @param value the memory value
     */
    public void setMemoryValue(String value) {
        memoryValue = value;
    }

    /**
     * Get the memory value
     * @return the memory value
     */
    public String getMemoryValue() {
        return memoryValue.isEmpty() ? newValue : memoryValue;
    }

    /**
     * Set the new value
     * @param newValue the new value
     */
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    /**
     * Get the new value
     * @return the new value
     */
    public String getNewValue() {
        return newValue;
    }

    /**
     * Set the result displayed flag
     * @param resultDisplayed the result displayed flag
     */
    public void setResultDisplayed(boolean resultDisplayed) {
        this.resultDisplayed = resultDisplayed;
    }

    /**
     * Check if the result is displayed
     * @return true if the result is displayed, false otherwise
     */
    public boolean isResultDisplayed() {
        return resultDisplayed;
    }

    /**
     * Set the execution mode
     * @param executionMode the execution mode
     */
    public void setExecutionMode(ExecutionMode executionMode) {
        this.executionMode = executionMode;
    }

    /**
     * Get the execution mode
     * @return the execution mode
     */
    public ExecutionMode getExecutionMode() {
        return executionMode;
    }
}
