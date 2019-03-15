/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

public class FlipFlopInputTWO
{
  
  boolean j_Input;
  boolean k_Input;
  boolean clock;

  //constructor
  public FlipFlopInputTWO(boolean j, boolean k)
  {
    j_Input = j;
    k_Input = k;
    clock = false;
  }

  /**
   * getter method to get value of J
   * @return returns the value of J
   */
  public boolean getJ()
  {
    return j_Input;
  }

  /**
   * getter method to get value of K
   * @return returns the value of K
   */
  public boolean getK()
  {
    return k_Input;
  }

  /**
   * void to set value of J
   * @param condition the condition which is to be set
   */
  public void setJ(boolean condition)
  {
    j_Input = condition;
  }

  /**
   * void to set value of K
   * @param condition the condition which to be set
   */
  public void setK(boolean condition)
  {
    k_Input = condition;
  }
}