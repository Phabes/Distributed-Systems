public class Leaf {
    private final String node;
    private final int depth;

    public Leaf(String node, int depth) {
        this.node = node;
        this.depth = depth;
    }

    public String getNode() {
        return node;
    }

    public int getDepth() {
        return depth;
    }
}
