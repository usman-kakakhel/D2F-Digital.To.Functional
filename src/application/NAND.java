/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

import java.util.ArrayList;

public class NAND extends BasicGates
{
  
  ArrayList<GateInput> input;
  ArrayList<Boolean> boolInput;
  boolean output;
  String name;
  int level;
  
  public NAND()
  {
    level = 0;
    name = "(~(";
    boolInput = new ArrayList<Boolean>();
    input = new ArrayList<GateInput>();
    output = true;
  }

  /**
   * method to check size of inputs
   * @return value of inputs
   */
  public int inValue()
  {
    return input.size();
  }

  /**
   * void to set level
   * @param level which is to be set
   */
  public void setLevel(int level) {
    myLvl = level;
    this.level = level;
  }

  /**
   * getter method to get level
   * @return the level value
   */
  public int getLevel() {
    return myLvl;
  }

  @Override
  public String getString() {
    return null;
  }

  /**
   * void to set Names
   * @param string the name to be set
   */
  public void setName(String string)
  {
    name = string;
  }

  /**
   * method to get string
   * @return the string name
   */
  public String getType()
  {
    return "NAND";
  }

  /**
   * void to et name
   */
  public void setName()
  {
    for (int i = 0; i < input.size() ; i++)
    {
      if ( i == input.size() - 1 )
      {
        name = name + input.get(i).getName();
      }
      else
      {
        name = name + input.get(i).getName() + ".";
      }
    }
  }

  /**
   * getter method to get name
   * @return the name string
   */
  public String getName()
  {
    return name + "))";
  }

  /**
   * void to add GateInput
   * @param input the  GateInput which will be added
   */
  public void input(GateInput... input)
  {
    AND and = new AND();
    NOT not  = new NOT();
    if (input.length <= 1 )
    {
      System.out.println("Inputs Insufficient");
      return;
    }
    for (GateInput i: input)
    {
      this.input.add(i);
    }
    and.input(input);
    not.input(and.getOutput());
    output = not.getOutput();
    this.setName();
  }

  /**
   * void to add boolean input
   * @param input the boolean input will be added
   */
  public void input(Boolean... input)
  {  
    AND and = new AND();
    NOT not  = new NOT();
    if (input.length <= 1 )
    {
      System.out.println("Inputs Insufficient");
      return;
    }
    for (Boolean i: input)
    {
      this.boolInput.add(i);
    }
    and.input(input);
    not.input(and.getOutput());
    output = not.getOutput();  

  }

  /**
   * getter method to get Input
   * @return the input Array list
   */
  public ArrayList<GateInput> getInput()
  {
    return input;
  }

  /**
   * getter method to get boolean Input
   * @return the boolean input Array list
   */
  public ArrayList<Boolean> getBoolInput()
  {
    return boolInput;
  }

  /**
   * getter method to get Output
   * @return the boolean output
   */
  public boolean getOutput()
  {
    return output;
  }

  /**
   * string representation
   * @return the string presentation
   */
  public String toString()
  {
    return "NAND Gate: " + output;
  }
}