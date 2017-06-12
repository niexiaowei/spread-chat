package spreadchat.view.component;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import spread.SpreadGroup;

public class UserPanel extends JList<SpreadGroup>
{
    
    // ------------- Class attributes
    
    private final DefaultListModel model;
    
    
    // ------------- Public methods

    // Default ctor
    public UserPanel() 
    {
        model = new DefaultListModel();
        setModel(model);
        setCellRenderer(new UserPanelCellRenderer());
    }
    
    public void addUser(SpreadGroup user)
    {
        model.addElement(user);
        updateModel();
    }
    
    public void removeUser(SpreadGroup user)
    {
        model.removeElement(user);
        updateModel();
    }
    
    public void setUsers(SpreadGroup[] users)
    {
        model.clear();
        for (SpreadGroup user : users)
        {
            model.addElement(user);
        }
        
        updateModel();
    }
    
    
    // ------------- Private methods
    
    private void updateModel()
    {
        setModel(model);
    }
    
}
