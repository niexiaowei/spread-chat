package spreadchat.model;

import spread.AdvancedMessageListener;
import spread.MembershipInfo;
import spread.SpreadGroup;
import spread.SpreadMessage;
import spreadchat.view.component.MessagePanel;
import spreadchat.view.component.UserPanel;

public class MessageListener implements AdvancedMessageListener
{
    
    // ------------- Class attributes
    
    private boolean userListCreated;
    private final UserPanel userPanel;
    private final MessagePanel messagePanel;

    
    // ------------- Public methods
    
    public MessageListener(UserPanel userPanel, MessagePanel messagePanel) 
    {
        userListCreated = false;
        this.userPanel = userPanel;
        this.messagePanel = messagePanel;
    }

    
    // ------------- Public overriden methods
    
    @Override
    public void regularMessageReceived(SpreadMessage sm) 
    {
        //System.out.println("Regular message received");
        messagePanel.addMessage(getUser(sm.getSender()), new String(sm.getData()));
    }

    @Override
    public void membershipMessageReceived(SpreadMessage sm) 
    {
        //System.out.println("Membership message received");
        MembershipInfo membershipInfo = sm.getMembershipInfo();
        SpreadGroup user = null;
        
        if (!userListCreated)
        {
            userPanel.setUsers(membershipInfo.getMembers());
            userListCreated = true;
        }
        else if (membershipInfo.isCausedByJoin())
        {
            user = membershipInfo.getJoined();
            messagePanel.addInformationMessage(getUser(user), "entered in the group.");
            userPanel.addUser(user);
        }
        else if (membershipInfo.isCausedByLeave() && 
                null != (user = membershipInfo.getLeft()))
        {
            messagePanel.addInformationMessage(getUser(user), "left the group.");
            userPanel.removeUser(user);
        }
        else if (membershipInfo.isCausedByDisconnect() && 
                null != (user = membershipInfo.getDisconnected()))
        {
            messagePanel.addInformationMessage(getUser(user), "was disconnected.");
            userPanel.removeUser(user);
        }
    }
    
    private String getUser(SpreadGroup user)
    {
        String[] userInfo = user.toString().split("#");
        return userInfo[1];
    }
    
}
