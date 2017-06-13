package spreadchat.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
                JOptionPane.showMessageDialog(this, "Could not disconnect. It's recommended to close and reopen the application.", 
                                                            "Disconnecting...", JOptionPane.ERROR_MESSAGE);
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
                } 
                catch (SpreadException ex) {}
                
                System.exit(0);
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
        lblMessageToSend.setEnabled(enabled);
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
            JOptionPane.showMessageDialog(this, "There was an error when trying to send the message.\nPlease try again.", "Error", JOptionPane.ERROR_MESSAGE);
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
        lblMessageToSend = new javax.swing.JLabel();
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

        lblMessageToSend.setText("Type a message to send...");
        lblMessageToSend.setToolTipText("");
        lblMessageToSend.setEnabled(false);

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
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMessage)
                                    .addComponent(chkSendWithEnterKey))
                                .addGap(0, 383, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsers)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMessageToSend)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsers)
                            .addComponent(lblMessage))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lblMessageToSend)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSendWithEnterKey)
                .addGap(12, 12, 12))
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
    private javax.swing.JLabel lblMessageToSend;
    private javax.swing.JLabel lblUsers;
    private spreadchat.view.component.MessagePanel messagePanel;
    private javax.swing.JMenuItem mnuConnect;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuQuit;
    private javax.swing.JTextArea txtMessage;
    private spreadchat.view.component.UserPanel userPanel;
    // End of variables declaration//GEN-END:variables

}
