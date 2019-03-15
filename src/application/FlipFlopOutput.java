/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

public class FlipFlopOutput
{

  //properties
  boolean output;
  boolean cmp_output;

  //constructor
  public FlipFlopOutput(boolean out, boolean cmp)
  {
    output = out;
    cmp_output = cmp;
  }

  /**
   * getter method to get value of out
   * @return value of out
   */
  public boolean getOut()
  {
    return output;
  }

  /**
   * getter method to get value of compare(cmp_output)
   * @return value of cmp_output
   */
  public boolean getCmp()
  {
    return cmp_output;
  }

    /**
     * void to set condition of out
     * @param condition condition to be set
     */
  public void setOut(boolean condition)
  {
    output = condition;
  }

    /**
     * void to set condition of cmp_output
     * @param condition the condition to be set
     */
  public void setCmp(boolean condition)
  {
    cmp_output = condition;
  }
}