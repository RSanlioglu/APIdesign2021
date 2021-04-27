package Sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * A binary search tree implementation for the use of sorting strings. The implementation
 * is gathered from the course: ITF20006-1 21V Algorithms and datastructures, and modified
 * so that it is capable for the use of the frameworks scope.
 */
final class BinarySearchTree {
    private TreeNode root;

    //Constructor that creates a search tree with a null value for the root
    public BinarySearchTree() {
        root = null;
    }

    /**
     * The words are inserted into the tree and will be easy to
     * traverse through in order to sort the strings in an alphabetical order
     * @param value - String that is to be inserted into the tree
     */
    public void insert(String value) {
        TreeNode newNode = new TreeNode(value);

        //See if the tree is empty
        if (root == null) {
            root = newNode;
            return;
        }

        TreeNode current = root;
        boolean finished = false;

        while (!finished) {
            int comp = value.compareTo(String.valueOf(current.data));
            //Comparing the strings
            if (comp < 0) {
                if (current.left == null) {
                    current.left = newNode;
                    finished = true;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    finished = true;
                } else {
                    current = current.right;
                }
            }
        }
    }

    /**
     * Simple inorder traversals within the search tree that has been inserted with data.
     * The traversal will start from the far left node and add all strings when passing to
     * the far right node, leaving us with a list with alphabetical sorted lists.
     * @return - A list of alphabetical sorted strings
     */
    public List<String> inorder() {
        List<String> sortedStrings = new ArrayList<>();
        inorder(root, sortedStrings);
        return sortedStrings;
    }

    private List<String> inorder(TreeNode t, List<String> stringsToBeSorted) {
        if (t != null) {
            inorder(t.left, stringsToBeSorted);
            stringsToBeSorted.add(t.data);
            inorder(t.right, stringsToBeSorted);
        }
        return stringsToBeSorted;
    }
}
