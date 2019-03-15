/**
 * @author Daniyal Khalil, Talal Ahmad
 * last edit: 10 May 2018
 */
package application;

import java.util.ArrayList;

public class NOT extends BasicGates {

    boolean boolInput;
    GateInput input;
    boolean output;
    String name;
    String string;
    int level;

    public NOT() {
        string = "";
        boolInput = false;
        output = true;
        name = "~(";
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
     * method to get inputs
     * @return the input value
     */
    public int inValue() {
        return 1;
    }

    /**
     * getter method to get level
     * @return the integer value of level
     */
    public int getLevel() {
        return myLvl;
    }

    /**
     * getter method to get string
     * @return the String string
     */
    public String getString() {
        return string;
    }

    /**
     * void to set string
     * @param string the string which is to be set
     */
    public void setString(String string) {
        this.string = string;
    }


    /**
     * getter method to get Type
     * @return the String type
     */
    public String getType() {
        return "NOT";
    }

    /**
     * void to set name
     * @param string the name to be set
     */
    public void setName(String string) {
        name = string;
    }

    /**
     * void to input GateInput
     * @param input the GateInputs which will be inputted into the gate
     */
    @Override
    public void input(GateInput... input) {
        //usman changed....change back if required
        for (GateInput a : input)
            input(a);

    }

    /**
     * void to set name
     */
    public void setName() {
        name = name + input.getName();
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
    public void input(GateInput input) {
        this.input = input;
        if (input.getOutput() == false) {
            output = true;
        } else {
            output = false;
        }
    }

    /**
     * void to input Boolean Input in the gate
     * @param input the boolean value to be added
     */
    public void input(boolean input) {
        this.boolInput = input;
        if (input == false) {
            output = true;
        } else {
            output = false;
        }
    }


    @Override
    public ArrayList<GateInput> getInput() {
        ArrayList<GateInput> gateInput = new ArrayList<GateInput>();
        gateInput.add(input);
        return gateInput;
    }

    /**
     * method to get boolInput of the gate
     * @return the boolean of the input
     */
    public boolean getBoolInput() {
        return boolInput;
    }

    /**
     * method to get output
     * @return the boolean output of the gate
     */
    public boolean getOutput() {
        return output;
    }

    /**
     * string representation of the class
     * @return the string
     */
    public String toString() {
        return "NOT Gate: " + output;
    }
}