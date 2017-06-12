package spreadchat.model;

import spread.SpreadConnection;
import spread.SpreadException;
import spread.SpreadGroup;
import spread.SpreadMessage;

public class Group 
{

    // ------------- Class attributes
    
    private final SpreadConnection connection;
    private final SpreadGroup group;
    private boolean isConnected;
    
    
    // ------------- Public methods
    
    // Default ctor
    public Group(SpreadConnection connection)
    {
        this.connection = connection;
        group = new SpreadGroup();
        isConnected = false;
    }
    
    public void joinGroup(String groupName) throws SpreadException
    {
        System.out.print("Entering group " + groupName + "... ");
        leaveGroup();
        group.join(connection, groupName);
        isConnected = true;
        System.out.println("OK.");
    }
    
    public void leaveGroup() throws SpreadException
    {
        if (isConnected)
        {
            System.out.print("Leaving group... ");
            group.leave();
            isConnected = false;
            System.out.println("OK.");
        }
    }
    
    public void sendMessage(String message) throws SpreadException
    {
        SpreadMessage spreadMessage = new SpreadMessage();
        spreadMessage.setData(message.getBytes());
        spreadMessage.addGroup(group);
        spreadMessage.setReliable();
        
        connection.multicast(spreadMessage);
    }
    
}
