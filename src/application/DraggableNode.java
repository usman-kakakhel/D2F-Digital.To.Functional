/**
 * @author Talal Ahmad, Joel Graff, Muhammad Bilal, Mian Usman Naeem Kakakhel, Balaj Saleem
 * last edit: 10 May 2018
 */
package application;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static java.awt.event.KeyEvent.VK_C;

public class DraggableNode extends AnchorPane implements Serializable, RightPaneChildren {

    @FXML
    private AnchorPane root_pane;
    @FXML
    private AnchorPane left_link_handle;
    @FXML
    private AnchorPane right_link_handle;
    @FXML
    private Label title_bar;
    @FXML
    private Label close_button;
    @FXML
    ImageView remove;
    @FXML
    AnchorPane title_bar_anchor;
    @FXML
    AnchorPane cross_something_pane;


    public int in;
    public int out;

    int clockTicks = 0;
    int delaySeconds = 0;

    public int inValue;

    public boolean offsetChecker;
    public boolean inputOffsetChecker;
    public boolean basicInputOffset;
    boolean botCheck = false;

    public boolean state = false;

    private String type1;

    public double xLoc;
    public double yLoc;
    private List<String> mLinkIdsIn = new ArrayList<String>();
    private List<String> mLinkIdsOut = new ArrayList<String>();
    ArrayList<Wires> wiresIn = new ArrayList<>();
    ArrayList<Wires> wiresOut = new ArrayList<>();
    private Components thisComponent;
    Circuits myCircuit;

    private EventHandler<MouseEvent> mLinkHandleDragDetected;
    private EventHandler<DragEvent> mLinkHandleDragDropped;
    private EventHandler<DragEvent> mContextLinkDragOver;
    private EventHandler<DragEvent> mContextLinkDragDropped;

    private EventHandler<DragEvent> mContextDragOver;
    private EventHandler<DragEvent> mContextDragDropped;

    private DragIconType mType = null;
    public boolean q = false;
    public boolean notQ = false;
    int level = 0;

    private Point2D mDragOffset = new Point2D(0.0, 0.0);

    private final DraggableNode self;

    private NodeLink mDragLink = null;
    private AnchorPane right_pane = null;

    private List<String> mLinkIds = new ArrayList<String>();

    /**
     * getter method to get list
     *
     * @return the list
     */
    public List<String> getList() {
        return mLinkIds;
    }

    /**
     * getter method to get list In
     *
     * @return returns listIn
     */
    public List<String> getListIn() {
        return mLinkIdsIn;
    }

    /**
     * getter method to get list Out
     *
     * @return returns listIn
     */
    public List<String> getListOut() {
        return mLinkIdsOut;
    }

    /**
     * void to add a string to listIn
     *
     * @param a the string to be added
     */
    public void addToListIn(String a) {
        mLinkIdsIn.add(a);
    }

    /**
     * void to add a string to listOut
     *
     * @param a the String to be added in ListOut
     */
    public void addToListOut(String a) {
        mLinkIdsOut.add(a);
    }

    /**
     * getter method to get components
     *
     * @return the components
     */
    public Components getComponent() {
        return thisComponent;
    }

    /**
     * void to set component
     *
     * @param thisComponent the component which is to be added
     */
    public void setComponent(Components thisComponent) {
        this.thisComponent = thisComponent;
    }


    boolean lablVisibility = false;
    boolean crossSomethingVisilblity = false;

    public DraggableNode() {
        type1 = "Node";


        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/DraggableNode.fxml")
        );

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        self = this;

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        //provide a universally unique identifier for this object
        setId(UUID.randomUUID().toString());


        if (RootLayout.removeSelected) {
            toggleRemoveOpacity();
        }
        if (RootLayout.selectSelected) {
            title_bar_anchor.setVisible(true);
            lablVisibility = true;
        }
    }

    /**
     * void to set label for dragging
     */
    public void setLabelDrag() {
        if (!lablVisibility) {
            lablVisibility = !lablVisibility;
            title_bar_anchor.setVisible(true);
        } else {
            lablVisibility = !lablVisibility;
            title_bar_anchor.setVisible(false);
        }
    }

    /**
     * void to set m link Ids
     *
     * @param mLinkIds the string list of mLinkIds
     */
    public void setmLinkIds(List<String> mLinkIds) {
        this.mLinkIds = mLinkIds;
    }

    /**
     * void to set m link ids in
     *
     * @param mLinkIdsIn the string list of mLinkIdsIn
     */
    public void setmLinkIdsIn(List<String> mLinkIdsIn) {
        this.mLinkIdsIn = mLinkIdsIn;
    }

    /**
     * void to set m link ids Out
     *
     * @param mLinkIdsOut the string list of mLinkIdsOut
     */
    public void setmLinkIdsOut(List<String> mLinkIdsOut) {
        this.mLinkIdsOut = mLinkIdsOut;
    }

    /**
     * getter method to get type
     *
     * @return type
     */
    public String getTypee() {
        return type1;
    }

    @FXML
    private void initialize() {

        buildNodeDragHandlers();
        buildLinkDragHandlers();

        left_link_handle.setOnDragDetected(mLinkHandleDragDetected);
        right_link_handle.setOnDragDetected(mLinkHandleDragDetected);

        title_bar_anchor.addEventHandler(MouseEvent.MOUSE_PRESSED, new mySelfClicked());
        title_bar_anchor.addEventHandler(MouseEvent.MOUSE_ENTERED, new nodeMouseEntered());


        left_link_handle.setOnDragDropped(mLinkHandleDragDropped);
        right_link_handle.setOnDragDropped(mLinkHandleDragDropped);

        mDragLink = new NodeLink();
        mDragLink.setVisible(false);

        parentProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable,
                                Object oldValue, Object newValue) {
                right_pane = (AnchorPane) getParent();

            }

        });

//        Circle c = new Circle();
//        c.setRadius(5.0f);
//        this.getChildren().add(c);

    }

    /**
     * void to add ids in mLinkIds
     *
     * @param linkId the id to be added
     */
    public void registerLink(String linkId) {
        mLinkIds.add(linkId);
    }

    /**
     * getter method to get wires
     *
     * @return the wiresOut
     */
    public ArrayList<Wires> getWiresOut() {
        return wiresOut;
    }

    /**
     * void to relocates the object to a point that has been converted to scene coordinate
     *
     * @param p the point where it will be relocated
     */
    public void relocateToPoint(Point2D p) {

        //relocates the object to a point that has been converted to
        //scene coordinates
        Point2D localCoords = getParent().sceneToLocal(p);
        xLoc = (int) (localCoords.getX() - mDragOffset.getX());
        yLoc = (int) (localCoords.getY() - mDragOffset.getY());
        relocate(
                //(int) (localCoords.getX() - mDragOffset.getX()),
                //(int) (localCoords.getY() - mDragOffset.getY())
                (int) (localCoords.getX()),
                (int) (localCoords.getY())

        );
    }

    /**
     * getter method to get type
     *
     * @return the mType
     */
    public DragIconType getType() {
        return mType;
    }

    /**
     * void to set type
     *
     * @param type type to be set
     */
    public void setType(DragIconType type) {

        mType = type;

        getStyleClass().clear();
        getStyleClass().add("dragicon");

        switch (mType) {

            case and:
                getStyleClass().add("icon-and");
                putComponentInCircuit(new AND());
                inValue = 2;
                break;

            case or:
                getStyleClass().add("icon-or");
                putComponentInCircuit(new OR());
                inValue = 2;
                break;

            case not:
                getStyleClass().add("icon-not");
                putComponentInCircuit(new NOT());
                inValue = 1;
                break;

            case xor:
                getStyleClass().add("icon-xor");
                putComponentInCircuit(new XOR());
                inValue = 2;
                break;

            case nand:
                getStyleClass().add("icon-nand");
                putComponentInCircuit(new NAND());
                inValue = 2;
                break;

            case nor:
                getStyleClass().add("icon-nor");
                putComponentInCircuit(new NOR());
                inValue = 2;
                break;

            case input:
                getStyleClass().add("icon-input");
                putComponentInCircuit(new Switch(state));
                break;

            case clock:
                getStyleClass().add("icon-clock");
                putComponentInCircuit(new Clock());
                break;

            case output:
                getStyleClass().add("icon-output");
                putComponentInCircuit(new Bulb());
                inValue = 2;
                break;

            case splitter:
                getStyleClass().add("icon-splitter");
                putComponentInCircuit(new Splitter());
                inValue = 1;
                break;

            case half_adder:
                getStyleClass().add("icon-half_adder");
                putComponentInCircuit(new HalfAdder());
                inValue = 2;
                break;

            case full_adder:
                getStyleClass().add("icon-full_adder");
                putComponentInCircuit(new FullAdder());
                inValue = 3;
                break;

            case JK:
                getStyleClass().add("icon-JK");
                putComponentInCircuit(new JKFlipFlop(q, notQ));
                inValue = 3;
                break;

            case SR:
                getStyleClass().add("icon-SR");
                putComponentInCircuit(new SRFlipFlop(q, notQ));
                inValue = 2;
                break;

            case T:
                getStyleClass().add("icon-T");
                putComponentInCircuit(new TFlipFlop(q, notQ));
                inValue = 2;
                break;

            case D:
                getStyleClass().add("icon-D");
                putComponentInCircuit(new DFlipFlop(q, notQ));
                inValue = 2;
                break;

            default:
                break;
        }
    }

    /**
     * void to put components in the circuit
     *
     * @param gate the gate which is to be added
     */
    public void putComponentInCircuit(Components gate) {
        myCircuit = RootLayout.myCircuits.get(RootLayout.myCircuits.size() - 1);
        RootLayout.myCircuits.get(RootLayout.myCircuits.size() - 1).add(this);
    }

    /**
     * check if node not going before output node and not going after input node
     *
     * @param localCoords the co ordinates of the location
     * @return the boolean value accordingly
     */
    public boolean nodeLocationCheck(Point2D localCoords) {
        boolean check = true;

        int x = (int) (localCoords.getX() - mDragOffset.getX());

        for (String in : mLinkIdsIn) {
            for (Object o : right_pane.getChildren().toArray()) {
                if (((RightPaneChildren) o).getTypee().equals("Node")) {
                    for (String out : ((DraggableNode) o).getListOut()) {
                        if (in.equals(out)) {
                            if ((((DraggableNode) o).xLoc >= x - 50) && ((DraggableNode.this.getType() != DragIconType.splitter) &&
                                    (((DraggableNode) o).getType() != DragIconType.splitter))) {
                                check = false;
                            }
                        }
                    }
                }
            }
        }

        for (String out : mLinkIdsOut) {
            for (Object o : right_pane.getChildren().toArray()) {
                if (((RightPaneChildren) o).getTypee().equals("Node")) {
                    for (String in : ((DraggableNode) o).getListIn()) {
                        if (in.equals(out)) {
                            if ((((DraggableNode) o).xLoc <= x + 70) && ((DraggableNode.this.getType() != DragIconType.splitter) &&
                                    (((DraggableNode) o).getType() != DragIconType.splitter))) {
                                check = false;
                            }
                        }
                    }
                }
            }
        }

        return check;
    }


    /**
     * void to build the node handlers
     */
    public void buildNodeDragHandlers() {

        mContextDragOver = new EventHandler<DragEvent>() {

            //dragover to handle node dragging in the right pane view
            @Override
            public void handle(DragEvent event) {

                event.acceptTransferModes(TransferMode.ANY);
                Point2D p = new Point2dSerial(event.getSceneX(), event.getSceneY());
                Point2D localCoords = getParent().sceneToLocal(p);

                if (nodeLocationCheck(localCoords))
                    relocateToPoint(p);
                event.consume();
            }
        };


        //dragdrop for node dragging
        mContextDragDropped = new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {

                getParent().setOnDragOver(null);
                getParent().setOnDragDropped(null);

                event.setDropCompleted(true);

                event.consume();
            }
        };

        //close button click
        close_button.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (close_button.getOpacity() == 1) {

                    for (int i = 0; i < DraggableNode.this.myCircuit.size(); ) {
                        boolean check = true;
                        if ((check) && (DraggableNode.this.myCircuit.GUIComponents.get(i).getTypee().equals("Node"))) {
                            if (((DraggableNode) DraggableNode.this.myCircuit.GUIComponents.get(i)).getId().equals(DraggableNode.this.getId())) {
                                DraggableNode.this.myCircuit.GUIComponents.remove(i);
                                check = false;
                            } else {
                                check = true;
                            }
                        } else if (DraggableNode.this.myCircuit.GUIComponents.get(i).getTypee().equals("Wire")) {
                            for (String a : DraggableNode.this.mLinkIds) {
                                if ((check) && ((NodeLink) DraggableNode.this.myCircuit.GUIComponents.get(i)).getId().equals(a)) {
                                    DraggableNode.this.myCircuit.GUIComponents.remove(i);
                                    check = false;
                                }
                            }
                        }
                        if (check)
                            i++;
                    }

                    removeWiresFromSurroundingGates();
                    AnchorPane parent = (AnchorPane) self.getParent();
                    parent.getChildren().remove(self);


                    //iterate each link id connected to this node
                    //find it's corresponding component in the right-hand
                    //AnchorPane and delete it.

                    //Note:  other nodes connected to these links are not being
                    //notified that the link has been removed.
                    for (ListIterator<String> iterId = mLinkIds.listIterator();
                         iterId.hasNext(); ) {

                        String id = iterId.next();

                        for (ListIterator<Node> iterNode = parent.getChildren().listIterator();
                             iterNode.hasNext(); ) {

                            Node node = iterNode.next();

                            if (node.getId() == null)
                                continue;

                            if (node.getId().equals(id))
                                iterNode.remove();
                        }

                        iterId.remove();
                    }

                }
            }

        });

        //drag detection for node dragging
        title_bar.setOnDragDetected(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                getParent().setOnDragOver(null);
                getParent().setOnDragDropped(null);

                getParent().setOnDragOver(mContextDragOver);
                getParent().setOnDragDropped(mContextDragDropped);

                //begin drag ops

                mDragOffset = new Point2D(event.getX(), event.getY());
                relocateToPoint(
                        new Point2D(event.getSceneX(), event.getSceneY())
                );

                ClipboardContent content = new ClipboardContent();
                DragContainer container = new DragContainer();

                container.addData("type", mType.toString());
                content.put(DragContainer.AddNode, container);

                startDragAndDrop(TransferMode.ANY).setContent(content);

                event.consume();
            }

        });
    }

    /**
     * void to build the node handlers
     */
    private void buildLinkDragHandlers() {

        mLinkHandleDragDetected = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                getParent().setOnDragOver(null);
                getParent().setOnDragDropped(null);

                getParent().setOnDragOver(mContextLinkDragOver);
                getParent().setOnDragDropped(mContextLinkDragDropped);

                //Set up user-draggable link for all types of components
                //and the output links are limited to their own personal outputs
                if ((mType == DragIconType.and) && (out < 1)) {
                    dragFromComponent();
                } else if ((mType == DragIconType.or) && (out < 1)) {
                    dragFromComponent();
                } else if ((mType == DragIconType.not) && (out < 1)) {
                    dragFromComponent();
                } else if ((mType == DragIconType.xor) && (out < 1)) {
                    dragFromComponent();
                } else if ((mType == DragIconType.nand) && (out < 1)) {
                    dragFromComponent();
                } else if ((mType == DragIconType.nor) && (out < 1)) {
                    dragFromComponent();
                } else if ((mType == DragIconType.input) && (out < 1)) {
                    dragFromComponent();
                } else if ((mType == DragIconType.clock) && (out < 1)) {
                    dragFromComponent();
                } else if (mType == DragIconType.splitter) {
                    dragFromComponent();
                } else if ((mType == DragIconType.JK) && (out < 2)) {
                    offsetChecker = true;
                    dragFromComponent();
                } else if ((mType == DragIconType.SR) && (out < 2)) {
                    offsetChecker = true;
                    dragFromComponent();
                } else if ((mType == DragIconType.T) && (out < 2)) {
                    offsetChecker = true;
                    dragFromComponent();
                } else if ((mType == DragIconType.D) && (out < 2)) {
                    offsetChecker = true;
                    dragFromComponent();
                } else if ((mType == DragIconType.half_adder) && (out < 2)) {
                    offsetChecker = true;
                    dragFromComponent();
                } else if ((mType == DragIconType.full_adder) && (out < 2)) {
                    offsetChecker = true;
                    dragFromComponent();
                }


                event.consume();
            }

            public void dragFromComponent() {
                //out++;
                mDragLink.addPrev(DraggableNode.this);
                right_pane.getChildren().add(0, mDragLink);

                mDragLink.setVisible(false);

                int offset = 0;

                if (offsetChecker) {
                    if (out == 1) {
                        offset = -10;
                    }
                    if (out == 2) {
                        offset = 10;
                    }
                }

                Point2D p = new Point2D(
                        getLayoutX() + (getWidth() / 2.0),
                        getLayoutY() + offset + (getHeight() / 2.0)
                );

                mDragLink.setStart(p);
                if (offset < 0)
                    System.out.println("Upper output");
                if (offset > 0)
                    System.out.println("Lower output");


                //Drag content code
                ClipboardContent content = new ClipboardContent();
                DragContainer container = new DragContainer();

                //pass the UUID of the source node for later lookup
                container.addData("source", getId());

                content.put(DragContainer.AddLink, container);

                startDragAndDrop(TransferMode.ANY).setContent(content);
            }
        };

        mLinkHandleDragDropped = new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                getParent().setOnDragOver(null);
                getParent().setOnDragDropped(null);

                //Set up user-draggable link for all types of components
                //and the input links are limited to their own personal inputs

                if ((mType == DragIconType.and) && (in < inValue)) {
                    basicInputOffset = true;
                    draggedToComponent(event);
                } else if ((mType == DragIconType.or) && (in < inValue)) {
                    basicInputOffset = true;
                    draggedToComponent(event);
                } else if ((mType == DragIconType.not) && (in < 1)) {
                    draggedToComponent(event);
                } else if ((mType == DragIconType.xor) && (in < inValue)) {
                    basicInputOffset = true;
                    draggedToComponent(event);
                } else if ((mType == DragIconType.nand) && (in < inValue)) {
                    basicInputOffset = true;
                    draggedToComponent(event);
                } else if ((mType == DragIconType.nor) && (in < inValue)) {
                    basicInputOffset = true;
                    draggedToComponent(event);
                } else if ((mType == DragIconType.output) && (in < 1)) {
                    draggedToComponent(event);
                } else if ((mType == DragIconType.splitter) && (in < 1)) {
                    draggedToComponent(event);
                } else if ((mType == DragIconType.JK) && (in < 3)) {
                    inputOffsetChecker = true;
                    draggedToComponent(event);
                } else if ((mType == DragIconType.SR) && (in < 2)) {
                    inputOffsetChecker = true;
                    draggedToComponent(event);
                } else if ((mType == DragIconType.T) && (in < 2)) {
                    inputOffsetChecker = true;
                    draggedToComponent(event);
                } else if ((mType == DragIconType.D) && (in < 2)) {
                    inputOffsetChecker = true;
                    draggedToComponent(event);
                } else if ((mType == DragIconType.half_adder) && (in < 2)) {
                    inputOffsetChecker = true;
                    draggedToComponent(event);
                } else if ((mType == DragIconType.full_adder) && (in < 3)) {
                    inputOffsetChecker = true;
                    draggedToComponent(event);
                } else {
                    //((NodeLink) right_pane.getChildren().get(0)).getPrev().out--;
                    mDragLink.setVisible(false);
                    right_pane.getChildren().remove(0);
                    event.setDropCompleted(true);
                }
                event.consume();
            }

            /**
             * void which draws wire from components
             * @param event draws from where mouse is clicked
             */
            private void draggedToComponent(DragEvent event) {
                ((NodeLink) right_pane.getChildren().get(0)).addNext(DraggableNode.this);
                //in++;
                //get the drag data.  If it's null, abort.
                //This isn't the drag event we're looking for.
                DragContainer container =
                        (DragContainer) event.getDragboard().getContent(DragContainer.AddLink);

                if (container == null)
                    return;

                //hide the draggable NodeLink and remove it from the right-hand AnchorPane's children
                mDragLink.setVisible(false);

                int offset1 = 0;

                if (inputOffsetChecker) {
                    if (in == 1) {
                        offset1 = -10;
                    }
                    if (in == 2) {
                        offset1 = 10;
                    }
                }
                if (offset1 < 0)
                    System.out.println("Upper input");
                if (offset1 > 0)
                    System.out.println("Lower input");
                if (inputOffsetChecker && in > 2)
                    System.out.println("Clock");


                right_pane.getChildren().remove(0);

                AnchorPane link_handle = (AnchorPane) event.getSource();

                ClipboardContent content = new ClipboardContent();

                //pass the UUID of the target node for later lookup
                container.addData("target", getId());

                content.put(DragContainer.AddLink, container);

                event.getDragboard().setContent(content);
                event.setDropCompleted(true);
            }
        };

        mContextLinkDragOver = new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {

                event.acceptTransferModes(TransferMode.ANY);

                //Relocate end of user-draggable link
                if (!mDragLink.isVisible())
                    mDragLink.setVisible(true);

                try {
                    Robot bot = new Robot();
                    Point2D p = new Point2D(event.getX(), event.getY());

                    if (!checkWireGoingBackward(p)) {
                        mDragLink.setEnd(p);
                    } else {
                        mDragLink.setVisible(false);
                        bot.mouseRelease(InputEvent.BUTTON1_MASK);
                    }
                } catch (AWTException e) {
                    e.printStackTrace();
                }


                event.consume();

            }
        };

        //drop event for link creation
        mContextLinkDragDropped = new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                System.out.println("context link drag dropped");
                //out--;
                mDragLink.removePrev();
                getParent().setOnDragOver(null);
                getParent().setOnDragDropped(null);

                //hide the draggable NodeLink and remove it from the right-hand AnchorPane's children
                mDragLink.setVisible(false);
                right_pane.getChildren().remove(0);

                event.setDropCompleted(true);
                event.consume();
            }

        };

    }


    /**
     * method to check if wire is going back from the gate or not
     *
     * @param p the co ordinates of the location
     * @return the boolean accordingly
     */
    public boolean checkWireGoingBackward(Point2D p) {
        boolean check = false;
        int x = (int) (p.getX() - mDragOffset.getX());

        if (xLoc + 50 >= x) {
            check = true;
        }
        return check;
    }


    /**
     * set remove button red cross to given opacity
     */
    public void toggleRemoveOpacity() {
        if (close_button.getOpacity() == 1)
            close_button.setOpacity(0);
        else if (close_button.getOpacity() == 0)
            close_button.setOpacity(1);
    }


    /**
     * remove wire from the surrounding gates
     */
    public void removeWiresFromSurroundingGates() {
        for (int l = 0; l < mLinkIdsIn.size(); l++) {
            String in = mLinkIdsIn.get(l);
            for (int o = 0; o < right_pane.getChildren().size(); o++) {
                Object d = right_pane.getChildren().toArray()[o];
                if (((RightPaneChildren) d).getTypee().equals("Node")) {
                    for (int i = 0; i < ((DraggableNode) d).getListOut().size(); ) {
                        if (((DraggableNode) d).getListOut().get(i).equals(in)) {
                            ((DraggableNode) d).getListOut().remove(i);
                            for (int j = 0; j < ((DraggableNode) d).getList().size(); ) {
                                if (((DraggableNode) d).getList().get(j).equals(in))
                                    ((DraggableNode) d).getList().remove(j);
                                else j++;
                            }
                            ((DraggableNode) d).out--;
                        } else i++;
                    }
                }
            }
        }

        for (int l = 0; l < mLinkIdsOut.size(); l++) {
            String out = mLinkIdsOut.get(l);
            for (int o = 0; o < right_pane.getChildren().size(); o++) {
                Object d = right_pane.getChildren().toArray()[o];
                if (((RightPaneChildren) d).getTypee().equals("Node")) {
                    for (int i = 0; i < ((DraggableNode) d).getListIn().size(); ) {
                        if (((DraggableNode) d).getListIn().get(i).equals(out)) {
                            ((DraggableNode) d).getListIn().remove(i);
                            for (int j = 0; j < ((DraggableNode) d).getList().size(); ) {
                                if (((DraggableNode) d).getList().get(j).equals(out))
                                    ((DraggableNode) d).getList().remove(j);
                                else j++;
                            }
                            ((DraggableNode) d).in--;
                        } else i++;

                    }
                }
            }
        }
    }


    /**
     * On pressing Cross in root layout node will not be able to do anything other than crossing
     */
    public void toggleCrossPaneVisible() {
        if (!crossSomethingVisilblity) {
            crossSomethingVisilblity = !crossSomethingVisilblity;
            cross_something_pane.setVisible(true);
        } else {
            crossSomethingVisilblity = !crossSomethingVisilblity;
            cross_something_pane.setVisible(false);
        }

    }


    public class mySelfClicked implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                onClicked();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * void which performs actions when nodes are clicked
         *
         * @throws IOException when exception
         */
        public void onClicked() throws IOException {
            if ((DraggableNode.this.mType == DragIconType.input) && (!state)) {
                getStyleClass().remove("icon-input");
                getStyleClass().add("icon-inputOn");
            } else if ((DraggableNode.this.mType == DragIconType.input) && (state)) {
                getStyleClass().remove("icon-inputOn");
                getStyleClass().add("icon-input");
            }


            if (RootLayout.jani != null) {
                removeCircle();
            }
            RootLayout.jani = DraggableNode.this;
            state = !state;

            if (RootLayout.runSelected) {
                if (DraggableNode.this.getType() == DragIconType.clock) {
                    botCheck = true;

                    try {
                        Robot bot = new Robot();
                        for (int c = 0; c < clockTicks; c++) {
                            bot.keyPress(VK_C);
                            bot.keyRelease(VK_C);
                        }

                    } catch (AWTException e) {
                        e.printStackTrace();
                    }


                } else {
                    myCircuit.initialise();
                    myCircuit.createCircuit();

                }
                // botCheck = false;
            }
            Circle c = new Circle();
            c.setRadius(30.0f);
            c.setFill(Color.TRANSPARENT);
            c.setStroke(Color.RED);
            c.setCenterY(50);
            c.setCenterX(50);

            if (!DraggableNode.this.getChildren().contains(c)) {
                DraggableNode.this.getChildren().add(0, c);
            }
            if (mType == DragIconType.output) {
                RootLayout.bulbSelected = true;

            } else {
                RootLayout.bulbSelected = false;

            }
        }
    }

    public void removeCircle() {
        if (RootLayout.jani != null) {
            for (int i = 0; i < RootLayout.jani.getChildren().toArray().length; ) {
                if (RootLayout.jani.getChildren().get(i).getClass() == Circle.class) {
                    RootLayout.jani.getChildren().remove(i);
                } else {
                    i++;
                }
            }
        }

    }

    public class nodeMouseEntered implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                onEntered();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * void which performs actions when mouse is entered on a node
         *
         * @throws IOException when exception
         */
        public void onEntered() throws IOException {
            if (RootLayout.runSelected) {
                if (DraggableNode.this.getComponent() != null) {
                    Tooltip info = new Tooltip();
                    if (DraggableNode.this.getComponent().getType().equals("Bulb"))
                        info.setText(((Bulb) DraggableNode.this.getComponent()).getName());
                    else if (DraggableNode.this.getComponent().getType().equals("AND") || DraggableNode.this.getComponent().getType().equals("OR")
                            || DraggableNode.this.getComponent().getType().equals("NOT") || DraggableNode.this.getComponent().getType().equals("NAND") ||
                            DraggableNode.this.getComponent().getType().equals("NOR") ||
                            DraggableNode.this.getComponent().getType().equals("XOR") || DraggableNode.this.getComponent().getType().equals("Splitter"))
                        info.setText(((BasicGates) DraggableNode.this.getComponent()).getName());
                    else if (DraggableNode.this.getComponent().getType().equals("Switch"))
                        info.setText(((Switch) DraggableNode.this.getComponent()).getName());

                    Tooltip.install(DraggableNode.this, info);
                }
            }

        }
    }

    /**
     * getter method to get Offsetchecker
     *
     * @return offsetchecker
     */
    public boolean getOffsetChecker() {

        return offsetChecker;
    }

    /**
     * getter method to get InputOffsetchecker
     *
     * @return InputOffsetChecker
     */
    public boolean getInputOffsetChecker() {

        return inputOffsetChecker;
    }

    /**
     * getter method to get BasicInputOffset
     *
     * @return BasicOffsetChecker
     */
    public boolean getBasicInputOffset() {

        return basicInputOffset;
    }

    /**
     * getter method to get value of Out
     *
     * @return the value of out
     */
    public int getOut() {

        return out;
    }


    /**
     * getter method to get value of In
     *
     * @return the value of in
     */
    public int getIn() {

        return in;
    }

}
