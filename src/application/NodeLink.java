/**
 * @author Talal Ahmad, Joel Graff, Muhammad Bilal, Mian Usman Naeem Kakakhel, Balaj Saleem
 * last edit: 10 May 2018
 */
package application;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.When;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.QuadCurveTo;

public class NodeLink extends AnchorPane implements Serializable, RightPaneChildren {

    @FXML
    CubicCurve node_link;


    private final DoubleProperty mControlOffsetX = new SimpleDoubleProperty();
    private final DoubleProperty mControlOffsetY = new SimpleDoubleProperty();
    private final DoubleProperty mControlDirectionX1 = new SimpleDoubleProperty();
    private final DoubleProperty mControlDirectionY1 = new SimpleDoubleProperty();
    private final DoubleProperty mControlDirectionX2 = new SimpleDoubleProperty();
    private final DoubleProperty mControlDirectionY2 = new SimpleDoubleProperty();

    private DraggableNode prev;
    private DraggableNode next;
    public DraggableNode source;
    public DraggableNode target;
    private String sourceId;
    private String targetId;
    private String type;
    Circuits myCircuit;


    public NodeLink() {
        type = "Wire";
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/NodeLink.fxml")
        );

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //provide a universally unique identifier for this object
        setId(UUID.randomUUID().toString());
    }

    /**
     * getter method to get source
     * @return the DraggableNode source.
     */
    public DraggableNode getSource() {
        return source;
    }

    /**
     * getter method to get target
     * @return the DraggableNode target.
     */
    public DraggableNode getTarget() {
        return target;
    }

    /**
     * getter method to get type
     * @return the string type
     */
    public String getTypee() {
        return type;
    }

    /**
     * getter method to get previous
     * @return the previous DraggableNode
     */
    public DraggableNode getPrev() {
        return prev;
    }

    /**
     * getter method to get next
     * @return the next DraggableNode
     */
    public DraggableNode getNext() {
        return next;
    }

    /**
     * void to add previous
     * @param d the previous DraggableNode to be added
     */
    public void addPrev(DraggableNode d) {
        this.prev = d;
    }

    /**
     * void to add next
     * @param d the next DraggableNode to be added
     */
    public void addNext(DraggableNode d) {
        this.next = d;
    }

    /**
     * void to remove previous
     */
    public void removePrev() {
        this.prev = null;
    }

    /**
     * void to remove next
     */
    public void removeNext() {
        this.next = null;
    }

    @FXML
    private void initialize() {

        mControlOffsetX.set(100.0);
        mControlOffsetY.set(50.0);


        mControlDirectionX1.bind(new When(
                node_link.startXProperty().greaterThan(node_link.endXProperty()))
                .then(-1.0).otherwise(1.0));

        mControlDirectionX2.bind(new When(
                node_link.startXProperty().greaterThan(node_link.endXProperty()))
                .then(1.0).otherwise(-1.0));


        node_link.controlX1Property().bind(
                Bindings.add(
                        node_link.startXProperty(), mControlOffsetX.multiply(mControlDirectionX1)
                )
        );

        node_link.controlX2Property().bind(
                Bindings.add(
                        node_link.endXProperty(), mControlOffsetX.multiply(mControlDirectionX2)
                )
        );

        node_link.controlY1Property().bind(
                Bindings.add(
                        node_link.startYProperty(), mControlOffsetY.multiply(mControlDirectionY1)
                )
        );

        node_link.controlY2Property().bind(
                Bindings.add(
                        node_link.endYProperty(), mControlOffsetY.multiply(mControlDirectionY2)
                )
        );
    }


    /**
     * void to start drawing wire
     * @param startPoint the point from where it should start
     */
    public void setStart(Point2D startPoint) {

        node_link.setStartX(startPoint.getX());
        node_link.setStartY(startPoint.getY());
    }

    /**
     * void to end drawing wire
     * @param endPoint the point from where it should end
     */
    public void setEnd(Point2D endPoint) {

        node_link.setEndX(endPoint.getX());
        node_link.setEndY(endPoint.getY());
    }


    /**
     * void by which wires are drawn
     * @param source previous DraggableNode
     * @param target next DraggableNode
     */
    public void bindEnds(DraggableNode source, DraggableNode target) {
        source.out++;
        target.in++;

        int offset = 0;

        if (source.getOffsetChecker() && source.getOut() == 1) {
            offset = -10;
        }
        if(source.getOffsetChecker() &&  source.getOut() == 2 ) {
            offset = 10;
        }
        if(!source.getOffsetChecker()) {
            offset = 0;
        }

        int offset1 = 0;

        if (target.getInputOffsetChecker() && target.getIn() == 1) {
            offset1 = -10;
        }
        if(target.getInputOffsetChecker() && (target.getIn() == 2)) {
            offset1 = 10;
        }
        if(!target.getInputOffsetChecker()) {
            offset1 = 0;
        }
        int offset2 = 0;

        if (target.getBasicInputOffset() && target.getIn()%2 == 0) {
            offset2 = target.getIn() * 2;
            if (offset2 > 12)
                offset2 = 12;
        }
        else if (target.getBasicInputOffset())
        {
            offset2 = -(target.getIn() * 2);
            if (offset2 < -12)
                offset2 = -12;
        }
        this.sourceId = source.getId();
        this.targetId = target.getId();
        this.source = source;
        this.target = target;
        if (!sourceId.equals(targetId)) {
            node_link.startXProperty().bind(
                    Bindings.add(source.layoutXProperty(), (source.getWidth() / 2.0)));

            node_link.startYProperty().bind(
                    Bindings.add(source.layoutYProperty(), (source.getWidth() / 2.0) + offset));

            node_link.endXProperty().bind(
                    Bindings.add(target.layoutXProperty(), (source.getWidth() / 2.0)));

            if (target.getBasicInputOffset()) {
                node_link.endYProperty().bind(
                        Bindings.add(target.layoutYProperty(), (target.getWidth() / 2.0) + offset2));
            } else
            {
                node_link.endYProperty().bind(
                        Bindings.add(target.layoutYProperty(), (target.getWidth() / 2.0) + offset1));
            }

            source.registerLink(getId());
            target.registerLink(getId());
            source.addToListOut(getId());
            target.addToListIn(getId());


            if (target.getType() == DragIconType.splitter) {
                ((CubicCurve) this.getChildren().get(0)).setStroke(new Color(0, 0.44, 1, 1));
            }

            addWireAndGatesInCircuit(source, target);


        } else {
            source.out--;
            target.in--;
            this.setVisible(false);
        }
    }

    /**
     * void to paint them bright green when 1
     */
    public void repaint(){
        ((CubicCurve) this.getChildren().get(0)).setStroke(new Color(0.215, 0.999, 0, 1));
    }

    /**
     * void to paint dull green if 0
     */
    public void initialPaint() {
        ((CubicCurve) this.getChildren().get(0)).setStroke(Color.GREEN);
    }

    /**
     * getter method to get sourceId
     * @return string of sourceId
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * getter method to get targetId
     * @return string of targetId
     */
    public String getTargetId() {
        return targetId;
    }

    /**
     *void to draw wires and gates in the circuit
     * @param source previous DraggableNode
     * @param target next DraggableNode
     */
    public void addWireAndGatesInCircuit(DraggableNode source, DraggableNode target) {

        if (!source.myCircuit.getId().equals(target.myCircuit.getId()))
        {
        int OriginalTargetLevel;
        int newTargetLevel;
        int difference;


        OriginalTargetLevel = target.level;
        newTargetLevel = source.level + 1;
        difference = OriginalTargetLevel - newTargetLevel;
        Circuits toRemove = target.myCircuit;
        ArrayList<RightPaneChildren> targetCircuitComponents = target.myCircuit.getGUIComponents();

        for (int i = 0; i < targetCircuitComponents.size(); i++) {
            if (targetCircuitComponents.get(i).getTypee().equals("Node")) {
                ((DraggableNode) targetCircuitComponents.get(i)).level = ((DraggableNode) targetCircuitComponents.get(i)).level - difference;
                ((DraggableNode) targetCircuitComponents.get(i)).myCircuit = source.myCircuit;
            } else if (targetCircuitComponents.get(i).getTypee().equals("Wire"))
                ((NodeLink) targetCircuitComponents.get(i)).myCircuit = source.myCircuit;

            source.myCircuit.add(targetCircuitComponents.get(i));
        }
        toRemove.GUIComponents.clear();

        int j = 0;

        while (RootLayout.myCircuits.get(j).size() != 0) {
            j++;
        }
        RootLayout.myCircuits.remove(j);

    }

        source.myCircuit.add(this);
        myCircuit = source.myCircuit;
    }

}
