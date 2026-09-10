class Solution {

    int cnt = 0;

    public int[] postOrder(TreeNode root) {

        if (root == null) {
            return new int[]{0, 0};
        }

        int[] left = postOrder(root.left);
        int[] right = postOrder(root.right);

        int sum = left[0] + right[0] + root.val;
        int count = left[1] + right[1] + 1;

        if (sum / count == root.val) {
            cnt++;
        }

        return new int[]{sum, count};
    }

    public int averageOfSubtree(TreeNode root) {
        postOrder(root);
        return cnt;
    }
}