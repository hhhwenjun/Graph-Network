
public class Edge<T> {

    private VertexInterface<T> end;
    private double weight;
    
    public Edge(VertexInterface<T> end, double weight) {
        this.end = end;
        this.weight = weight;
    }
    
    public VertexInterface<T> getEnd() {
        return end;
    }
    public void setEnd(VertexInterface<T> end) {
        this.end = end;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

}
