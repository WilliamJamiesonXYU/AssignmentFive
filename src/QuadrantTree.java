public class QuadrantTree {

    private QTreeNode root;

    public QuadrantTree(int[][] thePixels) {
        buildTree(root, thePixels.length, thePixels, 0);
    }

    private void buildTree (QTreeNode parent, int quadrantSize, int[][] quadrantPixels, int quadrantPos) {
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
