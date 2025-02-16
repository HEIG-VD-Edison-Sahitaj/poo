package calculator.core;

/**
 * Abstract class for operators.
 */
public abstract class Operator {

    /**
     * Execute the operator.
     * @param state the state of the calculator
     */
    public abstract void execute(State state);

  /**
   * Parse the operand from the state.
   * @param state the state of the calculator
   * @return the operand
   */
  protected double parseOperand(State state) {
      double value;
      if (state.getNewValue().equals("0")) {
          if (state.getStack().size() > 1) {
              value = state.popValue();
          } else {
              value = Double.parseDouble(state.getNewValue());
          }
      } else {
          value = Double.parseDouble(state.getNewValue());
      }
      state.setNewValue("0");
      return value;
  }
}
