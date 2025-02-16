package calculator.core;

/**
 * Console mode for the calculator
 */
public class ConsoleMode implements ExecutionMode {
    /**
     * Handle the digit input
     * @param state The current state of the calculator
     * @param digit The digit to handle
     */
    @Override
    public void handleDigit(State state, String digit) {
        if (state.isResultDisplayed()) {
            state.setResultDisplayed(false);
            pushIfNotZero(state);
            state.setNewValue(String.valueOf(digit));
        } else if (!state.getNewValue().equals("0")) {
            pushIfNotZero(state);
            state.setNewValue(String.valueOf(digit));
        } else {
            state.setNewValue(String.valueOf(digit));
        }
    }

    /**
     * Push the current newValue to the stack if it is not "0"
     * @param state The current state of the calculator
     */
    private void pushIfNotZero(State state) {
        if (!state.getNewValue().equals("0")) {
            state.pushValue(Double.parseDouble(state.getNewValue()));
        }
    }
}
