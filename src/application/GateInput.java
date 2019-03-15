/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;


public abstract class GateInput extends Components
{
  /**
   * getter method which gives output
   * @return output value
   */
  public abstract boolean getOutput();

  /**
   * void to setName to set name
   * @param name the name which is to be set
   */
  public abstract void setName(String name);

  /**
   * getter method which retuns getName
   * @return the string name
   */
  public abstract String getName();

  /**
   * void to set Level
   * @param level which is to be set
   */
  public abstract void setLevel(int level);

  /**
   * getter method which return level
   * @return the integer value of level
   */
  public abstract int getLevel();


  
}