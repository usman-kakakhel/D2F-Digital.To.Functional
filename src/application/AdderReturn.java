/**
 * @authors Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

public class AdderReturn
{
  private boolean sum;
  private boolean carry;
  
  public AdderReturn()
  {
    sum = false;
    carry = false;
  }

    /**
     * void to set sum
     * @param sum sum to be set
     */
  public void setSum( boolean sum)
  {
    this.sum = sum;
  }

    /**
     * to set carry of adder
     * @param carry carry to be set
     */
  public void setCarry( boolean carry)
  {
    this.carry = carry;
  }

    /**
     * getter method to get sum
     * @return sum of adder
     */
  public boolean getSum()
  {
    return sum;
  }

    /**
     * getter method to get carry
     * @return  carry of adder
     */
  public boolean getCarry()
  {
    return carry;
  }
}