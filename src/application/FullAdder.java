/**
 * @author Talal Ahmad, Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

public class FullAdder extends GateInput
{
    AdderReturn output;
    FullAdderInput input;
    String name;
    boolean state;
    int level;

    public FullAdder()
    {
        level = 0;
        state = false;
        output = new AdderReturn();
        input = new FullAdderInput();
        name = "";
    }


    /**
     * void to set level
     * @param level level which is to be set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * getter method to get value of level
     * @return the value of level
     */
    public int getLevel() {
        return level;
    }

    /**
     * getter method to get Type
     * @return the type
     */
    public String getType()
    {
        return "FullAdder";
    }
    /**
     * getter method to get name
     * @return the string name
     */
    public String getName() {
        return "";
    }

    /**
     * void to set name
     * @param name name which is to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * method to get output
     * @return the output value
     */
    public boolean getOutput()
    {
        return state;
    }

    /**
     * void to add input to a full adder
     * @param a the boolean value of first input
     * @param b the boolean value of second input
     * @param carryIn the boolean value of carryIn
     */
    public void input(boolean a, boolean b, boolean carryIn) {
        input.setA(a);
        input.setB(b);
        input.setCarryIn(carryIn);

        HalfAdder first = new HalfAdder();
        HalfAdder second = new HalfAdder();
        OR or = new OR();

        first.input(a, b);
        second.input((first.getSum()), carryIn);
        output.setSum(second.getSum());
        or.input(first.getCarry(), second.getCarry());
        output.setCarry(or.getOutput());
    }

    /**
     * void for inputs of GateInputs
     * @param inputs the GateInputs will be added
     */
    public void input (GateInput... inputs) {
        int j = 0;
        GateInput values[] = new GateInput[100];
        for ( GateInput i : inputs) {
            values[j] = i;
            j++;
        }
        input.setA(values[0].getOutput());
        input.setB(values[2].getOutput());
        input.setCarryIn(values[1].getOutput());

        HalfAdder first = new HalfAdder();
        HalfAdder second = new HalfAdder();
        OR or = new OR();

        first.input(values[0],values[2]);
        second.input(first.getSum(), values[1].getOutput());
        output.setSum(second.getSum());
        or.input(first.getCarry(), second.getCarry());
        output.setCarry(or.getOutput());
    }

    /**
     * getter method to return sum
     * @return value of sum
     */
    public boolean getSum() {
        return output.getSum();
    }

    /**
     * getter method to return carry
     * @return value of carry
     */
    public boolean getCarry() {
        return output.getCarry();
    }

    /**
     * getter method to return the Adder Output
     * @return the out value
     */
    public AdderReturn getAdderOutput()
    {
        return output;
    }

    /**
     * getter method to return the Input
     * @return the out value
     */
    public FullAdderInput getInput()
    {
        return input;
    }

    /**
     * method for string representation
     * @return the string representation
     */
    public String toString()
    {
        return "";
    }
}
