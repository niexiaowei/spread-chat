package spreadchat.view.component;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;
import spread.SpreadGroup;
import spreadchat.util.FileIO;

public class UserPanelCellRenderer extends DefaultListCellRenderer
{
    
    // ------------- Class attributes
    
    //private static final long serialVersionUID = -7799441088157759804L;
    private final JLabel label;
    
    
    // ------------- Public methods

    // Default ctor
    UserPanelCellRenderer()
    {
        label = new JLabel();
        label.setBorder(new EmptyBorder(3, 3, 3, 3));
        label.setOpaque(true);
    }
    
    
    // ------------- Public overriden methods

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean selected, boolean expanded) 
    {
        String[] spreadUser = ((SpreadGroup) value).toString().split("#");
        String user = spreadUser[1] + "@" + spreadUser[2];
        
        label.setIcon(new FileIO().loadIcon("user_16x16.png"));
        label.setText(user);
        label.setToolTipText(user);

        if (selected) 
        {
            label.setBackground(Color.GRAY);
            label.setForeground(Color.WHITE);
        } 
        else 
        {
            label.setBackground(Color.WHITE);
            label.setForeground(Color.BLACK);
        }

        return label;
    }
    
}
