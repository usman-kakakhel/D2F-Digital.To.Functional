/**
 * @authors Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;
import java.util.ArrayList;

public abstract class BasicGates extends GateInput
{

  /**
   * sets the GateInput of a gate
   * @param condition the GateInput which is to be set
   */
  public abstract void input(GateInput... condition);

  /**
   *  method to return array list of Gate Inputs of a gate
   * @return the GateInputs of a gate
   */
  public abstract ArrayList<GateInput> getInput();

  /**
   * void to set name of a gate
   * @param string the name to be set
   */
  public abstract void setName(String string);

  /**
   * getter method to get name of the gate
   * @return the name of the gate
   */
  public abstract String getName();

  /**
   * method to get the string from a gate
   * @return the string value of the gate
   */
  public abstract String getString();

  /**
   * method to return the size of inputs of that gate
   * @return the size of input values
   */
  public abstract int inValue();



//  public abstract String name();

}