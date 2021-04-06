package dataJSON.sorting;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
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

    public void inorder() {
        System.out.println("Ord og forekomster i teksten: ");
        inorder(root);
        System.out.println("\n");
    }

    private void inorder(TreeNode t) {
        if (t != null) {
            inorder(t.left);
            t.write();
            inorder(t.right);
        }
    }
}
