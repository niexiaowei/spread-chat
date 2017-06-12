package spreadchat.model;

public interface ConnectionListener 
{
    
    // ------------- Public methods
    
    public void onConnect(String nickname);
    public void onDisconnect();
    public void onGroupJoin(String groupName);
    
}
