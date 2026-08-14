class Solution {

    class Node {
        char leftChar, rightChar;
        int prefix, suffix, best, len;

        Node(char leftChar, char rightChar, int prefix, int suffix, int best, int len) {
            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.prefix = prefix;
            this.suffix = suffix;
            this.best = best;
            this.len = len;
        }
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        int n = s.length();
        arr = s.toCharArray();

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int q = queryIndices.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {

            int index = queryIndices[i];
            arr[index] = queryCharacters.charAt(i);

            update(1, 0, n - 1, index);

            ans[i] = tree[1].best;
        }

        return ans;
    }

    // Build segment tree
    void build(int node, int l, int r) {

        if (l == r) {
            tree[node] = new Node(
                arr[l],
                arr[l],
                1,
                1,
                1,
                1
            );
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Update one index
    void update(int node, int l, int r, int index) {

        if (l == r) {
            tree[node] = new Node(
                arr[l],
                arr[l],
                1,
                1,
                1,
                1
            );
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two adjacent segments
    Node merge(Node left, Node right) {

        char leftChar = left.leftChar;
        char rightChar = right.rightChar;

        int prefix = left.prefix;
        int suffix = right.suffix;

        int best = Math.max(left.best, right.best);

        // The boundary characters are same
        if (left.rightChar == right.leftChar) {

            // Entire left segment has same character
            if (left.prefix == left.len) {
                prefix = left.len + right.prefix;
            }

            // Entire right segment has same character
            if (right.suffix == right.len) {
                suffix = right.len + left.suffix;
            }

            // Join suffix of left + prefix of right
            best = Math.max(best, left.suffix + right.prefix);
        }

        return new Node(
            leftChar,
            rightChar,
            prefix,
            suffix,
            best,
            left.len + right.len
        );
    }
}