/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

public class DFlipFlop extends ComplexCircuitsONE
{
  //properties
  boolean d_Input;
  String name;
  FlipFlopOutput output;
  Clock clock;
  int level;

  //constructor
  public DFlipFlop(boolean q, boolean notQ)
  {
    level = 0;
    clock = new Clock();
    d_Input = false;
    output = new FlipFlopOutput(q, notQ);
  }


  /**
   * void to set level
   * @param level the level to be set
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * method to get level
   * @return the level
   */
  public int getLevel() {
    return level;
  }

  /**
   * method to get name
   * @return the name
   */
  public String getName()
  {
    return name;
  }
  /**
   * method to get type
   * @return the type
   */
    public String getType()
  {
    return "DFlipFlop";
  }

  /**
   * void to input in flip flop
   * @param d the input
   */
  public void input(GateInput... inputs) {
    GateInput values[] = new GateInput[100];
    int j = 0;
    for (GateInput i : inputs) {
      values[j] = i;
      j++;
    }
    d_Input = values[0].getOutput();
    boolean timer = values[1].getOutput();

    if (timer == false) {
      if (d_Input == true && getOut() == true && getCmp() == false) {
        output.setOut(true);
        output.setCmp(false);
      }

      if (d_Input == false && getCmp() == false && getOut() == true) {
        output.setCmp(false);
        output.setOut(true);
      }
      if (d_Input == true && getCmp() == false && getOut() == true) {
        output.setOut(true);
        output.setCmp(false);
      }

      if (d_Input == false && getOut() == false && getCmp() == true) {
        output.setCmp(true);
        output.setOut(false);
      }

    }
    else
    {
      if (d_Input == false) {
        output.setCmp(true);
        output.setOut(false);
      } else if (d_Input == true) {
        output.setOut(true);
        output.setCmp(false);
      }
    }
    if (getCmp() == getOut()) {
      output.setCmp(!getOut());
    }

  }

  /**
   * method to set name
   * @param name name to be set
   */
  public void setName(String name) {
    this.name = name;
  }


  /**
   * method to input the first input in flip flop
   * @return the input
   */
  public FlipFlopInputONE getInput()
  {
    FlipFlopInputONE input = new FlipFlopInputONE(d_Input);
    return input;
  }

  /**
   * getter method to get Out
   * @return the out
   */
  public boolean getOut() {
    return output.getOut();
  }

  /**
   * getter method to get comparison
   * @return the comparison
   */
  public boolean getCmp() {
    return output.getCmp();
  }

  /**
   * getter method to get Output
   * @return the output
   */
  public boolean getOutput()
  {
    return false;
  }

    /**
     * string representation
     * @return the string representation
     */
  public String toString()
  {
    return "NAND";
  }
  
}