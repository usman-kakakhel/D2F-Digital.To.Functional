/**
 * @author Mian Usman Naeem Kakakhel
 * last edit: 10 May 2018
 */
package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class D2F implements Serializable {
    public ArrayList<RightPaneChildren> items = new ArrayList<>();
    public double x = 0;
    public double y = 0;
    public String labelText = "";


    /**
     * void to turn into serial
     */
    public void turnIntoSerial()
    {
        labelText = RootLayout.labelText;
        this.x = RootLayout.x;
        this.y = RootLayout.y;
        for (int i = 0; i < items.size(); i++)
        {
            if (items.get(i).getTypee().equals("Wire"))
            {
                WireInfo a = new WireInfo();
                a.id = ((NodeLink)items.get(i)).getId();
                a.prevId = ((NodeLink)items.get(i)).getSourceId();
                a.nextId = ((NodeLink)items.get(i)).getTargetId();
                //a.myCircuitId = ((NodeLink)items.get(i)).myCircuit.getId();
                items.remove(i);
                items.add(i, a);
            }
            else
            {
                NodeInfo b = new NodeInfo();
                b.in = ((DraggableNode)items.get(i)).in;
                b.out = ((DraggableNode)items.get(i)).out;
                b.mLinkIds = ((DraggableNode)items.get(i)).getList();
                b.mLinkIdsIn = ((DraggableNode)items.get(i)).getListIn();
                b.mLinkIdsOut = ((DraggableNode)items.get(i)).getListOut();
                b.xLoc = ((DraggableNode)items.get(i)).xLoc;
                b.yLoc = ((DraggableNode)items.get(i)).yLoc;
                b.id = ((DraggableNode)items.get(i)).getId();
                b.mType = ((DraggableNode)items.get(i)).getType();
                b.myCircuitId = ((DraggableNode)items.get(i)).myCircuit.getId();
                b.inValue = ((DraggableNode)items.get(i)).inValue;
                b.offsetChecker = ((DraggableNode)items.get(i)).offsetChecker;
                b.inputOffsetChecker = ((DraggableNode)items.get(i)).inputOffsetChecker;
                b.basicInputOffset = ((DraggableNode)items.get(i)).basicInputOffset;
                b.state = ((DraggableNode)items.get(i)).state;
                b.level = ((DraggableNode)items.get(i)).level;
                items.remove(i);
                items.add(i, b);
            }
        }
    }
    //inner Node class
    public class NodeInfo implements Serializable, RightPaneChildren {
        String type = "Node";
        public int in;
        public int out;
        public int inValue;
        public boolean offsetChecker;
        public boolean inputOffsetChecker;
        public boolean basicInputOffset;
        public boolean state;
        public double xLoc;
        public double yLoc;
        public List<String> mLinkIds = new ArrayList<String>();
        public List<String> mLinkIdsIn = new ArrayList<String>();
        public List<String> mLinkIdsOut = new ArrayList<String>();
        public String myCircuitId;
        public String id;
        public DragIconType mType;
        public int level;

        @Override
        public String getTypee() {
            return type;
        }
    }
    //inner Wire class
    public class WireInfo implements Serializable, RightPaneChildren{
        public String type = "Wire";
        public String prevId;
        public String nextId;
        public String id;
        //public String myCircuitId;

        @Override
        public String getTypee() {
            return type;
        }
    }
}
