/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;
public class JKFlipFlop extends ComplexCircuitsTWO
{
  
  boolean j_Input;
  boolean k_Input;
  String name;
  Clock clock;
  FlipFlopOutput output;
  int level;
  
  public JKFlipFlop(boolean q, boolean notQ)
  {
    level = 0;
    j_Input = false;
    k_Input = false;
    name = "";
    clock = new Clock();
    output = new FlipFlopOutput(q, notQ);
  }

  @Override
  public void setLevel(int level) {
    this.level = level;
  }

  @Override
  public int getLevel()
  {
    return level;
  }

  public String getName()
  {
    return name;
  }

  public void setName (String name) {
    this.name = name;
  }

  public boolean getOut() {
    return output.getOut();
  }

  public boolean getCmp() {
    return output.getCmp();
  }

  public String getType()
  {
    return "JKFlipFlop";
  }

  public void setJ(boolean condition)
  {
    j_Input = condition;
  }
  
  public void setK(boolean condition)
  {
    k_Input = condition;
  }

  public void input(boolean j, boolean k)
  {
    j_Input = j;
    k_Input = k;
    NAND nand1_JCCM = new NAND();
    NAND nand2_KCO = new NAND();
    NAND nand3_1CM = new NAND();
    NAND nand4_2O = new NAND();
    nand1_JCCM.input(j_Input, clock.getState(), output.getOut());
    nand2_KCO.input(k_Input, clock.getState(), output.getCmp());
    nand3_1CM.input(nand1_JCCM.getOutput(), output.getCmp());
    nand4_2O.input(nand2_KCO.getOutput(), output.getOut());
    output.setOut(nand3_1CM.getOutput());
    output.setCmp(nand4_2O.getOutput());
  }

  public void input (GateInput... inputs) {
    GateInput values[] = new GateInput[100];
    int j = 0;
    boolean timer = false;
    for (GateInput i : inputs) {
      values[j] = i;
      j++;
    }

    j_Input = values[0].getOutput();
    k_Input = values[1].getOutput();
    timer = values[2].getOutput();

    if (getCmp() == getOut())
    {
      output.setCmp(!getOut());
    }

    if (timer == false)
    {
      output.setCmp(getCmp());
      output.setOut(getOut());
    }
    else
    {
      if (j_Input == false && k_Input == false)
      {
        output.setOut(getOut());
        output.setCmp(getCmp());
      }


      else if (j_Input == false && k_Input == true)
      {
        output.setCmp(true);
        output.setOut(false);
      }

      else if (j_Input == true && k_Input == false)
      {
        output.setOut(true);
        output.setCmp(false);
      }

      else if (j_Input == true && k_Input == true && getCmp() == true && getOut() == false)

      {

        output.setOut(true);
        output.setCmp(false);

      }

      else if (j_Input == true && k_Input == true && getOut() == true && getCmp() == false)
      {
        output.setCmp(true);
        output.setOut(false);
      }


    }



  }
  
  public FlipFlopInputTWO getInput()
  {
    FlipFlopInputTWO input = new FlipFlopInputTWO(j_Input, k_Input);
    return input;
  }
  
  public boolean getOutput()
  {
    return false;
  }

  public String toString()
  {
    return "JKFlipFlop";
  }
    
}