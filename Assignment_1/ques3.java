import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ques3 {
    public static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source - 1;
            this.destination = destination - 1;
            this.weight = weight;
        }
    }

    private static void allPaths(int vertices, int source, int destination, boolean[][] graph, ArrayList<Integer> v,
                                 int distance, boolean[] visited) {
        v.add(source);
        visited[source] = true;
        if (source == destination) {
            System.out.print("\nPath: ");
            for (Integer integer : v) {
                System.out.print(integer + 1 + " ");
            }
            System.out.println("\nDistance From Source: " + distance);
            visited[source] = false;
            v.remove(v.size() - 1);
            return;
        }

        for (int i=0; i<vertices; i++) {
            if (visited[i] == false && graph[source][i]) {
                allPaths(vertices, i, destination, graph, v, distance + 1, visited);
            }
        }
        visited[source] = false;
        v.remove(v.size() - 1);
    }

    private static void shortestPath(int vertices, int source, int destination, int edges, Edge[] edgeList) {
        int[] parent = new int[vertices];
        int[] distance = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        distance[source] = 0;
        for (int i=0; i<vertices-1; i++) {
            for (int j=0; j<edges; j++) {
                if (distance[edgeList[j].source] != Integer.MAX_VALUE
                        && distance[edgeList[j].source] + edgeList[j].weight < distance[edgeList[j].destination]) {
                    distance[edgeList[j].destination] = distance[edgeList[j].source] + edgeList[j].weight;
                    parent[edgeList[j].destination] = edgeList[j].source;
                }
            }

        }
        for (int j=0; j<edges; j++) {
            if (distance[edgeList[j].source] != Integer.MAX_VALUE
                    && distance[edgeList[j].source] + edgeList[j].weight < distance[edgeList[j].destination]) {
                System.out.println("Negative Cycles Exist");
                return;
            }
        }
        Stack<Integer> temp = new Stack<Integer>();
        int node = destination;
        while (node != -1) {
            temp.push(node + 1);
            node = parent[node];
        }
        System.out.print("Path: ");
        while (!temp.empty()) {
            System.out.print(temp.pop() + " ");
        }
        System.out.println("\nDistance From Source: " + distance[destination]);

    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of Vertices in the Graph: ");
        int vertices = input.nextInt();
        System.out.print("Enter number of Edges in the Graph: ");
        int edges = input.nextInt();
        Edge[] edgeList = new Edge[edges];
        System.out.println("Vertices may range from 1 to number of vertices.");
        for (int i = 0; i < edges; i++) {
            System.out.print("Enter Edge( source - destination - weight ): ");
            int s = input.nextInt();
            int d = input.nextInt();
            int w = input.nextInt();
            edgeList[i] = new ques3.Edge(s, d, w);
        }
        System.out.println("Enter the vertex number of Source and Destination");
        int s = input.nextInt();
        int d = input.nextInt();
        input.close();

        System.out.println("\nShortest Path according to the given graph:");
        shortestPath(vertices, s - 1, d - 1, edges, edgeList);

        System.out.println("\nAll Paths present in the Graph: ");
        boolean[] visited = new boolean[vertices];
        boolean[][] graph = new boolean[vertices][vertices];
        for (Edge edge : edgeList) {
            graph[edge.source][edge.destination] = true;
        }
        ArrayList<Integer> v = new ArrayList<Integer>();
        allPaths(vertices, s - 1, d - 1, graph, v, 0, visited);
    }
}
