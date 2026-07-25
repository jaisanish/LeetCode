import java.util.*;

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 1. Build adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        // 2. Track minimum cost to reach each node
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[src] = 0;

        // Queue stores: {node, current_cost}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0});

        int stops = 0;

        // 3. BFS up to k + 1 edges (k intermediate stops)
        while (!queue.isEmpty() && stops <= k) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();

                for (int[] next : adj.get(cur[0])) {
                    if (cur[1] + next[1] < minCost[next[0]]) {
                        minCost[next[0]] = cur[1] + next[1];
                        queue.offer(new int[]{next[0], minCost[next[0]]});
                    }
                }
            }

            stops++;
        }

        return minCost[dst] == Integer.MAX_VALUE ? -1 : minCost[dst];
    }
}