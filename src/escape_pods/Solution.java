package escape_pods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

// https://www.geeksforgeeks.org/dinics-algorithm-maximum-flow/
// https://sites.google.com/site/indy256/algo/dinic_flow
public class Solution {
    static class Edge {
        int vertex;
        int flow;
        int capacity;
        int reverse;

        public Edge(int vertex, int flow, int capacity, int reverse) {
            this.vertex = vertex;
            this.flow = flow;
            this.capacity = capacity;
            this.reverse = reverse;
        }
    }

    // Dinic's algorithm
    static class Graph
    {
        List<List<Edge>> adjacentEdges;
        int[] level;

        public Graph(int vertexCount) {
            adjacentEdges = new ArrayList<>(vertexCount);
            for (int i = 0; i < vertexCount; i++)
                adjacentEdges.add(i, new ArrayList<>());

            level = new int[vertexCount];
        }

        void addEdge(int s, int t, int capacity) {
            adjacentEdges.get(s).add(new Edge(t, 0, capacity, adjacentEdges.get(t).size()));
            adjacentEdges.get(t).add(new Edge(s, 0, 0, adjacentEdges.get(s).size() - 1));
        }

        // true if more flow can be sent
        boolean BFS(int s, int t) {
            Arrays.fill(level, -1);
            level[s] = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(s);

            while (!queue.isEmpty()) {
                int u = queue.remove();
                for (Edge e : adjacentEdges.get(u)) {
                    if (level[e.vertex] < 0 && e.flow < e.capacity) {
                        level[e.vertex] = level[u] + 1;

                        queue.add(e.vertex);
                    }
                }
            }

            return level[t] >= 0;
        }

        int sendFlowDFS(int s, int flow, int t, int ptr[]) {
            if (s == t)
                return flow;

            for (; ptr[s] < adjacentEdges.get(s).size(); ++ptr[s]) {
                Edge e = adjacentEdges.get(s).get(ptr[s]);

                if (level[e.vertex] == level[s]+1 && e.flow < e.capacity) {
                    int currentFlow = Math.min(flow, e.capacity - e.flow);
                    int temporaryFlow = sendFlowDFS(e.vertex, currentFlow, t, ptr);

                    if (temporaryFlow > 0) {
                        e.flow += temporaryFlow;

                        adjacentEdges.get(e.vertex).get(e.reverse).flow -= temporaryFlow;
                        return temporaryFlow;
                    }
                }
            }
            return 0;
        }

        int findMaxflow(int s, int t) {
            if (s == t)
                return -1;

            int total = 0;

            while (BFS(s, t)) {
                int[] start = new int[adjacentEdges.size()];

                while (true) {
                    int flow = sendFlowDFS(s, Integer.MAX_VALUE, t, start);
                    if (flow <= 0)
                        break;
                    total += flow;
                }
            }
            return total;
        }
    }

    static int solution(int[] entrances, int[] exits, int[][] path) {
        // Maximum Flow problem
        Graph g = new Graph(path.length + 2);
        for (int row = 0; row < path.length; ++row) {
            for (int column = 0; column < path.length; ++column) {
                if (path[row][column] > 0)
                    g.addEdge(row, column, path[row][column]);
            }
        }

        // supersources to sources
        for (int entrance : entrances)
            g.addEdge(path.length, entrance, Integer.MAX_VALUE);

        // sinks to supersink
        for (int exit : exits)
            g.addEdge(exit, path.length + 1, Integer.MAX_VALUE);

        return g.findMaxflow(path.length, path.length + 1);
    }

    public static void main(String[] args) {
        int[] entrances = {0, 1};
        int[] exits = {4, 5};
        int[][] path = {
                {0, 0, 4, 6, 0, 0},
                {0, 0, 5, 2, 0, 0},
                {0, 0, 0, 0, 4, 4},
                {0, 0, 0, 0, 6, 6},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        System.out.println(16 + " - truth, our solution: " + Solution.solution(entrances, exits, path));

        int[] entrances2 = {0};
        int[] exits2 = {3};
        int[][] path2 = {
                {0, 7, 0, 0},
                {0, 0, 6, 0},
                {0, 0, 0, 8},
                {9, 0, 0, 0}
        };
        System.out.println(6 + " - truth, our solution: " + Solution.solution(entrances2, exits2, path2));
    }
}