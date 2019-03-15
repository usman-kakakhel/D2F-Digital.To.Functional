/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */
package application;

public abstract class Components {


    String id = null;

    RightPaneChildren myGUI = null;

    int myLvl;

   String typee = "";


    /**
     * getter method to get Type
     * @return the type
     */
    public String getTypee() {
        return typee;
    }


    /**
     * method to setType
     * @param typee the type to be set
     */
    public void setTypee(String typee) {
        this.typee = typee;
    }

    /**
     * getter method to getId
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * abstract method to get type
     * @return type
     */
    public abstract String getType();

    /**
     * method to setId
     * @param id the id to be set
     */
    public void setId(String id) {
        this.id = id;
    }

    Circuits itsCircuit = null;

    /**
     * getter method to get circuits
     * @return the circuit
     */
    public Circuits getCircuit() {
        return itsCircuit;
    }

    /**
     * method to circuit
     * @param itsCircuit the circuit to be set
     */
    public void setCircuit(Circuits itsCircuit) {
        this.itsCircuit = itsCircuit;
    }

    /**
     * method to getGUI
     * @return myGUI
     */
    public RightPaneChildren getGUI() {
        return myGUI;
    }

    /**
     * method to set GUI
     * @param myGUI the GUI to be set
     */
    public void setGUI(RightPaneChildren myGUI) {
        this.myGUI = myGUI;
    }

    /**
     * getter method to get level
     * @return the level
     */
    public int getLvl() {
        return myLvl;
    }

    /**
     * method to set level
     * @param myLvl the level to be set
     */
    public void setLvl(int myLvl) {
        this.myLvl= myLvl;
    }


    /**
     * abstract methods for string representation
     * @return the string representation
     */
    public abstract String toString();

}