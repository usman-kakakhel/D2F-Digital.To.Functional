/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

public abstract class ComplexCircuitsTWO extends GateInput
{


  /**
   * getter method to get output
   * @return the output
   */
  public abstract boolean getOutput();

    /**
     * void to input the inputs
     * @param j first input
     * @param k second input
     */
  public abstract void input(boolean j, boolean k);

    /**
     * methods to getInput of FlipFlop
     * @return the inputs
     */
  public abstract FlipFlopInputTWO getInput();
  
}