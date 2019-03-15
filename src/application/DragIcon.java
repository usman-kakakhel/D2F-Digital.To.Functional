/**
 * @author Joel Graff
 * last edit: 10 May 2018
 */
package application;

import java.io.IOException;
import java.io.Serializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.CubicCurve;

public class DragIcon extends AnchorPane implements Serializable {

    @FXML
    AnchorPane root_pane;

    private DragIconType mType = null;

    //constructor
    public DragIcon() {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/DragIcon.fxml")
        );

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void initialize() {
    }

    /**
     * void to relocate to a point
     * @param p point at where it will be relocated
     */
    public void relocateToPoint(Point2D p) {

        //relocates the object to a point that has been converted to
        //scene coordinates
        Point2D localCoords = getParent().sceneToLocal(p);

        relocate(
                (int) (localCoords.getX() - (getBoundsInLocal().getWidth() / 2)),
                (int) (localCoords.getY() - (getBoundsInLocal().getHeight() / 2))
        );
    }

    /**
     * getter method to get Type
     * @return returns mtype value
     */
    public DragIconType getType() {
        return mType;
    }

    /**
     * void to setType
     * @param type type which is to be set
     */
    public void setType(DragIconType type) {

        mType = type;

        getStyleClass().clear();
        getStyleClass().add("dragicon");

        //added because the cubic curve will persist into other icons
        if (this.getChildren().size() > 0)
            getChildren().clear();

        switch (mType) {

            case cubic_curve:
                getStyleClass().add("icon-add");

                Pane pane = new Pane();

                pane.setPrefWidth(64.0);
                pane.setPrefHeight(64.0);
                //pane.getStyleClass().add("icon-blue");
                pane.setLayoutX(0.0);
                pane.setLayoutY(0.0);

                CubicCurve curve = new CubicCurve();

                curve.setStartX(10.0);
                curve.setStartY(20.0);
                curve.setEndX(54.0);
                curve.setEndY(44.0);
                curve.setControlX1(64.0);
                curve.setControlY1(20.0);
                curve.setControlX2(0.0);
                curve.setControlY2(44.0);
                curve.getStyleClass().add("cubic-icon");

                pane.getChildren().add(curve);

                //r//oot_pane.
                getChildren().add(pane);

                break;

            case and:
                getStyleClass().add("icon-and");
                break;

            case or:
                getStyleClass().add("icon-or");
                break;

            case not:
                getStyleClass().add("icon-not");
                break;

            case xor:
                getStyleClass().add("icon-xor");
                break;

            case nand:
                getStyleClass().add("icon-nand");
                break;

            case nor:
                getStyleClass().add("icon-nor");
                break;

            case input:
                getStyleClass().add("icon-input");
                break;

            case clock:
                getStyleClass().add("icon-clock");
                break;

            case output:
                getStyleClass().add("icon-output");
                break;
            case splitter:
                getStyleClass().add("icon-splitter");
                break;

            case half_adder:
                getStyleClass().add("icon-half_adder");
                break;

            case full_adder:
                getStyleClass().add("icon-full_adder");
                break;

            case JK:
                getStyleClass().add("icon-JK");
                break;

            case SR:
                getStyleClass().add("icon-SR");
                break;

            case T:
                getStyleClass().add("icon-T");
                break;

            case D:
                getStyleClass().add("icon-D");
                break;

            default:
                break;
        }
    }
}
