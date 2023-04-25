/**
	A driver for the classes Profile and VTConnect.

	@author
	@author 
	@version  
 */
public class Driver2
{
	public static void main(String[] args)
	{
		System.out.println("Creating profiles and the network.");
		VTConnect m = new VTConnect();

		Profile malcom = new Profile("","malcom@yahoo.com","");
		malcom.setName("Malcom", "X");
		malcom.setStatus("My name is Malcom.");
		
		Profile fannie = new Profile("","fannie@soho.com","");
		fannie.setName("Fannie-lou ", "Hamer");
		fannie.setStatus("My name is Fannie.");
		
		Profile brown = new Profile("","brown@gmail.com","");
		brown.setName("John", "Brown");
		brown.setStatus("My name is John Brown!");
		

		Profile lewis = new Profile("","jlewis@aol.com","");
		lewis.setName("John", "Lewis");
		lewis.setStatus("My name is also John.");
		
		Profile DuBois = new Profile("","jlewis@aol.com","");
		DuBois.setName("W. E. B.", "DuBois");
		DuBois.setStatus("My name is W E B Du Bois.");
		
	    System.out.println("There are "+m.size()+" users in VTConnect");  //The Graph is empty initially
	    
		m.addUser(malcom);
	    m.addUser(fannie);
	    m.addUser(brown);
		m.addUser(lewis);
		m.addUser(DuBois);
		  
		System.out.println("There are "+m.size()+" users in VTConnect");   //note the number of users here
		
      
		malcom.display();
		fannie.display();
        brown.display();
        lewis.display();
        DuBois.display();

		System.out.println("-------------------------------------\n");
		System.out.println("Creating friendships.\n");

		
		System.out.println("There are "+m.numberOfConnections()+" connections in VTConnect");
		m.createFriendship(malcom, fannie);
		m.createFriendship(fannie, brown);
		m.createFriendship(lewis, fannie);
		m.createFriendship(brown, lewis);
		m.createFriendship(DuBois, fannie);
     
		System.out.println("There are "+m.numberOfConnections()+" connections in VTConnect");   //note the number of connections here
		//displaying VTConnect
		m.traverse(malcom);

		System.out.println("-------------------------------------\n");
		System.out.println("Changing statuses / names.\n");
		
		lewis.setStatus("Just got married!");
		fannie.setStatus("Now Mrs. Smith!");
		fannie.setName("Fannie", "Smith");

		fannie.display();
        lewis.display();
        
        
       
        System.out.println("-------------------------------------\n");
		System.out.println("Checking Friendships.\n");
		
		Profile friendless1 = new Profile();
		friendless1.setName("Nameless", "1");
		friendless1.setStatus("My name is nameless1!");
		System.out.println(m.exists(friendless1));
		m.addUser(friendless1);
		System.out.println(m.exists(brown));
		System.out.println(m.exists(DuBois));
		System.out.println(m.hasFriendship(friendless1, fannie));
		System.out.println(m.hasFriendship(fannie, brown));
		System.out.println(m.hasFriendship(malcom, lewis));
		System.out.println(m.hasFriendship(friendless1, fannie));
		System.out.println(m.hasFriendship(brown, lewis));
		System.out.println(m.exists(friendless1));
		System.out.println(m.hasFriendship(lewis, fannie));
		
		System.out.println("---------------Suggestion----------------------------------");
		
		Profile friendless2 = new Profile("","xx@happy.net","");
		friendless2.setName("Nameless", "2");
		friendless2.setStatus("My name is nameless2!");
		m.addUser(friendless2);
		
		Profile friendless3 = new Profile();
        friendless3.setName("Nameless", "3");
        friendless3.setStatus("My name is nameless3!");
        m.addUser(friendless3);
        System.out.println(m.exists(friendless3));
        
		m.createFriendship(friendless1, friendless2);
		m.createFriendship(friendless2, lewis);
		m.createFriendship(friendless2, friendless3);
		
		System.out.println("There are "+m.numberOfConnections()+" connections in VTConnect");
		
		System.out.println(m.friendSuggestion(friendless2));
		System.out.println(m.friendSuggestion(brown));
		
		System.out.println(m.friendSuggestion(lewis));
		
		System.out.println("---------------Distance----------------------------------");
		
		System.out.println(m.friendshipDistance(fannie,brown));
		System.out.println(m.friendshipDistance(fannie,friendless1));
		System.out.println(m.friendshipDistance(fannie,lewis));
		System.out.println(m.friendshipDistance(malcom,friendless2));
		System.out.println(m.friendshipDistance(malcom,friendless3));
		System.out.println(m.friendshipDistance(DuBois,friendless3));
		
	  	m.removeUser(friendless2);	  
	  	m.removeFriendship(fannie, brown);
	  	
		System.out.println(m.friendshipDistance(malcom,friendless1));
		System.out.println(m.friendshipDistance(malcom,friendless2));
		System.out.println(m.friendshipDistance(fannie,brown));
		
		
	} // end main
} // end Driver2

/*
Creating profiles and the network.
There are 0 users in VTConnect
There are 4 users in VTConnect
Name: Malcom X
    Email: malcom@yahoo.com
    Status: My name is Malcom.
    Number of friend profiles: 0
Friends:
Name: Fannie-lou  Hamer
    Email: fannie@soho.com
    Status: My name is Fannie.
    Number of friend profiles: 0
Friends:
Name: John Brown
    Email: brown@gmail.com
    Status: My name is John Brown!
    Number of friend profiles: 0
Friends:
Name: John Lewis
    Email: jlewis@aol.com
    Status: My name is also John.
    Number of friend profiles: 0
Friends:
Name: W. E. B. DuBois
    Email: jlewis@aol.com
    Status: My name is W E B Du Bois.
    Number of friend profiles: 0
Friends:
-------------------------------------

Creating friendships.

There are 0 connections in VTConnect
There are 4 connections in VTConnect
Name: Malcom X
    Email: malcom@yahoo.com
    Status: My name is Malcom.
    Number of friend profiles: 1
Friends:
    Fannie-lou  Hamer

Name: Fannie-lou  Hamer
    Email: fannie@soho.com
    Status: My name is Fannie.
    Number of friend profiles: 3
Friends:
    Malcom X
    John Brown
    John Lewis

Name: John Brown
    Email: brown@gmail.com
    Status: My name is John Brown!
    Number of friend profiles: 2
Friends:
    Fannie-lou  Hamer
    John Lewis

Name: John Lewis
    Email: jlewis@aol.com
    Status: My name is also John.
    Number of friend profiles: 2
Friends:
    Fannie-lou  Hamer
    John Brown

-------------------------------------

Changing statuses / names.

Name: Fannie Smith
    Email: fannie@soho.com
    Status: Now Mrs. Smith!
    Number of friend profiles: 3
Friends:
    Malcom X
    John Brown
    John Lewis
Name: John Lewis
    Email: jlewis@aol.com
    Status: Just got married!
    Number of friend profiles: 2
Friends:
    Fannie Smith
    John Brown
-------------------------------------

Checking Friendships.

false
true
false
false
true
false
false
true
true
true
---------------Suggestion----------------------------------
false
There are 6 connections in VTConnect
[Name: Fannie Smith
    Email: fannie@soho.com
    Status: Now Mrs. Smith!
    Number of friend profiles: 3
, Name: John Brown
    Email: brown@gmail.com
    Status: My name is John Brown!
    Number of friend profiles: 2
]
[Name: Malcom X
    Email: malcom@yahoo.com
    Status: My name is Malcom.
    Number of friend profiles: 1
, Name: Nameless 2
    Email: xx@happy.net
    Status: My name is nameless2!
    Number of friend profiles: 2
]
[Name: Malcom X
    Email: malcom@yahoo.com
    Status: My name is Malcom.
    Number of friend profiles: 1
, Name: Nameless 1
    Email: 
    Status: My name is nameless1!
    Number of friend profiles: 1
]
---------------Distance----------------------------------
1
3
1
3
-1
-1
-1
-1
2

 */
