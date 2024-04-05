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
            buildTree(root, thePixels.length, thePixels, 0);
        }
    }

    private void buildTree (QTreeNode parent, int[][] thePixels) {
        //Base case: No children to be made
        if (parent.getSize() == 1) {
            return;
        }
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
//
//        // Base case: root node
//        if (this.root == null) {
//            if (quadrantSize == 1) {
//                this.root = new QTreeNode(null, 0, 0, quadrantSize, Gui.averageColor(quadrantPixels, 0, 0, quadrantSize));
//                return;
//            }
//            else {
//                this.root = new QTreeNode();
//                this.root.setColor(Gui.averageColor(quadrantPixels, 0, 0, quadrantSize));
//                this.root.setSize(quadrantSize);
//                for (int i = 0; i < 4; i++) {
//                    buildTree(root, quadrantSize, quadrantPixels, i);
//                }
//            }
//        }
//
//        int[][] subQuadrantPixels = new int[quadrantSize][quadrantSize];
//        quadrantSize /= 2;
//
//        QTreeNode child = new QTreeNode();
//        // Set proper x and y depending on which subquadrant the child should represent
//        if (quadrantPos == 0) {
//            child.setx(parent.getx());
//            child.sety(parent.gety());
//
//        }
//        else if (quadrantPos == 1) {
//            child.setx(parent.getx() + quadrantSize);
//            child.sety(parent.gety());
//        }
//        else if (quadrantPos == 2) {
//            child.setx(parent.getx());
//            child.sety(parent.gety() + quadrantSize);
//        }
//        else {
//            child.setx(parent.getx() + quadrantSize);
//            child.sety(parent.gety() + quadrantSize);
//        }
//        // Calculate the subquadrant's pixels
//        for (int j = 0; j < quadrantSize; j++) {
//            for (int h = 0; h < quadrantSize; h++) {
//                subQuadrantPixels[j][h] = quadrantPixels[child.getx() + j][child.gety() + h];
//            }
//        }
//        child.setColor(Gui.averageColor(subQuadrantPixels, child.getx(), child.gety(), quadrantSize));
//        if (quadrantSize != 1) {
//            for (int i = 0; i < 4; i++) {
//                buildTree(child, quadrantSize, subQuadrantPixels, quadrantPos);
//            }
//
//        }
//        parent.setChild(child, quadrantPos);
    }

    public QTreeNode getRoot() {
        return this.root;
    }

    public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel) {

    }

}
