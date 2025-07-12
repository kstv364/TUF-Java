package org.example.graphs;

import java.util.*;

public class Solution {

    public int shortestPath(int n, int[][] edges, int start, int end, Set<Integer> obstacles) {
        // Build graph as adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        // BFS setup
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        // Early exits
        if (obstacles.contains(start) || obstacles.contains(end)) return -1;

        queue.offer(new int[] { start, 0 }); // node, depth
        visited.add(start);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0], depth = current[1];

            if (node == end) return depth;

            for (int neighbor : graph.get(node)) {
                if (!visited.contains(neighbor) && !obstacles.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(new int[] { neighbor, depth + 1 });
                }
            }
        }

        return -1; // No path found
    }

}
