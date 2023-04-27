import java.util.Iterator;

public interface VertexInterface<T> {
    
    /**
     * Gets this vertex’s label.
     * @return
     */
    T getLabel();
    
    /**
     * Returns the number of neighbors of this vertex.
     * @return
     */
    int getNumberOfNeighbors();
    
    
    /**
     * Marks this vertex as visited.
     */
    void visit();
    
    /**
     * Removes this vertex’s visited mark.
     */
    void unvisit();
    
    /**
     * Returns true if the vertex is visited, false otherwise.
     * @return
     */
    boolean isVisited();
    
    /**
     * Connects this vertex and endVertex with a weighted edge. 
     * The two vertices cannot be the same, and must not already have this edge between them. 
     * Two vertices are equal (same)if their labels are equal (same). 
     * Returns true if the connection is successful, false otherwise.
     * 
     * @param endVertex
     * @param edgeWeight
     * @return
     */
    boolean connect(VertexInterface<T> endVertex, double edgeWeight);
    
    /**
     * Connects this vertex and endVertex with a unweighted edge.
     * The two vertices cannot be the same, and must not already have this edge between them.
     * Two vertices are equal (same)if their labels are equal (same). 
     * Returns true if the connection is successful, false otherwise.
     * 
     * @param endVertex
     * @return
     */
    boolean connect(VertexInterface<T> endVertex);
    
    /**
     * Disconnects this vertex from a given vertex with a weighted edge, i.e., removes the edge.
     * The Edge should exist in order to be disconnected. Returns true if the disconnection is successful, false otherwise.
     * 
     * @param endVertex
     * @param edgeWeight
     * @return
     */
    boolean disconnect(VertexInterface<T> endVertex, double edgeWeight);
    
    /**
     * Disconnects this vertex from a given vertex with an unweighted edge. 
     * The Edge should exist in order to be disconnected. Returns true if the 
     * disconnection is successful, false otherwise.
     * @param endVertex
     * @return
     */
    boolean disconnect(VertexInterface<T> endVertex);
    
    /**
     * creates an iterator of this vertex's neighbors by 
     * following all edges that begin at this vertex.
     * @return
     */
    Iterator<VertexInterface<T>> getNeighborIterator();
    
    /**
     * Sees whether this vertex has at least one neighbor.
     * @return
     */
    boolean hasNeighbor();
    
    /**
     * Gets an unvisited neighbor, if any, of this vertex.
     * @return
     */
    VertexInterface<T> getUnvisitedNeighbor();
    
    /**
     * Records the previous vertex on a path to this vertex.
     * @param predecessor
     */
    void setPredecessor(VertexInterface<T> predecessor);
    
    /**
     * Gets the recorded predecessor of this vertex.
     * @return
     */
    VertexInterface<T> getPredecessor();
    
    /**
     * Sees whether a predecessor was recorded for this vertex.
     * @return
     */
    boolean hasPredecessor();
    
    /**
     * Records the cost of a path to this vertex.
     * @param newCost
     */
    void setCost(double newCost);
    
    /**
     * Returns the cost of a path to this vertex.
     * @return
     */
    double getCost();

}
