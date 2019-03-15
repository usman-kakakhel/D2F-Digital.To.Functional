/**
 * @author Daniyal Khalil, Mian Usman Naeem Kakakhel.
 * last edit: 10 May 2018
 */
package application;

import java.util.ArrayList;

public class Splitter extends BasicGates {

    boolean state;
    GateInput start;
    ArrayList<GateInput> inputs;
    ArrayList<Components> end;
    String name;
    String string;
    int level;




    public Splitter()
    {
        string = "";
        level = 0;
        inputs = new ArrayList<GateInput>();
        state = false;
        name = "";
    }

    /**
     * sets the string
     * @param string the string to be set
     */
    public void setString(String string )
    {
        this.string = string;
    }

    /**
     * getter method to get sting
     * @return the string
     */
    public String getString() {
        return string;
    }

    /**
     * sets the level
     * @param level which is to be set
     */
    public void setLevel(int level) {
        myLvl = level;
        this.level = level;
    }

    /**
     * to get number of inputs
     * @return size of inputs
     */
    public int inValue()
    {
        return inputs.size();
    }

    /**
     * getter method to get level
     * @return the int value of level
     */
    public int getLevel() {
        return myLvl;
    }

    /**
     * getter method of type
     * @return the type of gate
     */
    public String getType()
    {
        return "Splitter";
    }

    /**
     * getter method of input
     * @return array list of GateInputs
     */
    public ArrayList<GateInput> getInput(){
        inputs.add(start);
        return inputs;
    }

    /**
     * void to set name
     */
    public void setName() {
        name = start.getName();
    }

    /**
     * void to set a specific name
     * @param name mane to be set
     */
    public void setName (String name)
    {
        this.name = name;
    }

    /**
     * void to input GateInput
     * @param start the input
     */
    public void input(GateInput... start) {

    }

    /**
     * input to splitter
     * @param start the GateInput to the splitter
     */

    public void input(GateInput start) {
        this.start = start;
        if ( start.getClass() == Wires.class)
        {
            this.setState(((Wires) (start)).getState());
        }
        setName();
    }

    /**
     * void to connect end
     * @param end the components
     */
    public void connectEnd(Components end) {
        this.end.add(end);
    }

    /**
     * getter method to get states
     * @return the state of splitter
     */
    public boolean getState() {

        return state;
    }

    /**
     * void to set state of splitter
     * @param condition the condition given to the state
     */
    public void setState(boolean condition) {
        state = condition;
    }


    /**
     * getter method to get name
     * @return the name of component
     */
    public String getName() {
        return name;
    }

    /**
     * getter method to get output
     * @return the state
     */
    public boolean getOutput() {
        return state;
    }

    /**
     * string representation of splitter
     * @return the string representing string
     */
    public String toString() {
        return "Splitter";
    }








}
