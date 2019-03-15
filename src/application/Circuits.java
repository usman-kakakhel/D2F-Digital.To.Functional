/**
 * @authors Daniyal Khalil, Mian Usman Naeem Kakakhel
 * last edit: 10 May 2018
 */
package application;

import java.util.ArrayList;
import java.util.UUID;


public class Circuits {

    //properties
    String id;
    ArrayList<Components> components;
    ArrayList<RightPaneChildren> GUIComponents;
    ArrayList<Components> toAdd;


    //constructor
    public Circuits() {
        components = new ArrayList<Components>();
        // wiresBefore = new ArrayList<>();
        GUIComponents = new ArrayList<>();
        toAdd = new ArrayList<>();
        setId(UUID.randomUUID().toString());
    }

    /**
     * this method returns size of GUIComponents
     *
     * @return the siz of GUIComponents
     */
    public int size() {
        return GUIComponents.size();
    }

    /**
     * this method gets the GUIComponents
     *
     * @return the GUIComponents
     */
    public ArrayList<RightPaneChildren> getGUIComponents() {
        return GUIComponents;
    }

    /**
     * getter method which returns Id
     *
     * @return the Id
     */
    public String getId() {
        return id;
    }

    /**
     * void which sets a specific Id to the id
     *
     * @param id the id which is to be set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * void which adds RightPane children to circuits
     *
     * @param component RightPane children which are added to GUI components
     */
    public void add(RightPaneChildren component) {
        GUIComponents.add(component);
    }

    /**
     * void which adds components to the components
     *
     * @param component the component which is to be added in components
     */
    public void add(Components component) {
        components.add(component);
    }


    /**
     * tostring method for combinatorial circuits
     *
     * @return the array list of tostring
     */
    public ArrayList<String> tostring() {

        ArrayList<String> strings = new ArrayList<String>();
        if (combinatorialCheck() == false) {
            strings.add("To String Cannot Be Made");
            return strings;
        }
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).getClass() == Bulb.class) {
                Bulb bulb = (Bulb) (components.get(i));
                strings.add(bulb.getName());
            }
        }
        return strings;
    }

    /**
     * void to initialise
     */
    public void initialise() {
        components.clear();
        for (int i = 0; i < GUIComponents.size(); i++) {
            if (GUIComponents.get(i).getTypee().equals("Node")) {
                ((DraggableNode) GUIComponents.get(i)).wiresIn.clear();
                ((DraggableNode) GUIComponents.get(i)).wiresOut.clear();
                ((DraggableNode) GUIComponents.get(i)).setComponent(null);
                ((DraggableNode) GUIComponents.get(i)).removeCircle();

                if (((DraggableNode) GUIComponents.get(i)).getType() == DragIconType.output) {

                    for (int l = 1; l < ((DraggableNode) GUIComponents.get(i)).getStyleClass().toArray().length; ) {
                        ((DraggableNode) GUIComponents.get(i)).getStyleClass().remove(l);
                    }
                    ((DraggableNode) GUIComponents.get(i)).getStyleClass().add("icon-output");
                }

            }
            if (GUIComponents.get(i).getTypee().equals("Wire")) {
                if (((NodeLink) (GUIComponents.get(i))).getTarget().getType() != DragIconType.splitter) {
                    ((NodeLink) (GUIComponents.get(i))).initialPaint();
                }
            }
        }
    }


    /**
     * method to draw the grid pane again
     */
    public void paint() {

        for (int i = 0; i < components.size(); i++) {
            for (int j = 0; j < GUIComponents.size(); j++) {
                if (GUIComponents.get(j).getTypee().equals("Wire")) {
                    if (components.get(i).getId().equals(((NodeLink) (GUIComponents.get(j))).getId())) {
                        if (((Wires) (components.get(i))).getState() == true) {
                            if ((((Wires) (components.get(i))).getEnd().getClass()) != Splitter.class) {
                                ((NodeLink) (GUIComponents.get(j))).repaint();
                            }
                        } else {
                            if ((((Wires) (components.get(i))).getEnd().getClass()) != Splitter.class) {
                                ((NodeLink) (GUIComponents.get(j))).initialPaint();
                            }
                        }
                    }
                } else if (GUIComponents.get(i).getTypee().equals("Node")) {
                    if (((DraggableNode) GUIComponents.get(i)).getType() == DragIconType.output) {
                        if (((Bulb) ((DraggableNode) GUIComponents.get(i)).getComponent()).getState()) {
                            for (int l = 1; l < ((DraggableNode) GUIComponents.get(i)).getStyleClass().toArray().length; l++) {
                                ((DraggableNode) GUIComponents.get(i)).getStyleClass().remove(l);
                            }
                            ((DraggableNode) GUIComponents.get(i)).getStyleClass().add("icon-outputOn");
                        } else {
                            for (int l = 1; l < ((DraggableNode) GUIComponents.get(i)).getStyleClass().toArray().length; l++) {
                                ((DraggableNode) GUIComponents.get(i)).getStyleClass().remove(l);
                            }
                            ((DraggableNode) GUIComponents.get(i)).getStyleClass().add("icon-output");
                        }
                    }
                }
            }
        }
    }


    /**
     * method to check circuit
     *
     * @return boolean value accordingly
     */
    public boolean circuitCheck() {
        boolean check = true;
        int bulbCount = 0;
        ArrayList<DragIconType> icons = new ArrayList<>();
        DraggableNode bulb = new DraggableNode();
        DraggableNode gate = new DraggableNode();
        NodeLink wire = new NodeLink();
        for (int j = 0; j < GUIComponents.size(); j++) {
            if (GUIComponents.get(j).getTypee().equals("Node")) {
                bulb = (DraggableNode) (GUIComponents.get(j));
                if (bulb.getType() == DragIconType.output) {
                    bulbCount++;
                }
            }

        }
        if (bulbCount < 1) {
            check = false;
            return check;
        }
        for (int i = 0; i < GUIComponents.size(); i++) {
            if (GUIComponents.get(i).getTypee().equals("Node")) {
                DraggableNode node = (DraggableNode) GUIComponents.get(i);

                if ((node.getType() == DragIconType.and)) {
                    if (node.getListOut().size() < 1) {
                        check = false;
                        return check;
                    }
                    if (node.getListIn().size() < 2) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.or)) {
                    if (node.getListOut().size() < 1) {
                        check = false;
                        return check;
                    }
                    if (node.getListIn().size() < 2) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.nand)) {
                    if (node.getListOut().size() < 1) {
                        check = false;
                        return check;
                    }
                    if (node.getListIn().size() < 2) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.nor)) {
                    if (node.getListOut().size() < 1) {
                        check = false;
                        return check;
                    }
                    if (node.getListIn().size() < 2) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.xor)) {
                    if (node.getListOut().size() < 1) {
                        check = false;
                        return check;
                    }
                    if (node.getListIn().size() < 2) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.not)) {
                    if (node.getListOut().size() < 1) {
                        check = false;
                        return check;
                    }
                    if (node.getListIn().size() < 1) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.clock)) {
                    if (node.getListOut().size() < 1) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.input)) {
                    if (node.getListOut().size() < 1) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.half_adder)) {
                    if (node.getListOut().size() < 2) {
                        check = false;
                        return check;
                    }
                    if (node.getListIn().size() < 2) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.full_adder)) {
                    if (node.getListOut().size() < 2) {
                        check = false;
                        return check;
                    }
                    if (node.getListIn().size() < 3) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.JK)) {
                    if (node.getListOut().size() < 2) {
                        check = false;
                        return check;
                    }
                    if (node.getListIn().size() < 3) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.SR)) {
                    if (node.getListOut().size() < 2) {
                        check = false;
                        return check;
                    }
                    if (node.getListIn().size() < 2) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.T)) {
                    if (node.getListOut().size() < 2) {
                        check = false;
                        return check;
                    }
                    if (node.getListIn().size() < 2) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.D)) {
                    if (node.getListOut().size() < 2) {
                        check = false;
                        return check;
                    }
                    if (node.getListIn().size() < 2) {
                        check = false;
                        return check;
                    }
                } else if ((node.getType() == DragIconType.splitter)) {
                    if (node.getListOut().size() < 1) {
                        check = false;
                        return check;
                    }
                    if (node.getListIn().size() < 0) {
                        check = false;
                        return check;
                    }
                }else if ((node.getType() == DragIconType.output)) {
                    if (node.getListIn().size() < 1) {
                        check = false;
                        return check;
                    }
                }


            }
        }

        return check;
    }

    /**
     * getter method to return size
     *
     * @return the size of components
     */
    public int getSize() {
        return components.size();
    }

    /**
     * method to check for combinatorial circuits
     *
     * @return the boolean value accordingly
     */
    public boolean combinatorialCheck() {

        for (int i = 0; i < components.size(); i++) {
            if ((components.get(i).getClass() == JKFlipFlop.class) || (components.get(i).getClass() == SRFlipFlop.class) ||
                    (components.get(i).getClass() == HalfAdder.class) || (components.get(i).getClass() == FullAdder.class) ||
                    (components.get(i).getClass() == Clock.class) || (components.get(i).getClass() == DFlipFlop.class)
                    || (components.get(i).getClass() == TFlipFlop.class) || (components.get(i).getClass() == XOR.class)) {
                return false;
            }
        }
        return true;
    }

    /**
     * method to check truth table
     *
     * @return the boolean value accordingly
     */
    public boolean truthTableCheck() {
        if (!combinatorialCheck()) {
            return false;
        }
        return true;
    }

    /**
     * void to create Circuit
     */
    public void createCircuit() {
        int numberOfTheSwitch = 65;
        int lowestLvl = 10000;
        int highestLvl = -10000;
        addComponentsFromGUI();


        for (int j = 0; j < components.size(); j++) {
            if (lowestLvl > components.get(j).getLvl())
                lowestLvl = components.get(j).getLvl();
            if (highestLvl < components.get(j).getLvl())
                highestLvl = components.get(j).getLvl();
            if (components.get(j).getTypee().equals("Switch")) {
                ((Switch) components.get(j)).setName("" + (char) numberOfTheSwitch);
                numberOfTheSwitch++;
            }
        }

        int y = lowestLvl;
        while (y <= highestLvl) {
            for (int i = 0; i < components.size(); i++) {

                if (!(components.get(i).getTypee().equals("Wires")) && (components.get(i).getLvl() == y)) {

                    for (int l = 0; l < ((DraggableNode) (components.get(i).getGUI())).wiresOut.size(); l++) {

                        Wires wire = ((DraggableNode) components.get(i).getGUI()).wiresOut.get(l);

                        if ((components.get(i).getType().equals("FullAdder"))) {
                            if (l == 0) {
                                wire.setState(((FullAdder) (components.get(i))).getSum());
                            }
                            if (l == 1) {
                                wire.setState(((FullAdder) (components.get(i))).getCarry());
                            }
                        } else if ((components.get(i).getType().equals("HalfAdder"))) {
                            if (l == 0) {
                                wire.setState(((HalfAdder) (components.get(i))).getSum());
                            } else if (l == 1) {
                                wire.setState(((HalfAdder) (components.get(i))).getCarry());
                            }
                        } else if ((components.get(i).getType().equals("DFlipFlop"))) {
                            if (l == 0) {
                                wire.setState(((DFlipFlop) (components.get(i))).getOut());
                                ((DraggableNode) (components.get(i).myGUI)).q = ((DFlipFlop) (components.get(i))).getOut();

                            } else if (l == 1) {
                                wire.setState(((DFlipFlop) (components.get(i))).getCmp());
                                ((DraggableNode) (components.get(i).myGUI)).notQ = ((DFlipFlop) (components.get(i))).getCmp();
                            }
                        } else if ((components.get(i).getType().equals("TFlipFlop"))) {
                            if (l == 0) {
                                wire.setState(((TFlipFlop) (components.get(i))).getOut());
                                ((DraggableNode) (components.get(i).myGUI)).q = ((TFlipFlop) (components.get(i))).getOut();
                            } else if (l == 1) {
                                wire.setState(((TFlipFlop) (components.get(i))).getCmp());
                                ((DraggableNode) (components.get(i).myGUI)).notQ = ((TFlipFlop) (components.get(i))).getCmp();

                            }
                        } else if ((components.get(i).getType().equals("SRFlipFlop"))) {
                            if (l == 0) {
                                wire.setState(((SRFlipFlop) (components.get(i))).getOut());
                                ((DraggableNode) (components.get(i).myGUI)).q = ((SRFlipFlop) (components.get(i))).getOut();
                            } else if (l == 1) {
                                wire.setState(((SRFlipFlop) (components.get(i))).getCmp());
                                ((DraggableNode) (components.get(i).myGUI)).notQ = ((SRFlipFlop) (components.get(i))).getCmp();
                            }
                        } else if ((components.get(i).getType().equals("JKFlipFlop"))) {
                            if (l == 0) {
                                wire.setState(((JKFlipFlop) (components.get(i))).getOut());
                                ((DraggableNode) (components.get(i).myGUI)).q = ((JKFlipFlop) (components.get(i))).getOut();
                            } else if (l == 1) {
                                wire.setState(((JKFlipFlop) (components.get(i))).getCmp());
                                ((DraggableNode) (components.get(i).myGUI)).notQ = ((JKFlipFlop) (components.get(i))).getCmp();
                            }
                        }


                        wire.connect(((GateInput) ((NodeLink) wire.getGUI()).target.getComponent()), ((GateInput) components.get(i)));

                        if (componentNotPresentInside(((NodeLink) wire.getGUI()).target.getComponent()) && ((NodeLink) wire.getGUI()).target.getComponent().getLvl() == y + 1)
                            toAdd.add(((NodeLink) wire.getGUI()).target.getComponent());

                    }

                }
            }
            for (Components a : toAdd) {
                inputWiresToGate(a);
            }
            toAdd.clear();
            y++;
        }
        paint();
    }

    /**
     * method to chech if components are present or not
     *
     * @param a the components to check
     * @return the boolean value accordingly
     */
    public boolean componentNotPresentInside(Components a) {
        for (Components b : toAdd) {
            if (a.getId().equals(b.getId()))
                return false;
        }
        return true;
    }


    /**
     * void to input wires in to the gates
     *
     * @param gate in which wire will be added
     */
    public void inputWiresToGate(Components gate) {
        ArrayList<Wires> listt = ((DraggableNode) gate.getGUI()).wiresIn;
        DragIconType mType = ((DraggableNode) gate.getGUI()).getType();


        switch (mType) {

            case and:
                ((AND) gate).input(listt.toArray(new Wires[listt.size()]));
                break;

            case or:
                ((OR) gate).input(listt.toArray(new Wires[listt.size()]));
                break;
            case not:
                ((NOT) gate).input(listt.get(0));
                ((NOT) gate).setName();
                break;

            case xor:
                ((XOR) gate).input(listt.toArray(new Wires[listt.size()]));
                break;

            case nand:
                ((NAND) gate).input(listt.toArray(new Wires[listt.size()]));

                break;

            case nor:
                ((NOR) gate).input(listt.toArray(new Wires[listt.size()]));

                break;

            case input:

                break;

            case clock:


                break;
            case output:
                ((Bulb) gate).connect(listt.get(0));
                break;
            case splitter:
                ((Splitter) gate).input(listt.get(0));
                break;

            case half_adder:
                ((HalfAdder) gate).input(listt.toArray(new Wires[listt.size()]));

                break;

            case full_adder:

                ((FullAdder) gate).input(listt.toArray(new Wires[listt.size()]));

                break;

            case JK:
                ((JKFlipFlop) gate).input(listt.toArray(new Wires[listt.size()]));

                break;

            case SR:
                ((SRFlipFlop) gate).input(listt.toArray(new Wires[listt.size()]));

                break;

            case T:
                ((TFlipFlop) gate).input(listt.toArray(new Wires[listt.size()]));

                break;

            case D:
                ((DFlipFlop) gate).input(listt.toArray(new Wires[listt.size()]));

                break;

            default:
                break;

        }

    }


    /**
     * void to add components from GUI
     */
    public void addComponentsFromGUI() {
        if (circuitCheck() == false) {
            return;
        }
        int y = 0;


        for (int i = 0; i < GUIComponents.size(); i++) {
            if (GUIComponents.get(i).getTypee().equals("Node")) {

                DragIconType mType = ((DraggableNode) GUIComponents.get(i)).getType();

                switch (mType) {

                    case and:
                        addGate(new AND(), i);
                        break;

                    case or:

                        addGate(new OR(), i);
                        break;

                    case not:

                        addGate(new NOT(), i);
                        break;

                    case xor:
                        addGate(new XOR(), i);
                        break;

                    case nand:

                        addGate(new NAND(), i);
                        break;

                    case nor:

                        addGate(new NOR(), i);
                        break;

                    case input:
                        addGate(new Switch(((DraggableNode) GUIComponents.get(i)).state), i);
                        break;

                    case clock:
                        addGate((new Clock(((DraggableNode) GUIComponents.get(i)).state)), i);
                        break;
                    case output:
                        addGate(new Bulb(), i);
                        break;
                    case splitter:
                        addGate(new Splitter(), i);
                        break;

                    case half_adder:

                        addGate(new HalfAdder(), i);
                        break;

                    case full_adder:

                        addGate(new FullAdder(), i);
                        break;

                    case JK:

                        addGate(new JKFlipFlop((((DraggableNode) GUIComponents.get(i)).q), (((DraggableNode) GUIComponents.get(i)).notQ)), i);
                        break;

                    case SR:

                        addGate(new SRFlipFlop((((DraggableNode) GUIComponents.get(i)).q), (((DraggableNode) GUIComponents.get(i)).notQ)), i);
                        break;

                    case T:

                        addGate(new TFlipFlop((((DraggableNode) GUIComponents.get(i)).q), (((DraggableNode) GUIComponents.get(i)).notQ)), i);
                        break;

                    case D:

                        addGate(new DFlipFlop((((DraggableNode) GUIComponents.get(i)).q), (((DraggableNode) GUIComponents.get(i)).notQ)), i);
                        break;

                    default:
                        break;

                }
            } else if (GUIComponents.get(i).getTypee().equals("Wire")) {
                Wires wire = new Wires();

                wire.setId(((NodeLink) GUIComponents.get(i)).getId());
                wire.setCircuit(((NodeLink) GUIComponents.get(i)).myCircuit);
                wire.setGUI(GUIComponents.get(i));
                ((NodeLink) GUIComponents.get(i)).source.wiresOut.add(wire);
                ((NodeLink) GUIComponents.get(i)).target.wiresIn.add(wire);
                components.add(wire);
            }
        }


    }

    /**
     * void to add gates
     *
     * @param gate the gate to be added
     * @param i    index of addition
     */
    public void addGate(Components gate, int i) {
        gate.setId(((DraggableNode) GUIComponents.get(i)).getId());
        gate.setGUI(GUIComponents.get(i));
        gate.setCircuit(((DraggableNode) GUIComponents.get(i)).myCircuit);
        gate.setLvl(((DraggableNode) GUIComponents.get(i)).level);
        ((DraggableNode) GUIComponents.get(i)).setComponent(gate);
        components.add(gate);
    }

}


