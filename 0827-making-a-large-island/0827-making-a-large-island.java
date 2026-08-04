import java.util.*;

class Solution {

    static class DisjointSet {
        int[] parent;
        int[] size;

        DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int node) {
            if (parent[node] == node)
                return node;
            return parent[node] = find(parent[node]);
        }

        void union(int u, int v) {

            int pu = find(u);
            int pv = find(v);

            if (pu == pv) return;

            if (size[pu] >= size[pv]) {
                parent[pv] = pu;
                size[pu] += size[pv];
            } else {
                parent[pu] = pv;
                size[pv] += size[pu];
            }
        }
    }

    boolean valid(int r, int c, int n) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public int largestIsland(int[][] grid) {

        int n = grid.length;

        DisjointSet ds = new DisjointSet(n * n);

        int[][] dir = {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };

        // Step 1 : Build DSU for all existing islands
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 0) continue;

                for (int[] d : dir) {

                    int nr = i + d[0];
                    int nc = j + d[1];

                    if (valid(nr, nc, n) && grid[nr][nc] == 1) {

                        ds.union(i * n + j, nr * n + nc);
                    }
                }
            }
        }

        int ans = 0;

        // Step 2 : Try converting every 0 into 1
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) continue;

                HashSet<Integer> set = new HashSet<>();

                for (int[] d : dir) {

                    int nr = i + d[0];
                    int nc = j + d[1];

                    if (valid(nr, nc, n) && grid[nr][nc] == 1) {

                        set.add(ds.find(nr * n + nc));
                    }
                }

                int currentSize = 1;

                for (int parent : set) {
                    currentSize += ds.size[parent];
                }

                ans = Math.max(ans, currentSize);
            }
        }

        // Step 3 : Handle all-1s grid
        for (int i = 0; i < n * n; i++) {

            if (ds.find(i) == i) {
                ans = Math.max(ans, ds.size[i]);
            }
        }

        return ans;
    }
}