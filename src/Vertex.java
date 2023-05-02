import java.util.*;
import java.util.stream.*;

public class Vertex<T> implements VertexInterface<T> {
    
    private T label;
    private boolean visited;
    private VertexInterface<T> previousVertex;
    private double cost;
    private List<Edge<T>> edgeList;
    
    public Vertex(T vertexLabel) {
        label = vertexLabel;
        visited = false;
        cost = 0.0;
        previousVertex = null;
        edgeList = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getLabel() {
        return label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfNeighbors() {
        Set<VertexInterface<T>> neighbors = new HashSet<>();
        
        for (Edge<T> single : edgeList) {
            VertexInterface<T> end = single.getEnd();
            if (!neighbors.contains(end)) {
                neighbors.add(end);
            } 
        }
        return neighbors.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit() {
       visited = true;       
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unvisit() {
        visited = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisited() {
        return visited;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
        
        if (endVertex.getLabel().equals(label)) return false;
        
        if (!checkIfConnect(endVertex, edgeWeight)) {
            edgeList.add(new Edge<T>(endVertex, edgeWeight));
            return true;
        }
        
        return false;
    }
    
    private boolean checkIfConnect(VertexInterface<T> endVertex, double edgeWeight) {
        
        for (Edge<T> single : edgeList) {
            VertexInterface<T> curr = single.getEnd();
            if (curr.getLabel().equals(endVertex.getLabel()) && curr.getCost() == endVertex.getCost()) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean connect(VertexInterface<T> endVertex) {
        
        if (endVertex.getLabel().equals(label)) return false;     
        if (!checkIfConnect(endVertex, -1.0)) {
            edgeList.add(new Edge<T>(endVertex, -1.0));
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean disconnect(VertexInterface<T> endVertex, double edgeWeight) {
        for (Edge<T> single : edgeList) {
            VertexInterface<T> curr = single.getEnd();
            if (curr.getLabel().equals(endVertex.getLabel()) && curr.getCost() == endVertex.getCost()) {
                return edgeList.remove(single);
            }
        }
        // item not found
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean disconnect(VertexInterface<T> endVertex) {
        for (Edge<T> single : edgeList) {
            VertexInterface<T> curr = single.getEnd();
            if (curr.getLabel().equals(endVertex.getLabel()) && curr.getCost() == -1.0) {
                return edgeList.remove(single);
            }
        }
        // item not found
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<VertexInterface<T>> getNeighborIterator() {
        List<VertexInterface<T>> neighborList = edgeList.stream()
            .map(Edge<T>::getEnd)
            .collect(Collectors.toList());
      
        return neighborList.iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNeighbor() {

        return edgeList.size() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VertexInterface<T> getUnvisitedNeighbor() {
        List<VertexInterface<T>> neighborList = edgeList.stream()
            .map(Edge<T>::getEnd)
            .collect(Collectors.toList());
        
        Optional<VertexInterface<T>> unvisitedNeighbor = neighborList.stream()
            .filter(neighbor -> !neighbor.isVisited())
            .findFirst();
        
        return unvisitedNeighbor.orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        this.previousVertex = predecessor;      
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VertexInterface<T> getPredecessor() {

        return previousVertex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPredecessor() {
        return this.previousVertex != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCost(double newCost) {
        this.cost = newCost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void disconnectAll(VertexInterface<T> endVertex) {
        for (Edge<T> edge : edgeList) {
            if (edge.getEnd().equals(endVertex)) {
                edgeList.remove(edge);
            }
        }
    }

    @Override
    public List<Edge<T>> getEdges() {
        return edgeList;
    }
    
}
