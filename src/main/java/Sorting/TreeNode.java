package Sorting;

/**
 * TreeNodes will contain a string of data and
 * two more nodes. One left node and one right node.
 */
final class TreeNode {
    String data;
    TreeNode left, right;

    /**
     * Constructor that assigns a value to the data of the TreNode.
     * The left and right nodes are also assigned to be null for now
     * @param value - String that will be used for sorting
     */
    public TreeNode(String value) {
        data = value;
        left = right = null;
    }
}
