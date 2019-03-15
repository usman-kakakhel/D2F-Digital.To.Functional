/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

import java.util.ArrayList;

public class NOR extends BasicGates
{
    ArrayList<GateInput> input;
    ArrayList<Boolean> boolInput;
    boolean output;
    String name;
    int level;

    public NOR()
    {
        level = 0;
        boolInput = new ArrayList<Boolean>();
        input = new ArrayList<GateInput>();
        output = true;
        name = "(~(";
    }


    /**
     * method to get inputs
     * @return the number inputs
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
     * getter method to get type
     * @return the string of gate type
     */
    public String getType()
    {
        return "NOR";
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
     * getter method to get name
     * @return the string name
     */
    public String getName()
    {
        return name + "))";
    }

    /**
     * void to input
     * @param input the GateInput value
     */
    public void input(GateInput... input)
    {
        OR or = new OR();
        NOT not = new NOT();
        if (input.length <= 1 )
        {
            System.out.println("Inputs Insufficient");
            return;
        }
        for (GateInput i: input)
        {
            this.input.add(i);
        }
        or.input(input);
        not.input(or.getOutput());
        output = not.getOutput();
        this.setName();
    }

    /**
     * void to input Boolean Input
     * @param input the Boolean Input
     */
    public void input(Boolean... input)
    {
        OR or = new OR();
        NOT not = new NOT();
        if (input.length <= 1 )
        {
            System.out.println("Inputs Insufficient");
            return;
        }
        for (Boolean i: input)
        {
            this.boolInput.add(i);
        }
        or.input(input);
        not.input(or.getOutput());
        output = not.getOutput();
        this.setName();
    }


    /**
     * getter method to get bool inputs
     * @return the Boolean Array list of bool Input
     */
    public ArrayList<Boolean> getBoolInput()
    {
        return boolInput;
    }
    /**
     * getter method to get inputs
     * @return the Boolean Array list of Input
     */
    public ArrayList<GateInput> getInput()
    {
        return input;
    }

    /**
     * getter method to get output
     * @return the Boolean output
     */
    public boolean getOutput()
    {
        return output;
    }

    /**
     * string representation of the class
     * @return the string of the representation
     */
    public String toString()
    {
        return "NOR Gate: " + output;
    }
}