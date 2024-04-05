public class QuadrantTree {

    private QTreeNode root;

    public QuadrantTree(int[][] thePixels) {
        if (thePixels.length == 1) {
            root = new QTreeNode(null, 0, 0, thePixels.length, Gui.averageColor(thePixels, 0, 0, thePixels.length));
        }
        else {
            root = new QTreeNode();
            root.setColor(Gui.averageColor(thePixels, 0, 0, thePixels.length));
            root.setSize(thePixels.length);
            buildTree(root, thePixels);
        }
    }

    private void buildTree (QTreeNode parent, int[][] thePixels) {
        //Base case: No children to be made
        for (int i = 0; i < 4; i++) {
            int subQuadrantSize = parent.getSize() / 2;
            int childX;
            int childY;
            if (i == 0) {
                childX = parent.getx();
                childY = parent.gety();
            }
            else if (i == 1) {
                childX = parent.getx() + subQuadrantSize;
                childY = parent.gety();
            }
            else if (i == 2) {
                childX = parent.getx();
                childY = parent.gety() + subQuadrantSize;
            }
            else {
                childX = parent.getx() + subQuadrantSize;
                childY = parent.gety() + subQuadrantSize;
            }
            int childColor = Gui.averageColor(thePixels, childX, childY, subQuadrantSize);
            QTreeNode child;
            if (subQuadrantSize == 1) {
                child = new QTreeNode(null, childX, childY, subQuadrantSize, childColor);
            }
            else {
                child = new QTreeNode();
                child.setx(childX);
                child.sety(childY);
                child.setSize(subQuadrantSize);
                child.setColor(childColor);
                buildTree(child, thePixels);
            }
            parent.setChild(child, i);

        }

    }

    public QTreeNode getRoot() {
        return this.root;
    }

    public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel) {
        // Base case: theLevel = 0 or we're at a leaf node:
        if (theLevel == 0 || r.isLeaf()) {
            return new ListNode<>(r);
        }
        ListNode<QTreeNode> resultantList = null;
        for (int i = 0; i < 4; i++) {
            ListNode<QTreeNode> pixelNodes = getPixels(r.getChild(i), theLevel - 1); // Decrement theLevel for the next recursion

            if (resultantList == null) {
                resultantList = pixelNodes; // Assign the result list to the first non-null list node
            } else {
                resultantList.setNext(pixelNodes); // Concatenate the result list of the current child to the overall result list
            }
        }
    }

}
