/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

import java.util.ArrayList;

public class OR extends BasicGates
{
  
  ArrayList<GateInput> input;
  ArrayList<Boolean> boolInput;
  boolean output;
  String name;
  String string;
  int level;
  
  public OR()
  {
    level = 0;
    string = "";
    boolInput = new ArrayList<Boolean>();
    input = new ArrayList<GateInput>();
    output = false;
    name = "(";
  }

  /**
   * method to get inputs
   * @return the input value
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
   * @return the integer value of level
   */
  public int getLevel() {
    return myLvl;
  }

  /**
   * void to set string
   * @param string the string which is to be set
   */
  public void setString(String string) {
    this.string = string;
  }

  /**
   * getter method to get string
   * @return the String string
   */
  public String getString() {
    return string;
  }

  /**
   * getter method to get Type
   * @return the String type
   */
  public String getType()
  {
    return "OR";
  }

  /**
   * void to set name
   * @param string the name to be set
   */
  public void setName(String string)
  {
    name = string;
  }

  /**
   * void to set name
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
        name = name + input.get(i).getName() + "+";
      }
    }   
  }

  /**
   * getter method to get the name
   * @return return the string of name
   */
  public String getName() {
    if (bracketsAfter(name, 0, ')') == (bracketsAfter(name, 0, '('))) {
      return name;
    }
    return name + ")";
  }

  /**
   * check number of brackets in a string after an index
   * @param string the string which will be checked
   * @param index the index from where it should be checked.
   * @param bracket the kind of the bracket
   * @return the number of occurences of the bracket
   */
  public int bracketsAfter(String string, int index, char bracket) {
    int counter = 0;
    for (int i = index; i < string.length(); i++) {
      if (string.charAt(i) == bracket) {
        counter++;
      }
    }
    return counter;
  }
  /**
   * void to input GateInput in the gate
   * @param input the GateInput to be added
   */
  public void input(GateInput... input)
  {
    if (input.length <= 1 )
    {
      System.out.println("Inputs Insufficient");
      return;
    }
    for (GateInput i: input)
    {
      this.input.add(i);
      if ( i.getOutput() == true)
      {
        output = true;
      }
    }
    if (getString().equals("")) {
      this.setName();
    }
  }

  /**
   * void to input Boolean Input in the gate
   * @param input the boolean value to be added
   */
  public void input(Boolean... input)
  {
    if (input.length <= 1 )
    {
      System.out.println("Inputs Insufficient");
      return;
    }
    for (Boolean i: input)
    {
      this.boolInput.add(i);
      if ( i == true)
      {
        output = true;
        return;
      }
    }
  }

  /**
   * method to get Input of the gate
   * @return the boolean of the input
   */
  public ArrayList<GateInput> getInput()
  {
    return input;
  }
  /**
   * method to get boolInput of the gate
   * @return the boolean of the input
   */
  
  public ArrayList<Boolean> getBoolInput()
  {
    return boolInput;
  }

  /**
   * getter method to get output
   * @return the boolean output of the gate
   */
  
  public boolean getOutput()
  {
    return output;
  }

  /**
   * string representation of the class
   * @return the string
   */
  public String toString()
  {
    return "OR Gatee: " + output;
  }
  
  
}