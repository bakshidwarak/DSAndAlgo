Graphs Introduction
===========================================================================

Key Points
====================

1. Graph
  1. Connected component- able to reach any vertex from any other vertex in the component
  1. Disconnected - No Path( But still part of graph)
1. Types
  1. Directed
    1. Cyclic
    2. Acyclic(aka DAG)
  2. Undirected
1. Sparse Graph- less vertices few edges
1. Dense - Many edges than vertices
1. In Degree- Total number of edges coming into a vertex
1. Out-degree - Total number of edges going out of a vertex
1. Representations
  1. Adjacency List
  1. Adjacency Matrix
1. <b> Interview problems could be of two types </b>
  1. <b>Implicit</b>- No need to create a new graph but use the priciples of graph to solve
  1. <b>Explicit</b> Need to pre-process to create a grph
  
1. Depth First Search
  1. Same as in trees, process all the neighbour's neigbour before processing its neigbour
  1. Paths
    1. Use a HashSet for visited nodes- Hashset provides O(1) lookup.
    1. After one path is complete , need to remove the last element so that paths from other neighbors can be explored
1. Breadth first search
  1. Similar to Trees, enqueue each of the neighbours and its neighbors and so on
  1. <b>In all of the unweighted graph probles , BFS gives the shortest Path</b>
  1. For weighted graphs use Dikshastra's algorithm
  1. Paths
    1. <b> Its important to store the back reference of the node that has an edge to the current node</b>
    
1. What to use?BFS or DFS
  1. If we have to visit every vertex DFS
  1. If shortest path BFS


