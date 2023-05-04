import java.util.*;

public class Profile {
    
    private String name;
    private String email;
    private String status;
    private List<Profile> friendProfiles;
    
    public Profile() {
        name = "";
        email = "";
        status = "";
        friendProfiles = new ArrayList<>();
    }
    
    public Profile(String name, String email, String status, List<Profile> friendProfiles) {
        this.name = name;
        this.email = email;
        this.status = status;
        this.friendProfiles = friendProfiles;
    }
    
    public Profile(String name, String email, String status) {
        this.name = name;
        this.email = email;
        this.status = status;
        this.friendProfiles = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String firstName, String lastName) {
        this.name = firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Profile> getFriendProfiles() {
        return friendProfiles;
    }

    public void setFriendProfiles(List<Profile> friendProfiles) {
        this.friendProfiles = friendProfiles;
    }
    
    public String toString() {
        return "Name: " + name + "\n\tEmail: " + email + 
            "\n\tStatus: " + status + "\n\tNumber of friend profiles: " + friendProfiles.size();
    }

    public void addFriend(Profile user) {
        friendProfiles.add(user);
    }
    
    public boolean unFriend(Profile user) {
        for (int i = 0; i < friendProfiles.size(); i++) {
            if (friendProfiles.get(i).equals(user)) {
                friendProfiles.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public void display() {
        System.out.println(this.toString());
        System.out.println("Friends:");
        for (Profile friend : friendProfiles) {
            System.out.println("\t" + friend.getName());
        }
        System.out.println("");
    }
}
