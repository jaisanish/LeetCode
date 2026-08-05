class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] e : invocations)
            graph.get(e[0]).add(e[1]);

        boolean[] suspicious = new boolean[n];

        dfs(k, graph, suspicious);

        // Check if any clean method invokes a suspicious one
        for (int[] e : invocations) {
            if (!suspicious[e[0]] && suspicious[e[1]]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) ans.add(i);
                return ans;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i])
                ans.add(i);
        }

        return ans;
    }

    void dfs(int u, List<List<Integer>> graph, boolean[] suspicious) {
        suspicious[u] = true;

        for (int v : graph.get(u)) {
            if (!suspicious[v])
                dfs(v, graph, suspicious);
        }
    }
}