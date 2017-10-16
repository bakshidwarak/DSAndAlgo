package leetcode.clonegraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors. OJ's undirected graph
 * serialization: Nodes are labeled uniquely. We use # as a separator for each node, and , as a separator for node label
 * and each neighbor of the node. As an example, consider the serialized graph {0,1,2#1,2#2,2}. The graph has a total of
 * three nodes, and therefore contains three parts as separated by #. First node is labeled as 0. Connect node 0 to both
 * nodes 1 and 2. Second node is labeled as 1. Connect node 1 to node 2. Third node is labeled as 2. Connect node 2 to
 * node 2 (itself), thus forming a self-cycle. Visually, the graph looks like the following:
 * 
 * <pre>

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
 * </pre>
 * 
 * @author dwaraknathbs
 */
public class CloneGraph {
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;

        HashMap<UndirectedGraphNode, UndirectedGraphNode> clones = new HashMap<>();

        return cloneGraph(node, clones);

    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node,
            HashMap<UndirectedGraphNode, UndirectedGraphNode> clones) {
        if (node == null)
            return null;
        if (clones.containsKey(node))
            return clones.get(node);

        UndirectedGraphNode graph = new UndirectedGraphNode(node.label);
        /**
         * Its key to add to hashmap before going to the neighbours
         */
        clones.put(node, graph);
        for (UndirectedGraphNode neighbour : node.neighbors) {
            UndirectedGraphNode clonedChild = cloneGraph(neighbour, clones);
            graph.neighbors.add(clonedChild);

        }

        return graph;
    }
}
