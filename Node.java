import java.util.*;

class Node implements Comparable<Node> {
    State state;
    Node parent;
    String action;
    int cost;
    int heuristic;

    public Node(State state, Node parent, String action, int cost, int heuristic) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    @Override
    public int compareTo(Node other) {
        // Prioritas adalah nilai cost + heuristic
        return Integer.compare(this.cost + this.heuristic, other.cost + other.heuristic);
    }
}

// Definisi kelas State untuk merepresentasikan keadaan (state)
class State {
    // Implementasikan atribut dan metode yang sesuai untuk merepresentasikan state
}


    // Implementasikan algoritma Greedy Best-First Search
    public Node greedyBFS() {
        Node initialNode = new Node(initialState, null, null, 0, heuristic(initialState));
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        frontier.add(initialNode);

        while (!frontier.isEmpty()) {
            Node node = frontier.poll();
            if (goalTest(node.state)) {
                return node; // Solusi ditemukan
            }

            for (String action : actions(node.state)) {
                State childState = result(node.state, action);
                Node childNode = new Node(childState, node, action, stepCost(node.state, action), heuristic(childState));
                frontier.add(childNode);
            }
        }

        return null; // Tidak ada solusi
    }

    // Implementasikan algoritma A* Search
    public Node aStarSearch() {
        Node initialNode = new Node(initialState, null, null, 0, heuristic(initialState));
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        Set<State> explored = new HashSet<>();
        frontier.add(initialNode);

        while (!frontier.isEmpty()) {
            Node node = frontier.poll();
            if (goalTest(node.state)) {
                return node; // Solusi ditemukan
            }

            explored.add(node.state);

            for (String action : actions(node.state)) {
                State childState = result(node.state, action);
                Node childNode = new Node(childState, node, action, node.cost + stepCost(node.state, action), heuristic(childState));

                if (!explored.contains(childState)) {
                    frontier.add(childNode);
                }
            }
        }

        return null; // Tidak ada solusi
    }

    public static void main(String[] args) {
        ProblemSolver problemSolver = new ProblemSolver();
        State initialState = new State(); // Inisialisasi state awal
        problemSolver.initialState = initialState;

        // Gunakan algoritma Greedy BFS
        Node resultGreedyBFS = problemSolver.greedyBFS();
        if (resultGreedyBFS != null) {
            System.out.println("Greedy BFS Path:");
            while (resultGreedyBFS != null) {
                System.out.println(resultGreedyBFS.state);
                resultGreedyBFS = resultGreedyBFS.parent;
            }
        } else {
            System.out.println("Tidak ada solusi dengan Greedy BFS.");
        }

        // Gunakan algoritma A* Search
        Node resultAStar = problemSolver.aStarSearch();
        if (resultAStar != null) {
            System.out.println("A* Search Path:");
            while (resultAStar != null) {
                System.out.println(resultAStar.state);
                resultAStar = resultAStar.parent;
            }
        } else {
            System.out.println("Tidak ada solusi dengan A* Search.");
        }
    }
}