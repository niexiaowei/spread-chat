package spreadchat.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import spread.SpreadException;
import spreadchat.model.Connection;
import spreadchat.model.ConnectionListener;
import spreadchat.model.MessageListener;
        
public class MainWindow extends JFrame implements ConnectionListener
{

    // ------------- Class attributes
    
    private boolean isConnected;
    private final ConnectionDialog connectionPanel;
    
    
    // ------------- Public methods
    
    // Default ctor
    public MainWindow()
    {
        isConnected = false;
        connectionPanel = new ConnectionDialog(this, true);
        
        initComponents();
        initListeners();
        
        Connection.getInstance().addMessageListener(new MessageListener(userPanel, messagePanel));
        txtMessage.requestFocus();
    }
    
    
    // ------------- Private methods
    
    private void initListeners()
    {
        mnuConnect.addActionListener((e) -> 
        {
            mnuConnectOnActionPerformed(e);
        });
        
        mnuQuit.addActionListener((e) -> 
        {
            mnuQuitOnActionPerformed(e);
        });
        
        btnSendMessage.addActionListener((e) -> 
        {
            btnSendMessageOnActionPerformed(e);
        });
        
        txtMessage.addKeyListener(new KeyListener() 
        {
            @Override
            public void keyPressed(KeyEvent e) 
            {
                if (KeyEvent.VK_ENTER == e.getKeyCode() &&
                        chkSendWithEnterKey.isSelected()) 
                {
                    sendMessage();
                }
            }
            
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
    
    private void mnuConnectOnActionPerformed(ActionEvent evt)
    {
        if (!isConnected)
        {
            connectionPanel.setVisible(true);
        }
        else
        {
            try 
            {
                Connection.getInstance().disconnect();
            } 
            catch (SpreadException ex) 
            {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void mnuQuitOnActionPerformed(ActionEvent evt)
    {
        if (isConnected)
        {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(
                    this, "Do you really wanna quit?", "Quiting...", JOptionPane.YES_NO_OPTION))
            {
                try 
                {
                    Connection.getInstance().disconnect();
                    System.exit(0);
                } 
                catch (SpreadException ex) 
                {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else System.exit(0);
    }
    
    private void btnSendMessageOnActionPerformed(ActionEvent evt)
    {
        sendMessage();
    }
    
    private void toggleComponents(boolean enabled)
    {
        txtMessage.setEnabled(enabled);
        btnSendMessage.setEnabled(enabled);
        chkSendWithEnterKey.setEnabled(enabled);
    }
    
    private void sendMessage()
    {
        try 
        {
            Connection.getInstance().getGroup().sendMessage(txtMessage.getText());
            txtMessage.setText(null);
            txtMessage.requestFocus();
        }
        catch (SpreadException ex) 
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // ------------- Public overriden methods
    
    @Override
    public void onConnect(String nickname)
    {
        mnuConnect.setText("Disconnect");
        mnuConnect.setMnemonic('d');
        isConnected = true;
        
        setTitle("SpreadChat - Connected as " + nickname);
        toggleComponents(true);
    }

    @Override
    public void onDisconnect()
    {
        mnuConnect.setText("Connect");
        mnuConnect.setMnemonic('c');
        isConnected = false;
        
        setTitle("SpreadChat");
        toggleComponents(false);
    }
    
    @Override
    public void onGroupJoin(String groupName) 
    {
        setTitle(getTitle() + " in the group " + groupName);
    }

    // ------------- Other (NetBeans auto generated stuff)
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        btnSendMessage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messagePanel = new spreadchat.view.component.MessagePanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        userPanel = new spreadchat.view.component.UserPanel();
        lblUsers = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        chkSendWithEnterKey = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuConnect = new javax.swing.JMenuItem();
        mnuQuit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SpreadChat");

        txtMessage.setColumns(20);
        txtMessage.setRows(5);
        txtMessage.setEnabled(false);
        jScrollPane2.setViewportView(txtMessage);

        btnSendMessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/spreadchat/resource/icon/send_32x32.png"))); // NOI18N
        btnSendMessage.setMnemonic('s');
        btnSendMessage.setText("Send");
        btnSendMessage.setEnabled(false);

        jScrollPane1.setViewportView(messagePanel);

        jScrollPane3.setViewportView(userPanel);

        lblUsers.setText("Users in the group");

        lblMessage.setText("Messages log");
        lblMessage.setToolTipText("");

        chkSendWithEnterKey.setMnemonic('k');
        chkSendWithEnterKey.setSelected(true);
        chkSendWithEnterKey.setText("Send with <ENTER> key");
        chkSendWithEnterKey.setToolTipText("");
        chkSendWithEnterKey.setEnabled(false);

        mnuFile.setText("File");

        mnuConnect.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mnuConnect.setText("Connect");
        mnuFile.add(mnuConnect);

        mnuQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        mnuQuit.setText("Quit");
        mnuFile.add(mnuQuit);

        jMenuBar1.add(mnuFile);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkSendWithEnterKey))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSendMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMessage)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsers))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsers)
                    .addComponent(lblMessage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkSendWithEnterKey)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSendMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSendMessage;
    private javax.swing.JCheckBox chkSendWithEnterKey;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblUsers;
    private spreadchat.view.component.MessagePanel messagePanel;
    private javax.swing.JMenuItem mnuConnect;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuQuit;
    private javax.swing.JTextArea txtMessage;
    private spreadchat.view.component.UserPanel userPanel;
    // End of variables declaration//GEN-END:variables

}
