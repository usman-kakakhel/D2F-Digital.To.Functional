/**
 * @author Muhammad Bilal
 * last edit: 10 May 2018
 */
package application;

public abstract class BasicTools extends GateInput
{

    /**
     * void to set state of the tool
     * @param condition the state which is to be set
     */
    public abstract void setState(boolean condition);

    /**
     * getter method to return state of tool
     * @return the state of tool
     */
    public abstract boolean getState();
}
