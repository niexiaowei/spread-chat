package spreadchat.model;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import spread.AdvancedMessageListener;
import spread.SpreadConnection;
import spread.SpreadException;

public class Connection
{
    
    // ------------- Class attributes

    private static Connection instance;
    private final SpreadConnection connection;
    private final Group group;
    private final List<ConnectionListener> listeners;
    private AdvancedMessageListener advancedMessageListener;
    
    // ------------- Private methods
    
    // Default ctor
    private Connection() 
    {
        connection = new SpreadConnection();
        group = new Group(connection);
        listeners = new ArrayList<>();
    }

    
    // ------------- Public methods
    
    public Connection connect(InetAddress address, int port, String privateName, boolean priority, boolean groupMembership) throws SpreadException
    {
        System.out.print("Estabilishing connection... ");
        if (connection.isConnected())
        {
            connection.disconnect();
        }
        
        connection.connect(address, port, privateName, priority, groupMembership);
        
        System.out.println("OK.");
        System.out.print("Notifying listeners... ");
        
        listeners.forEach((observer) -> 
        {
            observer.onConnect(privateName);
        });
        
        System.out.println("OK.");
        
        return this;
    }
    
    public void addConnectionListener(ConnectionListener l)
    {
        listeners.add(l);
    }
    
    public void addMessageListener(AdvancedMessageListener l)
    {
        advancedMessageListener = l;
        connection.add(l);
    }
    
    public void joinGroup(String groupName) throws SpreadException
    {
        group.joinGroup(groupName);
        
        System.out.print("Notifying listeners... ");
        listeners.forEach((observer) ->
        {
            observer.onGroupJoin(groupName);
        });
        System.out.println("OK.");
    }
    
    public void leaveGroup() throws SpreadException
    {
        group.leaveGroup();
    }
    
    public Group getGroup()
    {
        return group;
    }
    
    public void disconnect() throws SpreadException
    {
        if (connection.isConnected())
        {
            leaveGroup();

            System.out.print("Finishing connection... ");
            
            if (null != advancedMessageListener)
            {
                connection.remove(advancedMessageListener);
            }
            
            connection.disconnect();
            System.out.println("OK.");

            System.out.print("Notifying listeners... ");
            listeners.forEach((observable) -> 
            {
                observable.onDisconnect();
            });
            System.out.println("OK.");
        }
    }
    
    // ------------- Public static methods
    
    public static Connection getInstance()
    {
        if (null == instance)
        {
            instance = new Connection();
        }
        
        return instance;
    }
    
}