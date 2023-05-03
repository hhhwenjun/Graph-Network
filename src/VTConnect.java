import java.util.*;

public class VTConnect {
    
    private Graph<Profile> graph;
    
    public VTConnect() {
        graph = new Graph<Profile>();
    }
    
    public void addUser(Profile p) {
        graph.addVertex(p);
    }
    
    public void removeUser(Profile p) {
        graph.removeVertex(p);
    }
    
    public boolean createFriendship(Profile a, Profile b) {
        return graph.addEdge(a, b);
    }
    
    public boolean removeFriendship(Profile a, Profile b) {
        return graph.removeEdge(a, b);
    }
    
    public boolean hasFriendship(Profile a, Profile b) {
        return graph.hasEdge(a, b);
    }
    
    public void traverse(Profile startPoint) {
        Queue<Profile> queue = graph.getBreadthFirstTraversal(startPoint);
        queue.stream().map(Profile::toString).forEach(System.out::println);
    }
    
    public boolean exists(Profile user) {
        return graph.getVertices().containsKey(user);
    }
    
    public int size() {
        return graph.getVertices().size();
    }
    
    public int numberOfConnections() {
        return graph.getNumberOfEdges();
    }
    
    public List<Profile> friendSuggestion(Profile user){
        VertexInterface<Profile> currUser = graph.getVertices().get(user);
        currUser.getUnvisitedNeighbor();
    }

}
