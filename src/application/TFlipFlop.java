/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;


public class TFlipFlop extends ComplexCircuitsONE
{

  boolean t_Input;
  FlipFlopOutput output;
  String name;
  Clock clock;
  int level;

  public TFlipFlop(boolean q, boolean notQ)
  {
    level = 0;
    t_Input = false;
    output = new FlipFlopOutput(q, notQ);
    clock = new Clock();
    name = "";
  }

  /**
   * void to set level
   * @param level which is to be set
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * void to get level
   * @return the level
   */
  public int getLevel() {
    return level;
  }


  /**
   * void to set name
   * @param name the name which is to be set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * getter method to get name
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * getter method to get type
   * @return the type
   */
  public String getType()
  {
    return "TFlipFlop";
  }

  /**
   * void to input boolean in T FLip Flop
   * @param t the boolean to be input
   */
  public void input(boolean t)
  {
    t_Input = t;
    NAND nand1_tCmp = new NAND();
    NAND nand2_tOut =  new NAND();
    NAND nand3_1Cmp = new NAND();
    NAND nand4_2Out = new NAND();

    nand1_tCmp.input(t, clock.getState(), output.getCmp());
    nand2_tOut.input(t, clock.getState(), output.getOut());
    nand3_1Cmp.input(nand1_tCmp.getOutput(), output.getCmp());
    nand4_2Out.input(nand2_tOut.getOutput(), output.getOut());
    output.setOut(nand3_1Cmp.getOutput());
    output.setCmp(nand4_2Out.getOutput());

  }

  /**
   * GateInput to T Flip FLop
   * @param inputs the GateInputs
   */

  public void input(GateInput... inputs) {
    int j = 0;
    GateInput values[] = new GateInput[100];
    for ( GateInput i : inputs) {
      values[j] = i;
      j++;
    }

    t_Input = values[0].getOutput();
    boolean timer = values[1].getOutput();
    if (getCmp() == getOut())
    {
      output.setCmp(!getOut());
    }
    if (timer == false)
    {
      output.setOut(getOut());
      output.setCmp(getCmp());
    }
    else
    {
      if (t_Input == false && getCmp() == true && getOut() == false)
      {
        output.setCmp(true);
        output.setOut(false);
      }

      else if (t_Input == false && getCmp() ==  false && getOut() == true)
      {
        output.setOut(true);
        output.setCmp(false);
      }

      else if (t_Input == true && getOut() == false && getCmp() == true)
      {
        output.setOut(true);
        output.setCmp(false);
      }
      else if (t_Input == true && getCmp() == false && getOut() == true)
      {
        output.setCmp(true);
        output.setOut(false);
      }


    }



  }

    /**
     * getter method to get Input
     * @return the input of T flip flop
     */
  public FlipFlopInputONE getInput()
  {
    FlipFlopInputONE input = new FlipFlopInputONE(t_Input);
    return input;
  }

    /**
     * getter method to get output
     * @return the output of T Flip Flop
     */
  public boolean getOutput() {
    return false;
  }

    /**
     * getter method to get Out
     * @return the out
     */
  public boolean getOut() {
    return output.getOut();
  }

    /**
     * getter method to get cmp
     * @return return cmp
     */
  public boolean getCmp() {
    return  output.getCmp();
  }

    /**
     * string representation of Switch
     * @return the string representation
     */
  public String toString()
  {
    return "T FlipFlop";
  }
}