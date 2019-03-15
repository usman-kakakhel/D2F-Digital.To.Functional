package application;

import java.util.ArrayList;

/**
 * @author Daniyal Khalil
 * last edit: 10 May 2018
 */

public class CircuitMaker {

    //Properties
    ArrayList<Wires> wires;
    ArrayList<BasicGates> gates;
    ArrayList<Switch> switches;
    Bulb bulb;
    ArrayList<Splitter> splitters;
    ArrayList<String> switchesString;
    ArrayList<String> wiresString;
    Circuits circuit;

    //Constructor
    public CircuitMaker() {
        circuit = new Circuits();
        splitters = new ArrayList<Splitter>();
        bulb = new Bulb();
        gates = new ArrayList<BasicGates>();
        wires = new ArrayList<>();
        switches = new ArrayList<Switch>();
        switchesString = new ArrayList<String>();
        wiresString = new ArrayList<String>();
    }

    /**
     * Makes the circuit in the backend
     *
     * @param string
     */
    public void makeCircuit(String string) {
        connect(string);
    }

    /**
     * Produces and connects the backend circuit
     *
     * @param string
     */
    public void connect(String string) {
        createSwitches(string);
        createGates(string);
        gatesName(string);
        createSplitters();
        splitterSwitchesConnect();
        gateSplittersConnect();
        splitterGatesConnect();
        gateSwitchesConnect();

        gateGatesConnect();
        setGateNames();
        setSplitterNames();
        setWireNames();

        bulbConnect();
        bulb.setName();
        gateInputs();
        setLevels();
        addToCircuit();

    }

    /**
     * Gets The Circuit Made at backend
     *
     * @return Circuits
     */
    public Circuits getCircuit() {
        return circuit;
    }

    /**
     * To Find Input Expressions in a given string
     *
     * @param eq String
     * @return ArrayList<String></String>
     */

    public ArrayList<String> findInputsInExpression(String eq) {
        ArrayList<String> myLetters = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();
        String in = "";
        boolean check = true;

        for (int i = 0; i < eq.length(); i++) {
            if ((eq.charAt(i) >= 'A') && (eq.charAt(i) <= 'Z')) {
                in = eq.charAt(i) + "";

                if ((myLetters.indexOf(in) == -1) && (in.length() == 1)) {
                    myLetters.add(in);
                }
            }
        }
        return myLetters;
    }

    /**
     * To Set The Levels of the gates in accordance with their connections
     */

    public void setLevels() {
        boolean check = true;
        do {
            check = true;
            for (Wires i : wires) {
                if (i.getStart().getLevel() >= i.getEnd().getLevel()) {
                    i.getEnd().setLevel(i.getStart().getLevel() + 1);
                    check = false;
                }
            }
        } while (check == false);

    }

    /**
     * Adds the components made to the circuit
     */
    public void addToCircuit() {
        for (int i = 0; i < switches.size(); i++) {
            circuit.add(switches.get(i));
        }
        for (int i = 0; i < gates.size(); i++) {
            circuit.add(gates.get(i));
        }
        for (int i = 0; i < splitters.size(); i++) {
            circuit.add(splitters.get(i));
        }
        for (int i = 0; i < wires.size(); i++) {
            circuit.add(wires.get(i));
        }
        bulb.setName();
        circuit.add(bulb);
    }

    /**
     * Finds the number of instances of the brackets after the index provided in the String
     *
     * @param string  String
     * @param index   int
     * @param bracket char
     * @return int
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
     * Gives the names to gates in accordance to their dependencies on other gates
     *
     * @param string String
     */
    public void gatesName(String string) {
        int gateCount = 0;
        int stringCount = 0;
        while (numOfBrackets(string) > 1 && gateCount < gates.size()) {
            if (numOfBrackets(string) / 2 == 1) {
                String replace = string;
                setGate(replace, gateCount);
                string = "gate" + gateCount;
                gateCount++;
            } else if (findBracketsWithin(string, stringCount) == 0) {
                int close = findCloseBracket(string, stringCount);
                String replace = string.substring(stringCount, close + 1);
                setGate(replace, gateCount);
                string = string.replace(replace, "gate" + gateCount);
                stringCount = 0;
                gateCount++;
            }
            stringCount++;
        }
        for (int i = 0; i < gates.size(); ) {
            if (gates.get(i).getName().equals("()")) {
                gates.remove(i);
            } else {
                i++;
            }
        }
    }

    /**
     * sets the Gates to their generic types
     *
     * @param string    String
     * @param gateCount gateCount
     */
    public void setGate(String string, int gateCount) {
        if (string.contains("+")) {
            OR or = new OR();
            or.setName(string);
            or.setString("" + gateCount);
            gates.set(gateCount, or);
        }

        if ((string.contains("."))) {
            AND and = new AND();
            and.setName(string);
            and.setString("" + gateCount);
            gates.set(gateCount, and);
        }

        if ((string.charAt(1) == '~') || (string.charAt(0) == '~')) {
            NOT not = new NOT();
            not.setName(string);
            not.setString("" + gateCount);
            gates.set(gateCount, not);
        }
    }

    /**
     * sets the Gate names to their boolean algebraic expressions
     */
    public void setGateNames() {
        for (int i = 0; i < gates.size(); i++) {
            for (int j = 0; j < gates.size(); j++) {
                if (gates.get(j).getName().contains("gate" + gates.get(i).getString())) {
                    String name = (gates.get(j).getName());
                    String replace = name.replace("gate" + gates.get(i).getString(), gates.get(i).getName());
                    gates.get(j).setName(replace);
                }
            }
        }
    }

    /**
     * sets the Splitter Names to their boolean algebraic expressions
     */
    public void setSplitterNames() {
        for (int i = 0; i < splitters.size(); i++) {
            for (int j = 0; j < gates.size(); j++) {

                if (splitters.get(i).getName().contains("gate" + gates.get(j).getString())) {
                    splitters.get(i).setName(gates.get(j).getName());
                }
            }
        }
    }

    /**
     * inputs the gates with the wires connected to them
     */
    public void gateInputs() {
        for (int i = 0; i < gates.size(); i++) {
            if (gates.get(i).getClass() == NOT.class) {
                i++;
            } else {
                ArrayList<GateInput> inputs = new ArrayList<>();
                for (int j = 0; j < wires.size(); j++) {
                    if (wires.get(j).getEnd() == gates.get(i)) {
                        inputs.add(wires.get(j));
                    }
                }
                gates.get(i).input(inputs.toArray(new Wires[inputs.size()]));

            }
        }
    }

    /**
     * sets the Wire names to their boolean algebraic expressions
     */
    public void setWireNames () {
        for (int i = 0; i < wires.size(); i++) {
            wires.get(i).setName();
        }
    }

    /**
     * sets the name of the bulb to the name of the Last gate
     */
    public void setBulb () {
        int last = gates.size() - 1;
        bulb.setName(gates.get(last).getName());
    }

    /**
     * Returns the total number of brackets in a string
     * @param string String
     * @return int
     */
    public int numOfBrackets (String string){
        int counter = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                counter++;
            }
        }
        return counter * 2;
    }

    /**
     * creates the initial gates array
     * @param string String
     */
    public void createGates (String string){
        AND gate;
        int number = numOfBrackets(string) / 2;
        for (int i = 0; i < number; i++) {
            gate = new AND();
            gates.add(gate);
        }
    }

    /**
     * returns the complimentary index of the close bracket of an open bracket
     * @param string String
     * @param openIndex int
     * @return int
     */
    public int findCloseBracket (String string,int openIndex){
        int counterOpen = 1;
        int counterClose = 0;
        int position = openIndex;
        while ((counterOpen != counterClose)) {
            position++;
            if (string.charAt(position) == '(') {
                counterOpen++;
            } else if (string.charAt(position) == ')') {
                counterClose++;
            }
        }
        return position;
    }

    /**
     * returns the number of  brackets inside a given bracket
     * @param string String
     * @param openIndex int
     * @return int
     */
    public int findBracketsWithin (String string,int openIndex){
        int counter = 0;
        int closeIndex = findCloseBracket(string, openIndex);
        for (int i = openIndex + 1; i < closeIndex; i++) {
            if (string.charAt(i) == '(') {
                counter++;
            } else if (string.charAt(i) == ')') {
                counter++;
            }
        }
        return counter;
    }

    /**
     * creates the switches for the circuit
     * @param string String
     */

    public void createSwitches (String string){
        switchesString = findInputsInExpression(string);
        for (int i = 0; i < switchesString.size(); i++) {
            Switch newSwitch = new Switch();
            switches.add(newSwitch);
            switches.get(i).setName(switchesString.get(i));
        }
    }

    /**
     * creates the splitters for the circuit
     */
    public void createSplitters () {
        //For Splitters used in switches
        for (int i = 0; i < switches.size(); i++) {
            int counter = 0;
            for (int j = 0; j < gates.size(); j++) {
                if ((gates.get(j).getName()).contains(switches.get(i).getName())) {
                    counter++;
                    for (int m = 0; m < gates.get(j).getName().length(); m++) {
                        if (m == 0) {
                            counter--;
                        }
                        if (("" + gates.get(j).getName().charAt(m)).equals(switches.get(i).getName())) {
                            counter++;

                        }
                    }
                }

            }
            if (counter > 1) {
                Splitter splitter = new Splitter();
                splitter.setName(switches.get(i).getName());
                splitters.add(splitter);
            }
        }

        //For splitters used after gates
        for (int i = 0; i < gates.size(); i++) {
            int counter = 0;
            for (int j = 0; j < gates.size(); j++) {
                if ((gates.get(j).getName()).contains("gate" + gates.get(i).getString())) {
                    counter++;
                    int space = 0;
                    if (gates.get(i).getString().length() > 2) {
                        space = 6;
                    } else {
                        space = 5;
                    }
                    for (int m = 0; m < gates.get(j).getName().length() - space; m++) {
                        if (m == 0) {
                            counter--;
                        }
                        if (((gates.get(j).getName()).substring(m, m + space).equals("gate" + gates.get(i).getString()))) {
                            counter++;
                        }
                    }
                }
            }
            if (counter > 1) {
                Splitter splitter = new Splitter();
                splitter.setName("gate" + gates.get(i).getString());
                splitters.add(splitter);
            }
        }

    }

    /**
     * connects the switches with splitters through wires
     */
    public void splitterSwitchesConnect () {
        for (int i = 0; i < switches.size(); i++) {
            for (int j = 0; j < splitters.size(); j++) {
                Wires wire = new Wires();
                if (splitters.get(j).getName().equals(switches.get(i).getName())) {
                    wire.connect(splitters.get(j), switches.get(i));
                    wire.getName();

                    wires.add(wire);
                }
            }
        }
    }

    /**
     * connects the gates with splitters through wires
     */
    public void splitterGatesConnect () {
        for (int i = 0; i < gates.size(); i++) {
            for (int j = 0; j < splitters.size(); j++) {
                Wires wire = new Wires();
                if (splitters.get(j).getName().equals("gate" + gates.get(i).getString()) || (splitters.get(j).getName().equals(gates.get(i).getName()))) {
                    wire.connect(splitters.get(j), gates.get(i));
                    wire.setName("gate" + gates.get(i).getString());
                    wires.add(wire);
                }
            }
        }
    }

    /**
     * connects the splitters to gates through wires
     */
    public void gateSplittersConnect () {
        for (int i = 0; i < splitters.size(); i++) {
            for (int j = 0; j < gates.size(); j++) {
                Wires wire = new Wires();
                int counter = 0;
                if (gates.get(j).getName().contains(splitters.get(i).getName())) {
                    wire.connect(gates.get(j), splitters.get(i));
                    int space = 0;
                    counter++;
                    if (splitters.get(i).getName().length() == 1) {
                        space = 1;
                    } else if (gates.get(i).getString().length() > 2) {
                        space = 6;
                    } else {
                        space = 5;
                    }
                    for (int m = 0; m < gates.get(j).getName().length() - space; m++) {
                        if (m == 0) {
                            counter--;
                        } else if (((gates.get(j).getName()).substring(m, m + space).equals(splitters.get(i).getName()))) {

                            counter++;
                        }
                    }
                    for (int l = 0; l < counter; l++) {

                        wires.add(wire);
                    }
                }
            }
        }
    }


    /**
     * connects the switches to the gates
     */
    public void gateSwitchesConnect () {
        for (int i = 0; i < switches.size(); i++) {
            for (int j = 0; j < gates.size(); j++) {
                Wires wire = new Wires();
                if (gates.get(j).getName().contains(switches.get(i).getName())) {
                    Splitter splitter = new Splitter();
                    boolean check = false;
                    for (int m = 0; m < splitters.size(); m++) {

                        if (splitters.get(m).getName().equals(switches.get(i).getName())) {
                            check = true;
                            splitter = splitters.get(m);
                        }
                    }
                    if (!check) {
                        wire.connect(gates.get(j), switches.get(i));
                        wires.add(wire);
                    }
                }

            }
        }
    }

    /**
     * check for whether a gate is connected to a gateInput or not
     * @param gate BasicGates
     * @param input GateInput
     * @return
     */
    public boolean isGateConnected (BasicGates gate, GateInput input){
        ArrayList<GateInput> connections = gate.getInput();
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).equals(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * connects the gates to gates through wires
     */

    public void gateGatesConnect () {
        for (int i = 0; i < gates.size(); i++) {
            for (int j = 0; j < gates.size(); j++) {
                Wires wire = new Wires();

                if (gates.get(j).getName().contains("gate" + gates.get(i).getString())) {
                    if (!(gates.get(j).getName().equals(gates.get(i).getName()))) {
                        boolean check = false;
                        for (int m = 0; m < splitters.size(); m++) {
                            if (splitters.get(m).getName().equals("gate" + gates.get(i).getString())) {
                                check = true;
                                break;
                            }
                        }
                        if (!check) {
                            wire.connect(gates.get(j), gates.get(i));
                            wires.add(wire);
                        }
                    }
                }
            }
        }
    }

    /**
     * connects bulb and the last gate through wire
     */
    public void bulbConnect() {
        int last = gates.size() - 1;
        Wires wire = new Wires();
        if (gates.size() > 0 ) {
            wire.connect(bulb, gates.get(last));
        }
        else
        {
            wire.connect(bulb, switches.get(switches.size() - 1));
        }
        wires.add(wire);
    }
}