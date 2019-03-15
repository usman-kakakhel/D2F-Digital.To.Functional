/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;


public class HalfAdder extends GateInput
{
  AdderReturn output;
  HalfAdderInput input;
  String name;
  int level;
  

  public HalfAdder()
  {
    level = 0;
    input = new HalfAdderInput();
    name = "";
    output = new AdderReturn();
  }


  /**
   * void to set level
   * @param level which is to be set
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * getter method to get level
   * @return the integer value of level
   */
  public int getLevel() {
    return level;
  }

  /**
   * getter method to getOutput
   * @return the output value
   */
  public boolean getOutput() {
    return false;
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
   * @return the name of adder
   */
  public String getName() {
    return "HalfAdder";
  }

  /**
   * getter method to get type
   * @return the type
   */
  public String getType()
  {
    return "HalfAdder";
  }

  /**
   * void inputs into the gate
   * @param a the first input
   * @param b the second input
   */
  public void input( boolean a, boolean b)
  {
    input.setA(a);
    input.setB(b);
    XOR xor = new XOR();
    xor.input(a,b);
    output.setSum(xor.getOutput());
    AND and = new AND();
    and.input(a,b);
    output.setCarry(and.getOutput());
    
  }


  /**
   * void to input GateInput
   * @param inputs the GateInput to be added
   */
  public void input(GateInput... inputs)
  {
    GateInput values[] = new GateInput[100];
    int j = 0;
    for (GateInput  i : inputs) {
      values[j] = i;
      j++;
    }
    input.setA(values[0].getOutput());
    input.setB(values[1].getOutput());
    XOR xor = new XOR();
    xor.input(values[0].getOutput(), values[1].getOutput());
    output.setSum(xor.getOutput());
    AND and = new AND();
    and.input(values[0].getOutput(), values[1].getOutput());
    output.setCarry(and.getOutput());
  }

  /**
   * getter method to getSum
   * @return the sum value
   */
  public boolean getSum()
  {
    return output.getSum();
  }

  /**
   * getter method to get Carry
   * @return the value of Carry
   */
  public boolean getCarry() {

    return output.getCarry();
  }
//  public AdderReturn getOutput()
//  {
//    return output;
//  }

  /**
   * getter method to get input
   * @return the input
   */
  public HalfAdderInput getInput()
  {
    return input;
  }

  /**
   * string representation method
   * @return the empty string representation
   */
  public String toString()
  {
    return "";
  }
}