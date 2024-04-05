public class QTreeNode {
    private int x;
    private int y;
    private int size;
    private int color;
    private QTreeNode parent;
    private QTreeNode[] children;

    public QTreeNode() {
        this.parent = null;
        this.children = new QTreeNode[4];
        this.x = 0;
        this.y = 0;
        this.size = 0;
        this.color = 0;
    }

    public QTreeNode(QTreeNode[] theChildren, int xcoord, int ycoord, int theSize, int theColor) {
        this.children = theChildren;
        this.x = xcoord;
        this.y = ycoord;
        this.size = theSize;
        this.color = theColor;
    }

    public boolean contains(int xcoord, int ycoord) {
        return this.x == xcoord && this.y == ycoord;
    }

    public int getx() {
        return this.x;
    }

    public int gety() {
        return this.y;
    }

    public int getSize() {
        return this.size;
    }

    public int getColor() {
        return this.color;
    }

    public QTreeNode getParent() {
        return this.parent;
    }

    public QTreeNode getChild(int index) throws QTreeException {
        if (index < 0 || index > 3) {
            throw new QTreeException("Invalid Index");
        }
        if (children == null) {
            throw new QTreeException("No allocated children!");
        }
        // TODO: Check if children[index] is null
        return children[index];
    }

    public void setx(int newx) {
        this.x = newx;
    }

    public void sety(int newy) {
        this.y = newy;
    }

    public void setSize(int newSize) {
        this.size = newSize;
    }

    public void setColor(int newColor) {
        this.color = newColor;
    }

    public void setParent(QTreeNode newParent) {
        this.parent = newParent;
    }

    public void setChild(QTreeNode newChild, int index) throws QTreeException {
        if (index < 0 || index > 3) {
            throw new QTreeException("Invalid Index");
        }
        this.children[index] = newChild;
    }

    public boolean isLeaf() {
        if (children == null) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            if (children[i] == null) {
                continue;
            }
            return false;
        }
        return true;
    }
}
