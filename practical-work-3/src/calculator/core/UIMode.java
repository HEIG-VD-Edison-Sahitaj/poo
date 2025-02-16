package calculator.core;

/**
 * UI mode for the calculator
 */
public class UIMode implements ExecutionMode {
    /**
     * Handle the digit input
     * @param state The current state of the calculator
     * @param digit The digit to handle
     */
    @Override
    public void handleDigit(State state, String digit) {
        if (state.isResultDisplayed()) {
            state.setResultDisplayed(false);
            if (!state.getNewValue().equals("0"))
                state.pushValue(Double.parseDouble(state.getNewValue()));
            state.setNewValue(digit);
        } else {
            state.setNewValue(state.getNewValue().equals("0") ? String.valueOf(digit) : state.getNewValue() + digit);
        }
    }
}
