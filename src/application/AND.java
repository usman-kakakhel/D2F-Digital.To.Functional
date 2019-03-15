/**
 * @authors Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;
import java.util.ArrayList;

public class AND extends BasicGates
{

    //properties
    ArrayList<GateInput> input;
    ArrayList<Boolean> boolInput;
    boolean output;
    String name;
    String string;
    int level;

    //constructor
    public AND()
    {
        level = 0;
        string = "";
        name = "(";
        input = new ArrayList<GateInput>();
        boolInput = new ArrayList<Boolean>();
        output = false;
    }

    /**
     * void to set the string property of AND
     * @param string the string which should be set
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * method to return inputs of AND gates
     * @return input size of AND
     */
    public int inValue()
    {
        return input.size();
    }

    /**
     * method to set level
     * @param level the level to be set
     */
    public void setLevel(int level) {
        myLvl = level;
        this.level = level;
    }

    /**
     * getter method to return level of circuit
     * @return the level of circuits
     */
    public int getLevel() {
        return myLvl;
    }

    /**
     * getter method to get string of AND
     * @return string of AND
     */
    public String getString() {
        return string;
    }

    /**
     * getter method to get type of Gate
     * @return the type of Gate(AND)
     */
    public String getType()
    {
        return "AND";
    }

    /**
     * method to set name of gate
     * @param string the name of gate
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
                name = name + input.get(i).getName() + ".";
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
     * void to set boolean inputs of gate
     * @param input the input value of gate
     */
    public void input(Boolean... input)
    {
        output = true;
        if (input.length <= 1 )
        {
            System.out.println("Inputs Insufficient");
            return;
        }
        for (Boolean i: input)
        {
            this.boolInput.add(i);
            if (i == false)
            {
                output = false;

            }
        }
        return;
    }

    /**
     * void to set GateInput of the gate
     * @param input the gate input to be set
     */
    public void input(GateInput... input)
    {
        output = true;
        if (input.length <= 1 )
        {
            System.out.println("Inputs Insufficient");
            return;
        }
        for (GateInput i: input)
        {
            this.input.add(i);
            if (i.getOutput() == false)
            {
                output = false;
            }
        }
        if (getString().equals("")) {
            this.setName();
        }
        return;
    }


    /**
     * getter method to return boolean inputs
     * @return the boolean inputs
     */
    public ArrayList<Boolean> getBoolInput()
    {
        return boolInput;
    }

    /**
     * getter method to return output
     * @return output of gate
     */
    public boolean getOutput()
    {
        return output;
    }

    /**
     * getter method to return input of gate
     * @return input of gate
     */
    public ArrayList<GateInput> getInput()
    {
        return input;
    }

    /**
     * method to convert toString
     * @return string value Gate
     */
    public String toString()
    {
        return "AND Gate: " + output;
    }

    /**
     * method to get name
     * @return the name of gate
     */
    public String name()
    {
        return name;
    }

}