package spreadchat.util;

import javax.swing.ImageIcon;

public class FileIO 
{
    
    // ------------- Public methods
    
    public ImageIcon loadIcon(String path)
    {
        return new ImageIcon(getClass().getResource("/spreadchat/resource/icon/" + path));
    }

}
