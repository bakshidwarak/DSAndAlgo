Given a graph write a program to clone the graph

Key Points
====================

1. Explicit Graph problem
1. Visited -> Instead of HashSet use a Hashmap to map from original to the cloned node
2. If the hashmap already contains the original, return the value from the map- This will take care of cycles
3. Do not make the visited Map as global. 


Key Questions to Ask
====================
1. 

Approaches
====================
1. Idea is to do a dfs and have a map of original to the cloned nodes
2. Iterate the neighbors and add to the cloned neighbour the clones of the neighbors
 
