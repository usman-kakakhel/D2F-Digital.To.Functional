/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

public class Switch extends BasicTools
{
    // Variables
    public boolean state;
    int clock;
    String name;
    String typee = "Switch";
    int level;

    public Switch(boolean state)
    {
        level = 0;
       this.state = state;
      name = "q";
      setTypee("Switch");
    }

    public Switch()
    {
        level = 0;
        state = false;
        name = "";
        setTypee("Switch");
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
     * void to get level
     * @return the level
     */
    public int getLevel() {
        return myLvl;
    }


    /**
     * getter method to get type
     * @return the type
     */
    public String getType()
    {
        return "Switch";
    }
    /**
     * getter method to get name
     * @return the name
     */
    public String getName()
    {
      return name;
    }

    /**
     * void to set name
     * @param name the name which is to be set
     */
    public void setName(String name)
    {
      this.name = name;
    }

    /**
     * void to set state
     * @param condition the state which is to be set
     */
    public void setState(boolean condition)
    {
        state = condition;
    }

    /**
     * getter method to get output
     * @return the outpt
     */
    public boolean getOutput()
    {
      return state;
    }

    /**
     * getter method to get state of splitter
     * @return the name
     */
    public boolean getState()
    {
        return state;
    }

    /**
     * void for timer integration
     * @param time the input time for timer
     */
    private void timer(int time)
    {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * void to get timed state
     */
    public void timedState()
    {
        timer(1);
        if (state == false)
        {
            state = true;
        }
        else
            state = false;
    }

    /**
     * string representation of Switch
     * @return the string representation
     */
    public String toString()
    {
        return "Switch state: " + state;
    }
}
