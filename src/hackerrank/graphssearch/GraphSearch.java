package hackerrank.graphssearch;

import java.io.*;
import java.util.*;
/**
 * Check out the resources on the page's right side to learn more about breadth-first search. The video tutorial is by Gayle Laakmann McDowell, author of the best-selling interview book Cracking the Coding Interview.
Consider an undirected graph consisting of  nodes where each node is labeled from  to  and the edge between any two nodes is always of length . We define node  to be the starting position for a BFS.

Given  queries in the form of a graph and some starting node, , perform each query by calculating the shortest distance from starting node  to all the other nodes in the graph. Then print a single line of  space-separated integers listing node 's shortest distance to each of the  other nodes (ordered sequentially by node number); if  is disconnected from a node, print  as the distance to that node.

Input Format

The first line contains an integer, , denoting the number of queries. The subsequent lines describe each query in the following format:

The first line contains two space-separated integers describing the respective values of  (the number of nodes) and  (the number of edges) in the graph.
Each line  of the  subsequent lines contains two space-separated integers,  and , describing an edge connecting node  to node .
The last line contains a single integer, , denoting the index of the starting node.
Constraints

Output Format

For each of the  queries, print a single line of  space-separated integers denoting the shortest distances to each of the  other nodes from starting position . These distances should be listed sequentially by node number (i.e., ), but should not include node . If some node is unreachable from , print  as the distance to that node.

Sample Input

2
4 2
1 2
1 3
1
3 1
2 3
2
Sample Output

6 6 -1
-1 6
Explanation

We perform the following two queries:

The given graph can be represented as: 
graph1
where our start node, , is node . The shortest distances from  to the other nodes are one edge to node , one edge to node , and an infinite distance to node  (which it's not connected to). We then print node 's distance to nodes , , and  (respectively) as a single line of space-separated integers: 6 6 -1.
The given graph can be represented as: 
graph2
where our start node, , is node . There is only one edge here, so node  is unreachable from node  and node  has one edge connecting it to node . We then print node 's distance to nodes  and  (respectively) as a single line of space-separated integers: -1 6.
Note: Recall that the actual length of each edge is , and we print  as the distance to any node that's unreachable from .
 * @author dwaraknathbs
 *
 */
public class GraphSearch {
    
    static class Vertex {
        int val;
        List<Vertex> edges=new ArrayList<>();
        public Vertex(int k){
            val=k;
        }
        public boolean equals(Vertex v){
            return this.val==v.val;
        }
        
        public int hashcode(){
            return this.val;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner= new Scanner(System.in);
        int numberofqueries=scanner.nextInt();
        int i=0;
        while(i<numberofqueries){
             
            int n=scanner.nextInt();
            int m=scanner.nextInt();
            int j=0;
            Vertex[] vertices= new Vertex[n+1];
            for(int k=1;k<vertices.length;k++){
                vertices[k]=new Vertex(k);
            }
            while(j<m){
               
                int vertex1=scanner.nextInt();
                int vertex2=scanner.nextInt();
                vertices[vertex1].edges.add(vertices[vertex2]);
                vertices[vertex2].edges.add(vertices[vertex1]);
                j++;
            }
            int startingNode=scanner.nextInt();
            Vertex start=vertices[startingNode];
            for(int t=1; t<vertices.length;t++){
                if(t==startingNode) continue;
                int distance=bfs(start,vertices[t]);
                System.out.print(distance+ " ");
            }
            System.out.println();
            i++;
        }
       
    }
    
    static class Pair {
        Vertex v;
        int distance;
        
        public Pair(Vertex v,int distance){
            this.v=v;
            this.distance=distance;
        }
    }
    
    public static int bfs(Vertex v1, Vertex v2){
        
        Queue<Pair> queue= new LinkedList<>();
        HashSet<Vertex> visited= new HashSet<>();
        queue.add(new Pair(v1,0));
        while(!queue.isEmpty()){
            Pair pair=queue.remove();
            Vertex current=pair.v;
            int distance=pair.distance;
            if(visited.contains(current)) continue;
            if(current==v2) return distance;
            visited.add(current);
            for(Vertex v : current.edges){
                queue.add(new Pair(v,distance+6));
            }
            
        }
        
        return -1;
    }
}
