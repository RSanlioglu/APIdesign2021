package dataJSON.sorting;

public class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(String value) {
        TreeNode newNode = new TreeNode(value);

        if (isEmpty()) {
            root = newNode;
            return;
        }
        TreeNode current = root;
        boolean finished = false;

        while (!finished) {
            int comp = value.compareTo(String.valueOf(current.data));

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
