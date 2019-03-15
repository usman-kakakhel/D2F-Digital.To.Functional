package application;

public class Wires extends BasicTools
{
    // Variables
    boolean state;
    Components atEnd;
    Components atStart;
    String name;
    int level;


    public Wires()
    {
        level = 0;
        name = "";
        atEnd = null;
        atStart = null;
        state = false;
        setTypee("Wires");
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
    public String getType()
    {
        return "Wires";
    }
    public void setName()
    {
        GateInput input = (GateInput) atStart;
        name = input.getName();
    }

    public void setName(String string)
    {
        name = string;
    }

    public String getName()
    {
        return name;
    }

    public void setState(boolean condition)
    {
        state = condition;
    }

    public void setState()
    {
        GateInput start = (GateInput) atStart;
        state = start.getOutput();
    }

    public void connect(GateInput end, GateInput start)
    {
        atEnd = end;
        atStart = start;
        if ( (start.getClass() != FullAdder.class) && (start.getClass() != HalfAdder.class) && (start.getClass() != DFlipFlop.class) && (start.getClass() != TFlipFlop.class) && (start.getClass() != SRFlipFlop.class) && (start.getClass() != JKFlipFlop.class) ) {
            this.setState();
        }
        if (start.getLevel() >= end.getLevel())
        {
            end.setLevel(start.getLevel() + 1);
        }
        if (end.getClass() == Bulb.class)
        {
            ((Bulb) (end)).connect(this);
            ((Bulb) (end)).setName();
        }
        this.setName();
    }

    public GateInput getEnd()
    {
        return (GateInput) atEnd;
    }

    public GateInput getStart()
    {
        return (GateInput) atStart;
    }


    public boolean getOutput()
    {
        return state;
    }

    public boolean getState()
    {
        return state;
    }

    public String toString()
    {
        return "Wire state: " + state;
    }
}
