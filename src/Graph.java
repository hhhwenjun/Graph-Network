import java.util.*;
import java.util.stream.Collectors;

public class Graph<T> implements GraphInterface<T> {

    private Map<T, VertexInterface<T>> vertices;
    private int edgeNum;

    public Graph() {
        vertices = new HashMap<>();
        edgeNum = 0;
    }


    @Override
    public boolean addVertex(T vertexLabel) {
        if (vertexLabel == null)
            return false;
        vertices.put(vertexLabel, new Vertex<T>(vertexLabel));
        return true;
    }


    @Override
    public VertexInterface<T> removeVertex(T vertexLabel) {
        if (!vertices.containsKey(vertexLabel)) return null;
        VertexInterface<T> removedItem = vertices.get(vertexLabel);
        
        // remove all the nodes that has edge with it
        Iterator<VertexInterface<T>> removeItemIt = removedItem.getNeighborIterator();
        List<VertexInterface<T>> neighbors = new ArrayList<>();
        while(removeItemIt.hasNext()) {
            neighbors.add(removeItemIt.next());
        }
        
        for (VertexInterface<T> neighbor : neighbors) {
            neighbor.disconnectAll(removedItem);
        }
        return removedItem;
    }


    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        
        VertexInterface<T> beginVertex = null;
        VertexInterface<T> endVertex = null;
        // key not found
        if (!vertices.containsKey(begin) || !vertices.containsKey(end)) return false;
        
        for (Map.Entry<T, VertexInterface<T>> entry : vertices.entrySet()) {
            if (entry.getKey().equals(begin)) {
                beginVertex = entry.getValue();
            }
            if (entry.getKey().equals(end)) {
                endVertex = entry.getValue();
            }
        }
        
        if (beginVertex.connect(endVertex, edgeWeight) && endVertex.connect(beginVertex, edgeWeight)) {
            edgeNum++;
            return true;
        }
        
        return false;
    }


    @Override
    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 1.0);
    }


    @Override
    public boolean removeEdge(T begin, T end, double edgeWeight) {
        VertexInterface<T> beginVertex = null;
        VertexInterface<T> endVertex = null;
        // key not found
        if (!vertices.containsKey(begin) || !vertices.containsKey(end)) return false;
        
        for (Map.Entry<T, VertexInterface<T>> entry : vertices.entrySet()) {
            if (entry.getKey().equals(begin)) {
                beginVertex = entry.getValue();
            }
            if (entry.getKey().equals(end)) {
                endVertex = entry.getValue();
            }
        }
        
        if (beginVertex.disconnect(endVertex, edgeWeight) && endVertex.disconnect(beginVertex, edgeWeight)) {
            edgeNum--;
            return true;
        }
        return false;
    }


    @Override
    public boolean removeEdge(T begin, T end) {
        return removeEdge(begin, end, 1.0);
    }


    @Override
    public boolean hasEdge(T begin, T end) {
        VertexInterface<T> beginVertex = null;
        // key not found
        if (!vertices.containsKey(begin)) return false;
        
        for (Map.Entry<T, VertexInterface<T>> entry : vertices.entrySet()) {
            if (entry.getKey().equals(begin)) {
                beginVertex = entry.getValue();
            }
        }
        
        Iterator<VertexInterface<T>> iter = beginVertex.getNeighborIterator();
        while(iter.hasNext()) {
            if (iter.next().getLabel().equals(end)) return true;
        }
        return false;
    }


    @Override
    public int getNumberOfVertices() {
        return vertices.size();
    }


    @Override
    public int getNumberOfEdges() {     
        return edgeNum;
    }

    @Override
    public boolean isEmpty() {
        return vertices.size() == 0;
    }

    @Override
    public List<VertexInterface<T>> listVertices() {
        return vertices.values().stream().collect(Collectors.toList());
    }

    @Override
    public Map<T, VertexInterface<T>> getVertices() {
        return vertices;
    }

    @Override
    public Queue<T> getBreadthFirstTraversal(T origin) {
        Queue<T> queue = new LinkedList<>();
        Queue<T> traversalList = new LinkedList<>();
        queue.add(origin);
        
        while(!queue.isEmpty()) {
            T current = queue.poll();
            VertexInterface<T> currNode = vertices.get(current);
            if (currNode.isVisited()) continue;
            currNode.visit();
            traversalList.add(current);
            Iterator<VertexInterface<T>> it = currNode.getNeighborIterator();
            while(it.hasNext()) {
                queue.add(it.next().getLabel());
            }
        }
        return traversalList;
    }

    @Override
    public int getShortestPath(T origin, T destination, Stack<T> path) {
        
        for (VertexInterface<T> vertex : vertices.values()) {
            vertex.unvisit();
        }
        // Create a map to store the distance from the origin vertex to each
        // vertex
        Map<T, Integer> distances = new HashMap<>();
        // Create a map to store the previous vertex that leads to the current
        // vertex
        Map<T, T> previousVertices = new HashMap<>();

        // Create a priority queue to store vertices to visit, using a
        // comparator to prioritize based on distance
        PriorityQueue<VertexInterface<T>> queue = new PriorityQueue<>((a, b) -> 
        distances.get(a.getLabel()) - distances.get(b.getLabel()));
        // Initialize distances and previousVertices
        for (VertexInterface<T> vertex : vertices.values()) {
            distances.put(vertex.getLabel(), Integer.MAX_VALUE);
            previousVertices.put(vertex.getLabel(), null);
        }
        // Set the distance of the origin vertex to 0 and add it to the priority
        // queue
        distances.put(origin, 0);
        queue.add(vertices.get(origin));

        while (!queue.isEmpty()) {
            VertexInterface<T> current = queue.poll(); // Get the vertex with the smallest
                                      // distance
            current.visit();
            if (current.getLabel().equals(destination)) {
                // Found the destination vertex, so construct the path stack
                constructPath(origin, destination, previousVertices, path);
                return distances.get(destination);
            }

            // Visit each neighbor of the current vertex
            for (Edge<T> edge : current.getEdges()) {
                if (edge.getEnd().isVisited()) continue;
                int distance = (int)(distances.get(current.getLabel()) + edge.getWeight());

                // Update the distance and previous vertex if a shorter path is
                // found
                if (distance < distances.get(edge.getEnd().getLabel())) {
                    distances.put(edge.getEnd().getLabel(), distance);
                    previousVertices.put(edge.getEnd().getLabel(), current.getLabel());
                    queue.add(edge.getEnd());
                }
            }
        }
        return Integer.MAX_VALUE;
    }
    

    // Helper method to construct the path stack by backtracking from
    // destination to origin
    private void constructPath(
        T origin,
        T destination,
        Map<T, T> previousVertices,
        Stack<T> path) {
        T current = destination;
        while (current != null) {
            path.push(current);
            current = previousVertices.get(current);
        }
    }

    @Override
    public void clear() {
        edgeNum = 0;
        vertices = new HashMap<>();
    }

}
