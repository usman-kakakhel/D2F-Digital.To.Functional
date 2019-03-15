/**
 * @author Talal Ahmad, Joel Graff, Muhammad Bilal, Mian Usman Naeem Kakakhel, Balaj Saleem
 * last edit: 10 May 2018
 */
package application;

import java.util.ArrayList;

public class XOR extends BasicGates
{
  
  ArrayList<GateInput> input;
  ArrayList<Boolean> boolInput;
  boolean output;
  String name;
  int level;
  
  public XOR()
  {
    level = 0;
    name = "(";
    boolInput = new ArrayList<Boolean>();
    input = new ArrayList<GateInput>();
    output = false;
  }


  public void setLevel(int level)
  {
    this.level = level;
  }

  public int getLevel()
  {
    return level;
  }

  public int inValue()
  {
    return input.size();
  }


  @Override
  public String getString() {
    return null;
  }

  public String getType()
  {
    return "XOR";
  }
  
   public void setName(String string)
  {
    name = string;
  }
  public String getName()
  {
    return name;
  }
  
  public void input(GateInput... input)
  {
    int trueCounter = 0;
    if (input.length <= 1 )
    {
      System.out.println("Inputs Insufficient");
      return;
    }
    for (GateInput i : input)
    {
      this.input.add(i);
      if ( i.getOutput() == true)
      {
        trueCounter++;
      }
    }
    if (trueCounter % 2 == 1)
    {
      output = true;
    }
  }
  
  
  public void input(Boolean... input)
  {
    int trueCounter = 0;
    if (input.length <= 1 )
    {
      System.out.println("Inputs Insufficient");
      return;
    }
    for (Boolean i : input)
    {
      this.boolInput.add(i);
      if ( i == true)
      {
        trueCounter++;
      }
    }
    if (trueCounter % 2 == 1)
    {
      output = true;
    }
  }
  
  public ArrayList<GateInput> getInput()
  {
    return input;
  }
  
  public ArrayList<Boolean> getBoolInput()
  {
    return boolInput;
  }
  
  public boolean getOutput()
  {
    return output;
  }
  
  public String toString()
  {
    return "XOR Gate: " + output;
  }
}