package dataJSON.sorting;

    class TreeNode {
        String data;
        TreeNode left, right;

        public TreeNode(String value) {
            data = value;
            left = right = null;
        }

        //TODO: dette skal fjernes
        void write() {
            System.out.println(data);
        }
}
