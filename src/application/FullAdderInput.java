/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

public class FullAdderInput extends Components
{
  
  boolean a;
  boolean b;
  boolean carryIn;
  
  public FullAdderInput()
  {
    a= false;
    b= false;
    carryIn = false;
  }

  /**
   * getter method to get type
   * @return the type of gate
   */
  public String getType()
  {
    return "FullAdderInput";
  }

  /**
   * method to set Full adder input
   * @param a the boolean which will be set to the input
   */
  public void setA(boolean a)
  {
    this.a = a;
  }

  /**
   * method to set Full adder input
   * @param b the boolean which will be set to the input
   */
  public void setB(boolean b)
  {
    this.b = b;
  }

  /**
   * method to set Full adder carry input
   * @param carryIn the boolean which will be set to the input
   */
  public void setCarryIn(boolean carryIn)
  {
    this.carryIn = carryIn;
  }

  /**
   * getter method to get first input
   * @return the value of input a
   */
  public boolean getA()
  {
    return a;
  }

  /**
   * getter method to get first input
   * @return the value of input b
   */
  public boolean getB()
  {
    return b;
  }

  /**
   * getter method to get first input
   * @return the value of input carryIn
   */
  public boolean getCarryIn()
  {
    return carryIn;
  }

  /**
   * Method forString representation
   * @return the string representation
   */
  public String toString()
  {
    return "";
  }
}