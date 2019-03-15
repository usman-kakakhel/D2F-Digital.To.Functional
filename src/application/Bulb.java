/**
 * @authors Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

public class Bulb extends BasicTools
{
    // Variable
    boolean state;
    String name;
    Wires connection;
    int level;

    public Bulb()
    {
        level = 0;
      connection = new Wires();
        state = false;
        name= "";
    }


    /**
     *void to set level of bulb
     * @param level the level to be set
     */
    public void setLevel(int level) {
        myLvl = level;
        this.level = level;
    }

    /**
     * getter method to get level of circuits
     * @return the level of circuit
     */
    public int getLevel() {
        return myLvl;
    }

    /**
     * getter method to get type of tool
     * @return the type of tool
     */
    public String getType()
    {
        return "Bulb";
    }

    /**
     * void to set name of a tool
     */
    public void setName()
    {
      name = "(" + connection.getName() + ")";
    }

    /**
     *  method to set a specific name of a tool
     * @param string the name given to the tool
     */
    public void setName(String string) {
        name = string;
    }

    /**
     * getter method to get name of a tool
     * @return the name of that tool
     */
    public String getName()
    {
      return name;
    }

    /**
     * void to connect wire
     * @param wire the wire it should be connected with
     */
    public void connect(Wires wire)
    {
      connection = wire;
      setName();
      setState(wire.getState());
    }

    /**
     * method to set state of the buld
     * @param condition the state which is to be set
     */
    public void setState(boolean condition)
    {
        state = condition;
    }

    /**
     * getter method to get state of bulb
     * @return the state of the bulb
     */
    public boolean getState()
    {
        return state;
    }


    /**
     * method that returns the string representations
     * @return the string representation
     */
    public String toString()
    {
        return "Bulb state: " + state;
    }

    /**
     * getter method to return output of the gate
     * @return return true if bulb id ob
     */
    public boolean getOutput()
    {
      return true;
    }
}
