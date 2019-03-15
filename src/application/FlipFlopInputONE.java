/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

public class FlipFlopInputONE
{
  
  boolean d_Input;

  /**
   * method to add input to the flip flop
   * @param d the is input t be added
   */
  public FlipFlopInputONE(boolean d)
  {
    d_Input = false;
  }

  /**
   * method to set input of flip flop
   * @param d the input to be added
   */
  public void setD(boolean d)
  {
    d_Input = d;
  }

  /**
   * getter method to return D
   * @return the value of D input
   */
  public boolean getD()
  {
    return d_Input;
  }
}
    