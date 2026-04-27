import java.util.ArrayList;

public class TreeIsomorphism {
    private final Graph graph;

    public TreeIsomorphism(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph nao pode ser nulo");
        }
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public boolean isTree() {
        if(graph.E() == (graph.V() - 1)) {
            return true;
        }

        return false;
    }

    public String getValidationMessage() {
        if(this.isTree()) {
            return "O grafo é uma árvore válida.";
        }
        return "O grafo não é uma árvore válida";
    }

    public int[] getCenters() {
        int[] degree = new int[graph.V()];
        ArrayList<Integer> leaves = new ArrayList<>();

        for(int i = 0; i < graph.V(); i++) {
            degree[i] = graph.degree(i);
            if(degree[i] == 0 || degree[i] == 1) {
                leaves.add(i);
                degree[i] = 0;
            }
        }
        int count = leaves.size();

        while(count < graph.V()) {
            ArrayList<Integer> newLeaves = new ArrayList<>();

            for (int node : leaves) {
                for(int neighbor : graph.adj(node)) {
                    degree[neighbor] = degree[neighbor] -1;
                    if(degree[neighbor] == 1) {
                        newLeaves.add(neighbor);
                    }
                }
                degree[node] = 0;
            }

            count += newLeaves.size();
            leaves = newLeaves;
        }

        int[] auxLeaves = new int[leaves.size()];
        for(int i = 0; i < leaves.size(); i++) {
            auxLeaves[i] = leaves.get(i);
        }

        return auxLeaves;
    }

    public String getCanonicalEncoding() {
        int[] centers = this.getCenters();
        String canonical = null;

        for (int center : centers) {
            String encoding = encode(center, -1);
            
            if (canonical == null || encoding.compareTo(canonical) < 0) {
                canonical = encoding;
            }
        }
        return canonical;
    }

    private String encode(int u, int p) {
        ArrayList<String> labels = new ArrayList<>();

        for (int v : graph.adj(u)) {
            if (v != p) {
                labels.add(encode(v, u));
            }
        }

        java.util.Collections.sort(labels);

        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (String s : labels) {
            sb.append(s);
        }
        sb.append(")");

        return sb.toString();
    }

    public boolean isIsomorphicTo(TreeIsomorphism other) {
        return this.getCanonicalEncoding().equals(other.getCanonicalEncoding());
    }
}