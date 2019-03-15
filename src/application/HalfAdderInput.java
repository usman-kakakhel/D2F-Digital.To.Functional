/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

public class HalfAdderInput
{
  
  boolean a;
  boolean b;
  
  public HalfAdderInput()
  {
    a = false;
    b = false;
  }

    /**
     * void to set input a
     * @param a the boolean first input
     */
  public void setA(boolean a)
  {
    this.a = a;
  }

    /**
     * void to set input b
     * @param b the boolean input b
     */
  public void setB(boolean b)
  {
    this.b = b;
  }

  /**
   * getter method to get first input
   * @return the input1
   */
  public boolean getA()
  {
    return a;
    
  }
  /**
   * getter method to get second input
   * @return the input1
   */
  public boolean getB()
  {
    return b;
  }
}