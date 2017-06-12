package spreadchat.view.component;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class MessagePanel extends JEditorPane
{
 
    // ------------- Public methods
    
    // Default ctor
    public MessagePanel() 
    {
        initComponent();
    }
    
    public void addMessage(String user, String message)
    {
        message = message.trim();
        
        // TODO: Treat this exception
        try 
        {
            // User
            SimpleAttributeSet userSas = new SimpleAttributeSet();
            StyleConstants.setForeground(userSas, Color.BLUE);
            StyleConstants.setBold(userSas, true);
            StyleConstants.setAlignment(userSas, StyleConstants.ALIGN_LEFT);
            getDocument().insertString(getDocument().getLength(), user + ": ", userSas);
            
            // Message
            SimpleAttributeSet messageSas = new SimpleAttributeSet();
            StyleConstants.setAlignment(messageSas, StyleConstants.ALIGN_LEFT);
            getDocument().insertString(getDocument().getLength(), message + "\n", messageSas);
        } 
        catch (BadLocationException ex) 
        {
            Logger.getLogger(MessagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // ------------- Private methods
    
    private void initComponent()
    {
        setContentType("text/html");
        setEditable(false);
        setAutoscrolls(true);
    }
    
}
