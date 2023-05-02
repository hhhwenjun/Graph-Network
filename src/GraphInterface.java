import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public interface GraphInterface<T> {
    
    /**
     * Adds a given vertex to this graph. If vertexLabel is null, it returns
     * false.
     * 
     * @param <T>
     * @param vertexLabel
     * @return
     */
    boolean addVertex(T vertexLabel);


    /**
     * Removes a vertex with the given vertexLabel from this graph and returns
     * the removed vertex.
     * If vertex does not exist, it will return null.
     * 
     * @param <T>
     * @param vertexLabel
     * @return
     */
    VertexInterface<T> removeVertex(T vertexLabel);


    /**
     * Adds a weighted edge between two given distinct vertices that are
     * currently in this graph.
     * The desired edge must not already be in the graph. Note that the graph is
     * undirected graph.
     * 
     * @param <T>
     * @param begin
     * @param end
     * @param edgeWeight
     * @return
     */
    boolean addEdge(T begin, T end, double edgeWeight);


    /**
     * Adds an unweighted edge between two given distinct vertices that are
     * currently in this graph.
     * The desired edge must not already be in the graph.
     * 
     * @param <T>
     * @param begin
     * @param end
     * @return
     */
    boolean addEdge(T begin, T end);


    /**
     * Removes a weighted edge between two given distinct vertices that are
     * currently in this graph.
     * The desired edge must already be in the graph. It returns true if the
     * removal is successful, false otherwise.
     * 
     * @param <T>
     * @param begin
     * @param end
     * @param edgeWeight
     * @return
     */
    boolean removeEdge(T begin, T end, double edgeWeight);


    /**
     * Removes an unweighted edge between two given distinct vertices that are
     * currently in this graph.
     * The desired edge must already be in the graph. It returns true if the
     * removal is successful, false otherwise
     * 
     * @param <T>
     * @param begin
     * @param end
     * @return
     */
    boolean removeEdge(T begin, T end);


    /**
     * Sees whether an undirected edge exists between two given vertices.
     * 
     * @param <T>
     * @param begin
     * @param end
     * @return
     */
    boolean hasEdge(T begin, T end);


    /**
     * This method returns the number of Vertices in this graph.
     * 
     * @return
     */
    int getNumberOfVertices();


    /**
     * This method returns the number of undirected Edges in this graph.
     * 
     * @return
     */
    int getNumberOfEdges();


    /**
     * This method returns true, if this graph is empty, false otherwise.
     * 
     * @return
     */
    boolean isEmpty();


    /**
     * This method returns the list of all vertices in the graph.
     * If the graph is empty, it returns null.
     * 
     * @param <T>
     * @return
     */
    List<VertexInterface<T>> listVertices();


    /**
     * This method returns the Key and Value pairs of all vertices in the graph.
     * 
     * @param <T>
     * @return
     */
    Map<T, VertexInterface<T>> getVertices();


    /**
     * Performs a breadth- first traversal of a graph and returns the queue that
     * contains the result.
     * Empty queue can be returned.
     * 
     * @param <T>
     * @param origin
     * @return
     */
    Queue<T> getBreadthFirstTraversal(T origin);


    /**
     * Returns the shortest distance between the origin and destination. If a
     * path does not exist,
     * it returns the maximum integer (to simulate infinity).
     * 
     * @param <T>
     * @param origin
     * @param destination
     * @param path
     * @return
     */
    int getShortestPath(T origin, T destination, Stack<T> path);


    /**
     * clears the graph.
     */
    void clear();
}
