package spreadchat.view.component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;

public class MessagePanel extends JEditorPane
{
 
    // ------------- Class attributes
    
    private HTMLDocument doc;
    
    
    // ------------- Public methods
    
    // Default ctor
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MessagePanel() 
    {
        setContentType("text/html");
        setEditable(false);
        setAutoscrolls(true);
        
        initHTMLDoc();
    }
    
    public void addMessage(String user, String message)
    {
        try 
        {
            Element body = doc.getElement(doc.getDefaultRootElement(), StyleConstants.NameAttribute, HTML.Tag.BODY);
            doc.insertBeforeEnd(body, generateMessage(user, message));
        } 
        catch (IOException | BadLocationException ex)
        {
            Logger.getLogger(MessagePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // ------------- Private methods
    
    private void initHTMLDoc()
    {
        setText("<html>" +
                "   <head>" +
                "     <title>SpreadChat</title>" +
                "     <style type=\"text/css\">" +
                "       p { margin: 0 0 5px 0; }" +
                "       p span.nickname { color: #3333CC; font-weight: bold; }" +
                "     </style>" +
                "   </head>" +
                "   <body></body>" +
                "</html>");
        
        doc = (HTMLDocument) getDocument();
    }
    
    private String generateMessage(String user, String message)
    {
        String msg = "<p>" +
                     "  <span class=\"nickname\">" +
                            user +
                     ": </span>" + 
                        message +
                     "</p>";
        
        return msg;
    }
    
}