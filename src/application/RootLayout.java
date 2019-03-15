/**
 * @author Talal Ahmad, Joel Graff, Muhammad Bilal, Mian Usman Naeem Kakakhel, Balaj Saleem
 * last edit: 10 May 2018
 */

package application;

import java.awt.*;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.*;

import javax.swing.*;


public class RootLayout extends AnchorPane implements Serializable {

    @FXML
    transient SplitPane base_pane;
    @FXML
    transient AnchorPane right_pane;
    @FXML
    transient VBox left_pane;
    @FXML
    transient HBox in_left_pane;
    @FXML
    transient VBox left_pane1;
    @FXML
    transient VBox left_pane2;
    @FXML
    transient VBox left_pane3;
    @FXML
    transient VBox left_pane4;
    @FXML
    transient MenuItem info_Menu;
    @FXML
    transient MenuItem comp_Menu;
    @FXML
    transient MenuItem about_Menu;
    @FXML
    transient MenuItem save_Item;
    @FXML
    transient MenuItem load_Item;
    @FXML
    transient MenuItem quit;
    @FXML
    TextField bool_Text;
    @FXML
    ImageView run;
    @FXML
    ImageView remove;
    @FXML
    ImageView select;
    @FXML
    ImageView label;
    @FXML
    ImageView btb;
    @FXML
    ImageView simplify;
    @FXML
    ImageView truthTable;
    @FXML
    ImageView edit;
    @FXML
    ImageView reset;
    @FXML
    AnchorPane toolBar;
    @FXML
    TitledPane memory_TitledPane;
    @FXML
    TitledPane adders_TitledPane;
    @FXML
    TitledPane basicGates_TitledPane;
    @FXML
    TitledPane prop_TitledPane;
    @FXML
    TitledPane labl_TitledPane;
    @FXML
    TextArea label_Text;

    private DragIcon mDragOverIcon = null;
    boolean boolVisibility;
    boolean toolBarVisibility;
    static boolean runSelected;
    static boolean selectSelected;
    boolean btbSelected;
    boolean labelSelected;
    static boolean removeSelected;
    static boolean bulbSelected;
    static String labelText = "";
    int count = 0;
    public static ArrayList<Circuits> myCircuits = new ArrayList<>();
    static double x;
    static double y;
    static boolean botCheck = false;

    private EventHandler<DragEvent> mIconDragOverRoot = null;
    private EventHandler<DragEvent> mIconDragDropped = null;
    private EventHandler<DragEvent> mIconDragOverRightPane = null;
    //public static DraggableNode jani = null;
    public static DraggableNode jani;

    public RootLayout() {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/RootLayout.fxml")
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
        bool_Text.setStyle("-fx-prompt-text-fill: #ff0000;");
        bool_Text.setStyle("-fx-text-inner-color: red;");
        bool_Text.setStyle("-fx-control-inner-background: #222222;");
        label_Text.setStyle("-fx-prompt-text-fill: #959595;");
        label_Text.setStyle("-fx-text-inner-color: white;");
        label_Text.setStyle("-fx-control-inner-background: #222222;");

        boolVisibility = true;
        toolBarVisibility = true;
        selectSelected = false;
        btbSelected = false;
        removeSelected = false;
        labelSelected = false;
        runSelected = false;
        bulbSelected = false;
        truthTable.setOpacity(0.2);

        simplify.setOpacity(0.2);


        save_Item.setOnAction(new SaveItemListener());

        load_Item.setOnAction(new LoadItemListener());


        info_Menu.setOnAction(new InfoMenuListener());
        comp_Menu.setOnAction(new CompMenuListener());
        about_Menu.setOnAction(new AboutMenuListener());
        quit.setOnAction(new QuitItemListener());

        right_pane.addEventHandler(MouseEvent.MOUSE_MOVED, new bulbNodeClicked());

        right_pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, new mouseDraggedOnRightPaneListener());

        run.addEventHandler(MouseEvent.MOUSE_CLICKED, new runButtonClickedListener());

        run.addEventHandler(MouseEvent.MOUSE_ENTERED, new runButtonHoverListener());

        run.addEventHandler(MouseEvent.MOUSE_EXITED, new runButtonExitedListener());

        remove.addEventHandler(MouseEvent.MOUSE_CLICKED, new removeClickedListener());

        remove.addEventHandler(MouseEvent.MOUSE_ENTERED, new removeMouseEntered());

        remove.addEventHandler(MouseEvent.MOUSE_EXITED, new removeMouseExited());

        select.addEventHandler(MouseEvent.MOUSE_CLICKED, new selectClickedListener());

        select.addEventHandler(MouseEvent.MOUSE_ENTERED, new selectMouseEntered());

        select.addEventHandler(MouseEvent.MOUSE_EXITED, new selectMouseExited());

        label.addEventHandler(MouseEvent.MOUSE_CLICKED, new labelClickedListener());

        label.addEventHandler(MouseEvent.MOUSE_ENTERED, new labelMouseEntered());

        label.addEventHandler(MouseEvent.MOUSE_EXITED, new labelMouseExited());

        btb.addEventHandler(MouseEvent.MOUSE_CLICKED, new btbMouseClicked());

        btb.addEventHandler(MouseEvent.MOUSE_ENTERED, new btbMouseEnter());

        btb.addEventHandler(MouseEvent.MOUSE_EXITED, new btbMouseExit());

        simplify.addEventHandler(MouseEvent.MOUSE_CLICKED, new simplifyMouseClicked());

        simplify.addEventHandler(MouseEvent.MOUSE_ENTERED, new simplifyMouseEnter());

        simplify.addEventHandler(MouseEvent.MOUSE_EXITED, new simplifyMouseExit());

        edit.addEventHandler(MouseEvent.MOUSE_CLICKED, new editMouseClicked());

        edit.addEventHandler(MouseEvent.MOUSE_ENTERED, new editMouseEnter());

        edit.addEventHandler(MouseEvent.MOUSE_EXITED, new editMouseExit());

        truthTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new truthTableMouseClicked());

        truthTable.addEventHandler(MouseEvent.MOUSE_ENTERED, new truthTableMouseEnter());

        truthTable.addEventHandler(MouseEvent.MOUSE_EXITED, new truthTableMouseExit());

        reset.addEventHandler(MouseEvent.MOUSE_ENTERED, new resetMouseEnter());

        reset.addEventHandler(MouseEvent.MOUSE_EXITED, new resetMouseExit());

        reset.addEventHandler(MouseEvent.MOUSE_CLICKED, new resetMouseClicked());

        bool_Text.setOnKeyPressed(new booleanDrawClicked());

        base_pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (jani != null && jani.getType() == DragIconType.clock) {
                    if (ke.getCode().equals(KeyCode.C)) {
                        if (jani.botCheck) {
                            jani.myCircuit.initialise();
                            jani.myCircuit.createCircuit();
                            RootLayout.this.requestLayout();
                            try {
                                Thread.sleep(jani.delaySeconds);
                            } catch (InterruptedException ie) {
                                ie.printStackTrace();
                            }
                            jani.state = !jani.state;
                        }
                    }

                }
            }
        });


        //this.addEventHandler();

        x = right_pane.getMinWidth();
        y = right_pane.getMinHeight();
        //Add one icon that will be used for the drag-drop process
        //This is added as a child to the root anchorpane so it can be visible
        //on both sides of the split pane.
        mDragOverIcon = new DragIcon();

        mDragOverIcon.setVisible(false);
        mDragOverIcon.setOpacity(0.65);
        getChildren().add(mDragOverIcon);

        int k = 0;
        //populate left pane with multiple vboxes
        //this is basic gates vbox
        for (int j = 0; j < 5; j++) {
            in_left_pane = new HBox();
            in_left_pane.setPadding(new Insets(10, 50, 0, 10));
            in_left_pane.setSpacing(40);

            left_pane.getChildren().add(in_left_pane);

            for (int i = 0; i < 2; i++) {
                DragIcon icn = new DragIcon();
                k++;
                addDragDetection(icn);

                icn.setType(DragIconType.values()[k]);
                in_left_pane.getChildren().add(icn);

            }
        }
        //memory v box
        for (int j = 0; j < 2; j++) {
            in_left_pane = new HBox();
            in_left_pane.setPadding(new Insets(10, 50, 0, 10));
            in_left_pane.setSpacing(40);

            left_pane1.getChildren().add(in_left_pane);

            for (int i = 0; i < 2; i++) {
                DragIcon icn = new DragIcon();
                k++;
                addDragDetection(icn);

                icn.setType(DragIconType.values()[k]);
                in_left_pane.getChildren().add(icn);

            }
        }
        //adders vbox
        in_left_pane = new HBox();
        in_left_pane.setPadding(new Insets(10, 50, 0, 10));
        in_left_pane.setSpacing(40);

        left_pane2.getChildren().add(in_left_pane);

        for (int i = 0; i < 2; i++) {
            DragIcon icn = new DragIcon();
            k++;
            addDragDetection(icn);

            icn.setType(DragIconType.values()[k]);
            in_left_pane.getChildren().add(icn);

        }

        makePropLablPane();
        buildDragHandlers();
    }

    /**
     * void to make properties and label panes
     */
    public void makePropLablPane() {
        prop_TitledPane.setCollapsible(true);
        prop_TitledPane.setExpanded(true);
        labl_TitledPane.setCollapsible(true);
        labl_TitledPane.setExpanded(true);
        prop_TitledPane.setExpanded(false);
        prop_TitledPane.setCollapsible(false);
        labl_TitledPane.setExpanded(false);
        labl_TitledPane.setCollapsible(false);
    }

    /**
     * the panes with vertical boxes in the left side anchor
     */
    public void closeSidePanes() {
        basicGates_TitledPane.setExpanded(false);
        basicGates_TitledPane.setCollapsible(false);
        memory_TitledPane.setExpanded(false);
        memory_TitledPane.setCollapsible(false);
        adders_TitledPane.setExpanded(false);
        adders_TitledPane.setCollapsible(false);
        prop_TitledPane.setCollapsible(true);
        prop_TitledPane.setExpanded(true);
        labl_TitledPane.setCollapsible(true);
        labl_TitledPane.setExpanded(true);
    }

    /**
     * void to open side panes
     */
    public void openSidePanes() {
        basicGates_TitledPane.setCollapsible(true);
        basicGates_TitledPane.setExpanded(true);
        memory_TitledPane.setCollapsible(true);
        memory_TitledPane.setExpanded(true);
        adders_TitledPane.setCollapsible(true);
        adders_TitledPane.setExpanded(true);
        prop_TitledPane.setExpanded(false);
        prop_TitledPane.setCollapsible(false);
        labl_TitledPane.setExpanded(false);
        labl_TitledPane.setCollapsible(false);
    }


    /**
     * void to detect drag
     *
     * @param dragIcon the DragIcon on which action will be performed
     */
    private void addDragDetection(DragIcon dragIcon) {

        dragIcon.setOnDragDetected(new DragIconDraggedListener());
    }

    /**
     * void to build drag handlers
     */
    private void buildDragHandlers() {

        //drag over transition to move widget form left pane to right pane
        mIconDragOverRoot = new DragOverRootListener();

        mIconDragOverRightPane = new DragOverRightPaneListener();

        mIconDragDropped = new DragDroppedListener();

        this.setOnDragDone(new OnDragDoneListener());
    }


    //event handeling inner classes
    //save button listener
    public class SaveItemListener implements Serializable, EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                saveAsItemClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * void to put actions when Save As  is clicked
         *
         * @throws IOException exception
         */
        public void saveAsItemClick() throws IOException {
            labelText = label_Text.getText();
            Stage window = new Stage();

            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("SAVE AS");
            window.setResizable(false);

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");

            D2F a = new D2F();
            a.items = getChildrenList();
            a.turnIntoSerial();
            FileStorage saveAsFile = new FileStorage(fileChooser.showSaveDialog(window), a);

            saveAsFile.save();
        }

        /**
         * method to get Children
         *
         * @return RightChildren of Array List
         */
        public ArrayList<RightPaneChildren> getChildrenList() {
            ArrayList<RightPaneChildren> back = new ArrayList<>();
            Object list[] = RootLayout.this.right_pane.getChildren().toArray();
            for (int i = 0; i < list.length; i++) {
                back.add((RightPaneChildren) list[i]);
            }
            return back;
        }

    }

    /**
     * void to remove from circuit
     */
    public void removeCircuit() {
        for (int i = 0; i < myCircuits.size(); i++) {
            if (myCircuits.get(i).getSize() < 0) {
                myCircuits.remove(i);
            }
        }
    }


    public class QuitItemListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            quitClick();
        }

        /**
         * void to exit on quit clicked
         */
        public void quitClick() {
            System.exit(0);
        }
    }

    //load item listener
    public class LoadItemListener implements Serializable, EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                try {
                    loadAsItemClick();
                } catch (AWTException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * void to perform action on load click item
         *
         * @throws IOException  exception
         * @throws AWTException exception
         */
        public void loadAsItemClick() throws IOException, AWTException {

            Stage window = new Stage();
            //JFileChooser chooser = new JFileChooser();

            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Load");
            window.setResizable(false);

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            D2F a = new D2F();
            FileStorage loadAsFile = new FileStorage(fileChooser.showOpenDialog(window), a);
            D2F mySaveFile = loadAsFile.load();
            if (mySaveFile != null)
                removeAndAddSave(mySaveFile);
        }


        /**
         * void to load file
         *
         * @param mySaveFile the file which is to be loaded
         * @throws AWTException exception
         */
        public void removeAndAddSave(D2F mySaveFile) throws AWTException {
            label_Text.setText(mySaveFile.labelText);
            x = mySaveFile.x;
            y = mySaveFile.y;
            right_pane.setMinWidth(x);
            right_pane.setMinHeight(y);
            Circuits newCircuit;
            Robot bot = new Robot();
            if (RootLayout.this.right_pane.getChildren() != null) {
                RootLayout.this.right_pane.getChildren().remove(0, RootLayout.this.right_pane.getChildren().size());
                myCircuits.clear();
            }
            for (int j = 0; j < mySaveFile.items.size(); j++) {
                if (mySaveFile.items.get(j).getTypee().equals("Node")) {
                    myCircuits.add(new Circuits());
                    DraggableNode a = new DraggableNode();



                    a.setType(((D2F.NodeInfo) mySaveFile.items.get(j)).mType);
                    a.setId(((D2F.NodeInfo) mySaveFile.items.get(j)).id);
//                    a.in = ((D2F.NodeInfo) mySaveFile.items.get(j)).in;
//                    a.out = ((D2F.NodeInfo) mySaveFile.items.get(j)).out;
                    a.inValue = ((D2F.NodeInfo) mySaveFile.items.get(j)).inValue;
                    a.xLoc = ((D2F.NodeInfo) mySaveFile.items.get(j)).xLoc;
                    a.yLoc = ((D2F.NodeInfo) mySaveFile.items.get(j)).yLoc;
                    a.inValue = ((D2F.NodeInfo) mySaveFile.items.get(j)).inValue;
                    a.offsetChecker = ((D2F.NodeInfo) mySaveFile.items.get(j)).offsetChecker;
                    a.inputOffsetChecker = ((D2F.NodeInfo) mySaveFile.items.get(j)).inputOffsetChecker;
                    a.basicInputOffset = ((D2F.NodeInfo) mySaveFile.items.get(j)).basicInputOffset;
                    a.level = ((D2F.NodeInfo) mySaveFile.items.get(j)).level;
                    a.state = ((D2F.NodeInfo) mySaveFile.items.get(j)).state;
                    if (a.getType() == DragIconType.clock)
                        a.addEventHandler(MouseEvent.MOUSE_RELEASED, new clockNodeClicked());
                    else
                        a.addEventHandler(MouseEvent.MOUSE_RELEASED, new dragNodeClicked());
                    myCircuits.remove(myCircuits.size() - 1);
                    //check for a circuit in arrayList, if present dont make a new one if not do so.
                    boolean check = false;
                    for (int i = 0; i < myCircuits.size(); i++) {
                        if (myCircuits.get(i).getId().equals(((D2F.NodeInfo) mySaveFile.items.get(j)).myCircuitId)) {
                            check = true;
                            a.myCircuit = myCircuits.get(i);
                            myCircuits.get(i).add(a);
                        }

                    }
                    if (!check) {
                        newCircuit = new Circuits();
                        newCircuit.setId(((D2F.NodeInfo) mySaveFile.items.get(j)).myCircuitId);
                        myCircuits.add(newCircuit);
                        a.myCircuit = newCircuit;
                        myCircuits.get(myCircuits.size() - 1).add(a);
                    }


                    RootLayout.this.right_pane.getChildren().add(a);
                    a.resizeRelocate(a.xLoc, a.yLoc, 100, 100);
                    a.setmLinkIds(((D2F.NodeInfo) mySaveFile.items.get(j)).mLinkIds);
                    a.setmLinkIdsOut(((D2F.NodeInfo) mySaveFile.items.get(j)).mLinkIdsOut);
                    a.setmLinkIdsIn(((D2F.NodeInfo) mySaveFile.items.get(j)).mLinkIdsIn);
                }

            }


            for (int j = 0; j < mySaveFile.items.size(); j++) {

                if ((mySaveFile.items.get(j).getTypee().equals("Wire"))) {

                    for (Object ch : right_pane.getChildren().toArray()) {

                        if ((((RightPaneChildren) ch).getTypee().equals("Node")) &&
                                (((D2F.WireInfo) mySaveFile.items.get(j)).prevId.equals(((DraggableNode) ch).getId()))) {

                            for (Object ch2 : right_pane.getChildren().toArray()) {

                                if ((((RightPaneChildren) ch2).getTypee().equals("Node"))
                                        && (((D2F.WireInfo) mySaveFile.items.get(j)).nextId.equals(((DraggableNode) ch2).getId()))) {
                                    NodeLink a = new NodeLink();

                                    a.setId(((D2F.WireInfo) mySaveFile.items.get(j)).id);

                                    a.bindEnds(((DraggableNode) ch), ((DraggableNode) ch2));
                                    RootLayout.this.right_pane.getChildren().add(0, a);
                                }
                            }
                        }
                    }
                }
            }
        }

    }


    //info menu istener
    public class InfoMenuListener implements Serializable, EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                infoMenuClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        /**
         * listener for info menu click
         *
         * @throws IOException exception
         */
        public void infoMenuClick() throws IOException {

            Stage window = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/InfoLayout.fxml"));
            Scene scene = new Scene(root, 1274, 600);
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Help");
            window.setScene(scene);
            window.setResizable(false);
            window.showAndWait();
        }
    }

    //component menu help listener
    public class CompMenuListener implements Serializable, EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                compMenuClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        /**
         * Listener For Component Menu
         *
         * @throws IOException exception
         */
        public void compMenuClick() throws IOException {

            Stage window = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/CompLayout.fxml"));
            Scene scene = new Scene(root, 1184, 600);
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Components");
            window.setScene(scene);
            window.setResizable(false);
            window.showAndWait();
        }
    }


    //about menu listener
    public class AboutMenuListener implements Serializable, EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            try {
                aboutMenuClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * listener for about menu click
         *
         * @throws IOException exception
         */
        public void aboutMenuClick() throws IOException {

            Stage window = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/AboutLayout.fxml"));
            Scene scene = new Scene(root, 1000, 466);
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("About");
            window.setScene(scene);
            window.setResizable(false);
            window.showAndWait();
        }
    }


    //DragIcon Dragged Listetner
    public class DragIconDraggedListener implements Serializable, EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {

            // set drag event handlers on their respective objects
            base_pane.setOnDragOver(mIconDragOverRoot);
            right_pane.setOnDragOver(mIconDragOverRightPane);
            right_pane.setOnDragDropped(mIconDragDropped);

            // get a reference to the clicked DragIcon object
            DragIcon icn = (DragIcon) event.getSource();

            //begin drag ops
            mDragOverIcon.setType(icn.getType());
            mDragOverIcon.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));

            ClipboardContent content = new ClipboardContent();
            DragContainer container = new DragContainer();

            container.addData("type", mDragOverIcon.getType().toString());
            content.put(DragContainer.AddNode, container);

            mDragOverIcon.startDragAndDrop(TransferMode.ANY).setContent(content);
            mDragOverIcon.setVisible(true);
            mDragOverIcon.setMouseTransparent(true);
            event.consume();
        }
    }

    //DragOverRootListener
    public class DragOverRootListener implements Serializable, EventHandler<DragEvent> {

        @Override
        public void handle(DragEvent event) {

            Point2D p = right_pane.sceneToLocal(event.getSceneX(), event.getSceneY());

            //turn on transfer mode and track in the right-pane's context
            //if (and only if) the mouse cursor falls within the right pane's bounds.
            if (!right_pane.boundsInLocalProperty().get().contains(p)) {

                event.acceptTransferModes(TransferMode.ANY);
                mDragOverIcon.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                return;
            }

            event.consume();
        }
    }

    //DragOverRightPaneListener
    public class DragOverRightPaneListener implements Serializable, EventHandler<DragEvent> {

        @Override
        public void handle(DragEvent event) {

            event.acceptTransferModes(TransferMode.ANY);

            //convert the mouse coordinates to scene coordinates,
            //then convert back to coordinates that are relative to
            //the parent of mDragIcon.  Since mDragIcon is a child of the root
            //pane, coodinates must be in the root pane's coordinate system to work
            //properly.
            mDragOverIcon.relocateToPoint(
                    new Point2D(event.getSceneX(), event.getSceneY())
            );
            event.consume();
        }
    }

    //DragDroppedListener
    public class DragDroppedListener implements Serializable, EventHandler<DragEvent> {

        @Override
        public void handle(DragEvent event) {

            DragContainer container =
                    (DragContainer) event.getDragboard().getContent(DragContainer.AddNode);

            container.addData("scene_coords",
                    new Point2D(event.getSceneX(), event.getSceneY()));

            ClipboardContent content = new ClipboardContent();
            content.put(DragContainer.AddNode, container);

            event.getDragboard().setContent(content);
            event.setDropCompleted(true);
        }
    }

    //OnDragDoneListener
    public class OnDragDoneListener implements Serializable, EventHandler<DragEvent> {

        @Override
        public void handle(DragEvent event) {

            right_pane.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverRightPane);
            right_pane.removeEventHandler(DragEvent.DRAG_DROPPED, mIconDragDropped);
            base_pane.removeEventHandler(DragEvent.DRAG_OVER, mIconDragOverRoot);

            mDragOverIcon.setVisible(false);

            //Create node drag operation
            DragContainer container =
                    (DragContainer) event.getDragboard().getContent(DragContainer.AddNode);

            if (container != null) {
                if (container.getValue("scene_coords") != null) {

                    if (container.getValue("type").equals(DragIconType.cubic_curve.toString())) {
                        CubicCurveDemo curve = new CubicCurveDemo();

                        right_pane.getChildren().add(curve);

                        Point2D cursorPoint = container.getValue("scene_coords");

                        curve.relocateToPoint(
                                new Point2D(cursorPoint.getX() - 32, cursorPoint.getY() - 32)
                        );
                    } else {

                        DraggableNode node = new DraggableNode();
                        myCircuits.add(new Circuits());


                        node.setType(DragIconType.valueOf(container.getValue("type")));
                        right_pane.getChildren().add(node);

                        Point2D cursorPoint = container.getValue("scene_coords");

                        node.relocateToPoint(
                                new Point2D(cursorPoint.getX() - 32, cursorPoint.getY() - 32)
                        );

                        if (node.getType() == DragIconType.clock)
                            node.addEventHandler(MouseEvent.MOUSE_RELEASED, new clockNodeClicked());
                        else
                            node.addEventHandler(MouseEvent.MOUSE_RELEASED, new dragNodeClicked());


                    }
                }
            }
				/*
				//Move node drag operation
				container =
						(DragContainer) event.getDragboard().getContent(DragContainer.DragNode);

				if (container != null) {
					if (container.getValue("type") != null)
						System.out.println ("Moved node " + container.getValue("type"));
				}
				*/

            //AddLink drag operation
            container =
                    (DragContainer) event.getDragboard().getContent(DragContainer.AddLink);

            if (container != null) {

                //bind the ends of our link to the nodes whose id's are stored in the drag container
                String sourceId = container.getValue("source");
                String targetId = container.getValue("target");

                if (sourceId != null && targetId != null) {

                    NodeLink link = new NodeLink();

                    //add our link at the top of the rendering order so it's rendered first
                    right_pane.getChildren().add(0, link);

                    DraggableNode source = null;
                    DraggableNode target = null;

                    for (Node n : right_pane.getChildren()) {

                        if (n.getId() == null)
                            continue;

                        if (n.getId().equals(sourceId))
                            source = (DraggableNode) n;

                        if (n.getId().equals(targetId))
                            target = (DraggableNode) n;

                    }

                    if (source != null && target != null)
                        link.bindEnds(source, target);
                }

            }

            event.consume();
        }
    }

    //run button clicked listener
    public class runButtonClickedListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            try {
                boolean check = false;
                for (int i = 0; i < myCircuits.size(); i++) {

                    if (myCircuits.get(i).circuitCheck())
                        check = true;
                }
                if (check)
                    run();

            } catch (IOException e) {
                e.printStackTrace();
            }
            event.consume();
        }

        /**
         * run menu action listener
         *
         * @throws IOException exception
         */
        public void run() throws IOException {
            if (removeSelected) {
                (new removeClickedListener()).remove();
                (new removeClickedListener()).removeButtonClick();
            }
            if (!selectSelected)
                (new selectClickedListener()).select();
            toolBarVisibility = !toolBarVisibility;

            if (toolBarVisibility == false) {
                toolBar.setVisible(true);
                runSelected = true;
                prop_TitledPane.setVisible(false);
            } else {
                toolBar.setVisible(false);
            }
            buildCircuit();
        }

        /**
         * void building circuits
         */
        public void buildCircuit() {

            for (int i = 0; i < myCircuits.size(); i++) {
                if (myCircuits.get(i).circuitCheck())
                    myCircuits.get(i).createCircuit();
            }
        }

    }

    public class runButtonHoverListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * run button hover listener
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            Image image = new Image("/Image/runHover.png", true);
            run.setImage(image);
        }
    }


    public class runButtonExitedListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * runbuttonhover exited listener
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            Image image = new Image("/Image/run.png", true);
            run.setImage(image);
        }
    }

    public class removeClickedListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                if (selectSelected) {
                    (new selectClickedListener()).select();
                }
                if (labelSelected) {
                    (new labelClickedListener()).label();
                }
                remove();
                removeButtonClick();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        /**
         * removeClickedListener
         */
        private void removeButtonClick() {
            for (Object d : right_pane.getChildren().toArray()) {
                if (((RightPaneChildren) d).getTypee().equals("Node")) {
                    ((DraggableNode) d).toggleRemoveOpacity();
                    ((DraggableNode) d).toggleCrossPaneVisible();
                }
            }
        }

        /**
         * void to remove
         *
         * @throws IOException exception
         */
        public void remove() throws IOException {
            if (!removeSelected) {
                removeSelected = !removeSelected;
                Image image1 = new Image("/Image/removeSelected.png", true);
                remove.setImage(image1);
                RootLayout.this.closeSidePanes();
            } else {
                removeSelected = !removeSelected;
                Image image2 = new Image("/Image/remove.png", true);
                removeCircuit();
                makePropLablPane();
                remove.setImage(image2);
                RootLayout.this.openSidePanes();
            }
        }
    }

    public class removeMouseEntered implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * remove mouse entered
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            if (!removeSelected) {
                Image image = new Image("/Image/removeHover.png", true);
                remove.setImage(image);
            }
        }
    }

    public class removeMouseExited implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {


            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * remove mouse exited
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            if (!removeSelected) {
                Image image = new Image("/Image/remove.png", true);
                remove.setImage(image);
            }
        }
    }

    public class selectClickedListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                if (removeSelected) {
                    (new removeClickedListener()).remove();
                    (new removeClickedListener()).removeButtonClick();
                }
                if (labelSelected) {
                    (new labelClickedListener()).label();
                }
                select();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * select clicked listener
         *
         * @throws IOException exceptions
         */
        public void select() throws IOException {
            if (!selectSelected) {
                selectSelected = !selectSelected;
                Image image1 = new Image("/Image/selectSecondary.png", true);
                select.setImage(image1);
                prop_TitledPane.setVisible(true);
                RootLayout.this.closeSidePanes();
            } else {
                selectSelected = !selectSelected;
                Image image2 = new Image("/Image/select.png", true);
                select.setImage(image2);
                prop_TitledPane.setVisible(false);
                if (jani != null)
                    jani.removeCircle();
                RootLayout.this.openSidePanes();
            }
            for (Object d : right_pane.getChildren().toArray()) {
                if (((RightPaneChildren) d).getTypee().equals("Node")) {
                    ((DraggableNode) d).setLabelDrag();
                }
            }
        }
    }

    public class selectMouseEntered implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * select mouse entered
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            if (!selectSelected) {
                Image image = new Image("/Image/selectHover.png", true);
                select.setImage(image);
            }
        }
    }

    public class selectMouseExited implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * selectMouseExited
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            if (!selectSelected) {
                Image image = new Image("/Image/select.png", true);
                select.setImage(image);
            }
        }
    }

    public class labelClickedListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                if (removeSelected) {
                    (new removeClickedListener()).remove();
                    (new removeClickedListener()).removeButtonClick();
                }
                if (selectSelected) {
                    (new selectClickedListener()).select();
                }
                label();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * labelClicked listener
         *
         * @throws IOException exception
         */
        public void label() throws IOException {
            if (!labelSelected) {
                labelSelected = !labelSelected;
                Image image = new Image("/Image/labelSelected.png", true);
                label.setImage(image);
                labl_TitledPane.setVisible(true);
                RootLayout.this.closeSidePanes();
                prop_TitledPane.setExpanded(false);
                prop_TitledPane.setCollapsible(false);
                prop_TitledPane.setVisible(true);
            } else {
                labelSelected = !labelSelected;
                Image image = new Image("/Image/label.png", true);
                label.setImage(image);
                labl_TitledPane.setVisible(false);
                prop_TitledPane.setVisible(false);
                RootLayout.this.openSidePanes();
            }
        }
    }

    public class labelMouseEntered implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * label mouse entered
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            if (!labelSelected) {
                Image image = new Image("/Image/labelHover.png", true);
                label.setImage(image);
            }
        }
    }

    public class labelMouseExited implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * label mouse exited
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            if (!labelSelected) {
                Image image = new Image("/Image/label.png", true);
                label.setImage(image);
            }
        }
    }

    public class btbMouseClicked implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                btb();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        /**
         * btbMouseClicked
         *
         * @throws IOException exception
         */
        public void btb() throws IOException {
            boolVisibility = !boolVisibility;

            if (boolVisibility == false) {
                bool_Text.setVisible(true);
            } else {
                bool_Text.setVisible(false);
            }
        }
    }

    public class btbMouseEnter implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * BTB Mouse Enter
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            Image image = new Image("/Image/btbHover.png", true);
            btb.setImage(image);
        }
    }

    public class btbMouseExit implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        /**
         * BTB Mouse Exit
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            Image image = new Image("/Image/btb.png", true);
            btb.setImage(image);
        }
    }

    public class simplifyMouseClicked implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                simplify();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Simplify Mouse Clicked
         *
         * @throws IOException exception
         */
        public void simplify() throws IOException {
            if (jani.getType() == DragIconType.output && jani.myCircuit.truthTableCheck()) {

                BooleanAlgebra mySimplifier = new BooleanAlgebra(((Bulb) jani.getComponent()).getName());
                (new booleanDrawClicked()).drawCircuits(mySimplifier.getReducedStatement());
            }
        }
    }

    public class simplifyMouseEnter implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Simplify Mouse Enter

        /**
         * Simplify Mouse Enter
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            Image image = new Image("/Image/scHover.png", true);
            simplify.setImage(image);
        }
    }

    public class simplifyMouseExit implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Simplify Mouse Exit
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            Image image = new Image("/Image/sc.png", true);
            simplify.setImage(image);
        }
    }

    public class editMouseClicked implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {


            try {
                if (selectSelected)
                    (new selectClickedListener()).select();
                edit();
                for (Circuits a : myCircuits) {
                    a.initialise();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            event.consume();
        }

        /**
         * Edit Mouse Clicked
         *
         * @throws IOException exception
         */
        public void edit() throws IOException {
            toolBarVisibility = !toolBarVisibility;
            toolBar.setVisible(false);
            runSelected = false;
        }
    }

    public class editMouseEnter implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Edit Mouse Enter
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            Image image = new Image("/Image/editHover.png", true);
            edit.setImage(image);
        }
    }

    public class editMouseExit implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Edit Mouse Exit
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            Image image = new Image("/Image/edit.png", true);
            edit.setImage(image);
        }
    }

    public class truthTableMouseClicked implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                truthTableItemClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        /**
         * Truth Table Mouse Clicked
         *
         * @throws IOException exception
         */
        public void truthTableItemClick() throws IOException {
            if ((jani.myCircuit.circuitCheck()) && jani.myCircuit.truthTableCheck()) {
                String truthTable;

                if (jani.getType() == DragIconType.output)
                    truthTable = ((Bulb) jani.getComponent()).getName();
                else if (jani.getType() == DragIconType.input)
                    truthTable = ((Switch) jani.getComponent()).getName();
                else
                    truthTable = ((BasicGates) jani.getComponent()).getName();

                TruthTable myTable = new TruthTable();
                myTable.createTable(truthTable);
                String original = myTable.toString();

                int totalColumns = myTable.getRowSize();
                int totalRows = myTable.getColumnSize();
                int end = 0;
                int start = 7;

                ScrollPane sp = new ScrollPane();
                Stage window = new Stage();
                StackPane root = new StackPane();
                HBox hBox = new HBox();
                hBox.setSpacing(-1);

                for (int i = 0; i <= totalColumns; i++) {
                    VBox vb = new VBox();
                    Label lb;
                    if (i == totalColumns - 1) {
                        lb = new Label("  OUT");
                    } else
                        lb = new Label("     " + (char) (i + 65)); //Headings

                    lb.setStyle("-fx-font-weight: bold;");
                    lb.setStyle("-fx-text-fill: #38E61A");
                    vb.getChildren().add(lb);
                    end = original.indexOf(']');


                    if (end != -1) {
                        String binary = original.substring(start, end);
                        for (int j = 0; j <= binary.length(); j += 3) //list men sare binary a gae
                        {

                            Label nLbl = new Label(binary.charAt(j) + "");
                            nLbl.setStyle("-fx-text-fill: #FFFFFF;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-padding: 15;" +
                                    "-fx-border-style: solid inside;" +
                                    "-fx-border-width: 1;" +
                                    "-fx-border-color: #6d6c6c;");
                            vb.getChildren().add(nLbl);
                        }
                        original = original.substring(end + 2);
                        vb.setSpacing(0);
                        vb.setStyle("-fx-padding: 0;" +
                                "-fx-border-style: solid inside;" +
                                "-fx-border-width: 0;" +
                                "-fx-border-insets: 0;" +
                                "-fx-border-radius: 0;");
                        hBox.getChildren().add(vb);
                    }
                }
                hBox.setStyle("-fx-background-color: #222222;" + "-fx-border-insets: 20;");
                sp.setContent(hBox);
                int pad = totalColumns * 36;
                pad = 500 - pad;
                pad = pad / 5;
                sp.setPadding(new Insets(30, pad, 0, pad * 2.5));
                sp.setStyle("-fx-background: #222222;");
                root.getChildren().add(sp);
                int height = totalRows * 30;

                if (totalRows * 30 > 400)
                    height = 400;
                window.setScene(new Scene(root, 500, 500));//totalColumns * 30, height
                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("TruthTables");
                window.setResizable(false);
                window.showAndWait();
            }

        }
    }

    public class truthTableMouseEnter implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        /**
         * Truth Table Hover Enter
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            Image image = new Image("/Image/ttHover.png", true);
            truthTable.setImage(image);
        }
    }

    public class truthTableMouseExit implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Truth Table Hover Exit
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            Image image = new Image("/Image/tt.png", true);
            truthTable.setImage(image);
        }
    }

    public class resetMouseClicked implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            right_pane.getChildren().clear();
            myCircuits.clear();
            event.consume();
        }
    }

    public class resetMouseEnter implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Reset Mouse Hover Enter
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            Image image = new Image("/Image/resetHover.png", true);
            reset.setImage(image);
        }
    }

    public class resetMouseExit implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                hover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Reset Mouse Hover Exit
         *
         * @throws IOException exception
         */
        public void hover() throws IOException {
            Image image = new Image("/Image/reset.png", true);
            reset.setImage(image);
        }
    }

    public class dragNodeClicked implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                if (selectSelected) {
                    left_pane3.getChildren().clear();
                    if (jani.getType() != DragIconType.input && jani.getType() != DragIconType.output && jani.getType() != DragIconType.not
                            && jani.getType() != DragIconType.D && jani.getType() != DragIconType.T && jani.getType() != DragIconType.SR
                            && jani.getType() != DragIconType.JK && jani.getType() != DragIconType.full_adder
                            && jani.getType() != DragIconType.half_adder && jani.getType() != DragIconType.splitter)
                        onClicked();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * when draggable node clicked
         *
         * @throws IOException exception
         */
        public void onClicked() throws IOException {
            HBox prop = new HBox();
            prop.setSpacing(20);
            prop.setPrefSize(300, 50);
            prop.setPadding(new Insets(15, 0, 0, 10));

            Label label = new Label("Number Of Inputs:       ");
            label.setStyle("-fx-font-size: 14px;" + "-fx-text-fill: #FFFFFF;");

            Button set = new Button("Set");

            TextField inputNum = new TextField(jani.inValue + "");
            inputNum.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        inputNum.setText(newValue.replaceAll("\\D", ""));
                    }
                }
            });

            inputNum.setStyle("-fx-text-fill: #000000");
            inputNum.setPrefSize(50, 14);

            prop.getChildren().add(label);
            prop.getChildren().add(set);
            prop.getChildren().add(inputNum);
            left_pane3.getChildren().add(prop);

            inputNum.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode().equals(KeyCode.ENTER)) {
                        int input = 0;
                        input = Integer.parseInt(inputNum.getText());
                        jani.inValue = input;
                    }
                }
            });

            set.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    int input = 0;
                    input = Integer.parseInt(inputNum.getText());
                    jani.inValue = input;
                }
            });
        }
    }

    public class inputNodeClicked implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            try {
                if (selectSelected) {
                    left_pane3.getChildren().clear();
                    onClicked();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            event.consume();
        }

        /**
         * input node clicked
         *
         * @throws IOException exception
         */
        public void onClicked() throws IOException {
            HBox prop = new HBox();
            prop.setSpacing(95);
            prop.setPrefSize(300, 50);
            prop.setPadding(new Insets(15, 0, 0, 10));

            Label label = new Label("Toggle Input Value:");
            label.setStyle("-fx-font-size: 14px;" + "-fx-text-fill: #FFFFFF");

            Button toggle = new Button("0");
            toggle.setPrefSize(50, 14);
            prop.getChildren().add(label);
            prop.getChildren().add(toggle);
            left_pane3.getChildren().add(prop);

            toggle.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    if (toggle.getText().equals("0")) {
                        toggle.setText("1");
                        Image image = new Image("/Image/inOn.png", true);
                        jani.state = true;
                    } else {
                        toggle.setText("0");
                        jani.state = false;
                    }
                    if (runSelected) {
                        jani.myCircuit.initialise();
                        jani.myCircuit.createCircuit();
                    }
                }
            });
        }
    }

    public class clockNodeClicked implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {

            event.consume();
            try {
                if (selectSelected) {
                    left_pane3.getChildren().clear();
                    onClicked();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * clock clicked
         *
         * @throws IOException exception
         */
        public void onClicked() throws IOException {
            HBox prop1 = new HBox();
            prop1.setSpacing(57);
            prop1.setPrefSize(300, 25);
            prop1.setPadding(new Insets(15, 0, 0, 10));

            Label label = new Label("Delay:");
            label.setStyle("-fx-font-size: 14px;" + "-fx-text-fill: #FFFFFF");

            TextField delay = new TextField(jani.delaySeconds + "");
            delay.setPrefSize(70, 14);
            delay.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        delay.setText(newValue.replaceAll("\\D", ""));
                    }
                }
            });

            Button delaySet = new Button("Set");
            delaySet.setPrefSize(50, 14);

            prop1.getChildren().add(label);
            prop1.getChildren().add(delaySet);
            prop1.getChildren().add(delay);
            left_pane3.getChildren().add(prop1);

            delaySet.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    jani.delaySeconds = Integer.parseInt(delay.getText());
                }
            });

            Button generate = new Button("Set");
            generate.setPrefSize(50, 14);

            HBox prop2 = new HBox();
            prop2.setSpacing(59);//163
            prop2.setPrefSize(300, 50);
            prop2.setPadding(new Insets(10, 0, 0, 10));

            Label label2 = new Label("Ticks:");
            label2.setStyle("-fx-font-size: 14px;" + "-fx-text-fill: #FFFFFF");

            TextField ticks = new TextField((jani.clockTicks / 2) + "");
            ticks.setPrefSize(70, 14);
            ticks.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        ticks.setText(newValue.replaceAll("\\D", ""));
                    }
                }
            });

            prop2.getChildren().add(label2);
            prop2.getChildren().add(generate);
            prop2.getChildren().add(ticks);
            left_pane3.getChildren().add(prop2);

            generate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    jani.clockTicks = 2 * Integer.parseInt(ticks.getText());
                }
            });


        }
    }

    public class mouseDraggedOnRightPaneListener implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            Point2D p = right_pane.sceneToLocal(e.getSceneX(), e.getSceneY());
            if (p.getX() >= RootLayout.this.x - 200) {
                RootLayout.this.x = RootLayout.this.x + 100;
                right_pane.setMinWidth(RootLayout.this.x);
            }
            if (p.getY() >= RootLayout.this.y - 200) {
                RootLayout.this.y = RootLayout.this.y + 100;
                right_pane.setMinHeight(RootLayout.this.y);
            }
        }
    }

    public class bulbNodeClicked implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            if (runSelected && jani != null) {
                if (jani.myCircuit.truthTableCheck()) {
                    truthTable.setOpacity(1);
                    if (jani.getType() == DragIconType.output)
                        simplify.setOpacity(1);
                }
                if (jani.getType() != DragIconType.output)
                    simplify.setOpacity(0.2);
                if (!jani.myCircuit.truthTableCheck())
                    truthTable.setOpacity(0.2);
            } else {
                truthTable.setOpacity(0.2);
                simplify.setOpacity(0.2);
            }
        }
    }

    public class booleanDrawClicked implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent ke) {
            if (!removeSelected && !selectSelected) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    String enteredString = "";
                    enteredString = bool_Text.getText();

                    drawCircuits(enteredString);
                }
            }
        }

        /**
         * draw clicked
         *
         * @param enteredString the string which is entered
         */
        public void drawCircuits(String enteredString) {
            CircuitMaker myCreator = new CircuitMaker();
            myCreator.makeCircuit(enteredString);
            Circuits newCircuit = myCreator.getCircuit();


            int x = 50;
            int y = -50;
            for (int i = 0; i < right_pane.getChildren().size(); i++) {
                if (right_pane.getChildren().get(i).getLayoutY() > y)
                    y = (int) right_pane.getChildren().get(i).getLayoutY() + 50;

            }
            int originalY = y;
            int highestLevel = -1000;
            for (int i = 0; i < newCircuit.components.size(); i++) {
                if (newCircuit.components.get(i).getLvl() > highestLevel)
                    highestLevel = newCircuit.components.get(i).getLvl();

            }
            int heightOfNewCircuit = findH(newCircuit, highestLevel);

            RootLayout.this.y = RootLayout.this.y + heightOfNewCircuit;
            right_pane.setMinHeight(RootLayout.this.y);

            int widthNewCircuit = findW(highestLevel);
            if (widthNewCircuit > RootLayout.this.x) {
                RootLayout.this.x = RootLayout.this.x + widthNewCircuit;
                right_pane.setMinHeight(RootLayout.this.x);
            }


            int t = 0;
            Circuits myCircuit = new Circuits();
            myCircuits.add(myCircuit);

            while (t <= highestLevel) {
                for (int i = 0; i < newCircuit.components.size(); i++) {
                    if (newCircuit.components.get(i).getLvl() == t) {
                        if (!newCircuit.components.get(i).getType().equals("Wires")) {
                            DraggableNode node = new DraggableNode();

                            if (newCircuit.components.get(i).getType().equals("Switch")) {
                                node.setType(DragIconType.input);

                            } else if (newCircuit.components.get(i).getType().equals("AND")) {
                                node.setType(DragIconType.and);
                                node.inValue = ((AND) newCircuit.components.get(i)).inValue();
                                node.basicInputOffset = true;

                            } else if (newCircuit.components.get(i).getType().equals("OR")) {
                                node.setType(DragIconType.or);
                                node.inValue = ((OR) newCircuit.components.get(i)).inValue();
                                node.basicInputOffset = true;

                            } else if (newCircuit.components.get(i).getType().equals("NOT")) {
                                node.setType(DragIconType.not);

                            } else if (newCircuit.components.get(i).getType().equals("Splitter")) {
                                node.setType(DragIconType.splitter);

                            } else if (newCircuit.components.get(i).getType().equals("Bulb")) {
                                node.setType(DragIconType.output);
                            }


                            y = y + 150;
                            RootLayout.this.right_pane.getChildren().add(node);
                            node.setComponent(newCircuit.components.get(i));
                            node.level = newCircuit.components.get(i).myLvl;
                            newCircuit.components.get(i).setGUI(node);
                            node.resizeRelocate(x, y, 100, 100);
                            node.xLoc = x;
                            node.yLoc = y;


                            if (node.getType() == DragIconType.clock)
                                node.addEventHandler(MouseEvent.MOUSE_RELEASED, new clockNodeClicked());
                            else
                                node.addEventHandler(MouseEvent.MOUSE_RELEASED, new dragNodeClicked());

                        }
                    }
                }
                x = x + 150;
                t++;
                y = originalY;
            }


            for (int i = 0; i < newCircuit.components.size(); i++) {
                if (newCircuit.components.get(i).getType().equals("Wires")) {
                    NodeLink a = new NodeLink();

                    a.bindEnds((DraggableNode) (((Wires) newCircuit.components.get(i)).getStart().getGUI()),
                            (DraggableNode) (((Wires) newCircuit.components.get(i)).getEnd().getGUI()));
                    RootLayout.this.right_pane.getChildren().add(0, a);
                }

            }


        }


        /**
         * checks the vertically where circuit is finished
         *
         * @param newCircuit    the new circuit to be added
         * @param heighestLevel the highest level
         * @return the highest level
         */
        public int findH(Circuits newCircuit, int heighestLevel) {
            int size = 0;
            int temp = 0;
            for (int j = 0; j <= heighestLevel; j++) {
                for (int i = 0; i < newCircuit.components.size(); i++) {
                    if (!newCircuit.components.get(i).getType().equals("Wires"))
                        if (newCircuit.components.get(i).getLvl() == j)
                            size++;
                }
                if (size > temp)
                    temp = size;
                size = 0;
            }

            size = temp * 150;
            return size + 100;
        }

        /**
         * checks horizontally where circut finishes
         *
         * @param heighestLevel the highest level
         * @return the highest level
         */
        public int findW(int heighestLevel) {
            heighestLevel = heighestLevel * 150;
            return heighestLevel + 50;
        }

    }
}
