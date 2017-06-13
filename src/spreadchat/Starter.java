package spreadchat;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import spreadchat.model.Connection;
import spreadchat.view.MainWindow;

public class Starter
{

    // ------------- Public static methods
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            try
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {}

            MainWindow mainWindow = new MainWindow();
            Connection.getInstance().addConnectionListener(mainWindow);
            mainWindow.setVisible(true);
        });
    }
    
}