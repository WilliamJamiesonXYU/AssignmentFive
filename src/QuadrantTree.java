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
        // If the current node is null, return null (to avoid adding null entries in the list).
        if (r == null) {
            return null;
        }
        // Base case: if we are at the target level, or the node is a leaf, return it in a new list.
        if (theLevel == 0 || r.isLeaf()) {
            return new ListNode<>(r);
        } else {
            // Initialize an empty list to hold the results.
            ListNode<QTreeNode> resultList = null;
            // Recursive case: Traverse the tree and concatenate lists from child nodes.
            for (int i = 0; i < 4; i++) { // Assuming each node has up to 4 children
                try {
                    QTreeNode child = r.getChild(i);
                    if (child != null) {
                        ListNode<QTreeNode> childList = getPixels(child, theLevel - 1);
                        resultList = concatenate(resultList, childList); // Concatenate the lists.
                    }
                } catch (QTreeException e) {
                    System.err.println("Invalid child index: " + e.getMessage());
                }
            }
            return resultList;
        }
    }

    private ListNode<QTreeNode> concatenate(ListNode<QTreeNode> list1, ListNode<QTreeNode> list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode<QTreeNode> current = list1;
        // Find the last node of the first list
        while (current.getNext() != null) {
            current = current.getNext();
        }
        // Append the second list to the first
        current.setNext(list2);
        return list1;
    }

    /*public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel) {
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
    }*/

    public Duple findMatching (QTreeNode r, int theColor, int theLevel) {

    }

    /**
     * Finds a node at a specified level representing a quadrant containing the specified point.
     *
     * @param r The current node (root for the initial call).
     * @param theLevel The level at which to search for the node.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return The node representing the quadrant containing the point at the specified level, or null if not found.
     */
    public QTreeNode findNode(QTreeNode r, int theLevel, int x, int y) {
        if (r == null || !r.contains(x, y)) {
            return null;
        }
        if (theLevel == 0 || r.isLeaf()) {
            return r;
        } else {
            for (int i = 0; i < 4; i++) {
                try {
                    QTreeNode child = r.getChild(i);
                    if (child != null && child.contains(x, y)) {
                        QTreeNode result = findNode(child, theLevel - 1, x, y);
                        if (result != null) {
                            return result;
                        }
                    }
                } catch (QTreeException e) {
                    System.err.println("Invalid child index: " + e.getMessage());
                }
            }
        }
        return null; // If the point is contained but not found at the specified level.
    }

}
