/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;


public class SRFlipFlop extends ComplexCircuitsTWO
{
  
  boolean s_Input;
  boolean r_Input;
  String name;
  FlipFlopOutput output;
  int level;
  
  public SRFlipFlop(boolean q, boolean notQ)
  {
    level = 0;
    name = "";
    s_Input = false;
    r_Input = false;
    output = new FlipFlopOutput(q, notQ);
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
   * getter method to get name
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * boid to set name
   * @param name the name which is to be set
   */
  public void setName (String name ) {
    this.name = name;
  }

  /**
   * getter method to get out
   * @return the out
   */
  public boolean getOut() {
    return output.getOut();
  }

  /**
   * getter method to get compare
   * @return the cmp
   */
  public boolean getCmp() {
    return output.getCmp();
  }

  /**
   * get input for SR
   * @param r r input
   * @param s s input
   */
  public void input(boolean r, boolean s)
  {
    s_Input = s;
    r_Input = r;
    NAND nand1_SCm = new NAND();
    NAND nand2_RO = new NAND();
    nand1_SCm.input(s_Input, output.getCmp());
    nand2_RO.input(r_Input, output.getOut());
    output.setOut(nand1_SCm.getOutput());
    output.setCmp(nand2_RO.getOutput());
  }

  /**
   * Gate input of SR
   * @param inputs the inputs
   */
  public void input(GateInput... inputs) {
    int j = 0;
    GateInput values[] = new GateInput[100];
    for (GateInput i : inputs) {
      values[j] = i;
      j++;
    }
    s_Input = values[0].getOutput();
    r_Input = values[1].getOutput();


    if (s_Input == true && r_Input == false && getOut() == true)
    {
      output.setCmp(false);
      output.setOut(true);
    }
    if (s_Input == true && r_Input == false && getCmp() == false)
    {
      output.setCmp(false);
      output.setOut(true);
    }
    if (s_Input == true && r_Input == false && getOut() == true)
    {
      output.setCmp(false);
      output.setOut(true);
    }

    if (s_Input == true && r_Input == false && getCmp() == false)
    {
      output.setCmp(false);
      output.setOut(true);
    }

    if (s_Input == false && r_Input == true && getOut() == false)
    {
      output.setCmp(true);
      output.setOut(false);
    }

    if (s_Input == false && r_Input == true && getCmp() == true)
    {
      output.setCmp(true);
      output.setOut(false);
    }

    if (s_Input == false && r_Input == false && getOut() == false)
    {
      output.setCmp(true);
      output.setOut(false);
    }
    if (s_Input == false && r_Input == false && getCmp() == true)
    {
      output.setCmp(true);
      output.setOut(false);
    }

    if (s_Input == true && r_Input == true)
    {
      output.setOut(false);
      output.setCmp(false);
    }



  }

  /**
   * getter method to get Type
   * @return the string type
   */
  public String getType()
  {
    return "SRFlipFlop";
  }

  /**
   * getter method to get output
   * @return the output of SR
   */
  public boolean getOutput()
  {
    return false;
  }

  /**
   * FlipFlop inputs
   * @return the input
   */
  public FlipFlopInputTWO getInput()
  {
    FlipFlopInputTWO input = new FlipFlopInputTWO(s_Input, r_Input);
    return input;
  }

  /**
   * string representation of SR
   * @return the string representation
   */
  public String toString()
  {
    return "SR FlipFlop";
  }
  
  
}