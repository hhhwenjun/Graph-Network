import java.util.*;
import java.util.stream.Collectors;

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
        a.addFriend(b);
        b.addFriend(a);
        return graph.addEdge(a, b);
    }
    
    public boolean removeFriendship(Profile a, Profile b) {
        a.unFriend(b);
        b.unFriend(a);
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
        List<Profile> potentialFriends = new ArrayList<>();
        
        List<VertexInterface<Profile>> currFriends = new ArrayList<>();
        Set<Profile> potentialFriendSet = new HashSet<>();
        VertexInterface<Profile> currUser = graph.getVertices().get(user);
        Iterator<VertexInterface<Profile>> it = currUser.getNeighborIterator();
        while(it.hasNext()) {
            currFriends.add(it.next());
        }
        Set<Profile> currFriendSet = currFriends.stream().map(VertexInterface<Profile>::getLabel).collect(Collectors.toSet());
        for (VertexInterface<Profile> friend : currFriends) {
            Iterator<VertexInterface<Profile>> curr = friend.getNeighborIterator();
            while(curr.hasNext()) {
                VertexInterface<Profile> potentialFriend = curr.next();
                if (!currFriendSet.contains(potentialFriend.getLabel()) &&
                    !potentialFriendSet.contains(potentialFriend.getLabel())) {
                    potentialFriends.add(potentialFriend.getLabel());
                    potentialFriendSet.add(potentialFriend.getLabel());
                }
            }
        }
        return potentialFriends;
    }
    
    public int friendshipDistance(Profile a, Profile b) {
        return graph.getShortestPath(a, b, new Stack<Profile>()) == Integer.MAX_VALUE ? -1 
            : graph.getShortestPath(a, b, new Stack<Profile>());
    }

}
