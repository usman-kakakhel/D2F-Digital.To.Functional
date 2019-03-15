/**
 * @author Muhammad Bilal
 * last edit: 10 May 2018
 */
package application;

public class Clock extends BasicTools
{
    //properties
    int count;
    boolean state;
    String name;
    int level;

    //constructor
    public Clock()
    {
        count = 0;
        state = false;
        level = 0;
        name = "";
    }

    public Clock(boolean state)
    {
        count = 0;
        this.state = state;
        level = 0;
        name = "";
    }

    /**
     * void to set level
     * @param level the level to be set
     */
    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * getter method to get level
     * @return the level
     */
    @Override
    public int getLevel() {
        return super.getLvl();
    }

    /**
     * method to set clock
     * @param repeat the integer for iterations
     */
    public void setClock(int repeat)
    {
        count = repeat;
    }

    /**
     * method to get type of Tool
     * @return the the type of tool
     */
    public String getType()
    {
        return "Clock";
    }

    /**
     * getter method to get Name
     * @return name of tool
     */
    public String getName()
    {
      return "Clock";
    }

    /**
     * void to set name of the tool
     * @param string the name which is to be set
     */
    public void setName(String string) {
        name = string;
    }

    /**
     * method to input the state
     * @param c the input state
     * @return the state
     */
    public boolean input(int c)
    {
        for(int i = c; i < count; i++)
        {
            input(i);
            return state;
        }
        return state;

    }

    /**
     * method to set state
     * @param state the state to be set
     */
    public void setState(boolean state)
    {
      this.state = state;
    }

    /**
     * getter method to get output
     * @return the output
     */
    public boolean getOutput()
    {
      return state;
    }

    /**
     * getter method to get state
     * @return the state
     */
    public boolean getState()
    {
      return state;
    }

    /**
     * method for string representation
     * @return the string representation
     */
    public String toString()
    {
      return "Clock";
    }
}