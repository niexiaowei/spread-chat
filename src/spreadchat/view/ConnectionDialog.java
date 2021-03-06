package spreadchat.view;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import spread.SpreadException;
import spreadchat.model.Connection;

public class ConnectionDialog extends JDialog 
{

    // ------------- Public methods
    
    // Default ctor
    public ConnectionDialog(Frame parent, boolean modal) 
    {
        super(parent, modal);
        initComponents();
        initListeners();
    }
    
    
    // ------------- Private methods
    
    private void initListeners()
    {
        btnConnect.addActionListener((e) ->
        {
            btnConnectOnActionPerformed(e);
        });
        
        btnCancel.addActionListener((e) -> 
        {
            btnCancelOnActionPerformed(e);
        });

        // JTextFields keyAdapter
        KeyAdapter keyAdapter = new KeyAdapter()
        {
            
            @Override
            public void keyReleased(KeyEvent e) 
            {
                super.keyReleased(e);
                
                boolean enable = true;
                for (Component c : getContentPane().getComponents()) 
                {
                    if (c instanceof JTextField)
                    {
                        if (((JTextField) c).getText().trim().equalsIgnoreCase(""))
                        {
                            enable = false;
                            break;
                        }
                    }
                }
                
                btnConnect.setEnabled(enable);
                
                if (enable && KeyEvent.VK_ENTER == e.getKeyCode())
                {
                    connect();
                }
            }
        };

        txtHostAddress.addKeyListener(keyAdapter);
        txtPort.addKeyListener(keyAdapter);
        txtNickname.addKeyListener(keyAdapter);
        txtGroup.addKeyListener(keyAdapter);
        
        // Dialog listener
        ActionListener escListener = (ActionEvent e) -> 
        {
            setVisible(false);
        };
                
        getRootPane().registerKeyboardAction(
                escListener, 
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
    
    private void btnConnectOnActionPerformed(ActionEvent evt)
    {
        connect();
    }
    
    private void btnCancelOnActionPerformed(ActionEvent evt)
    {
        setVisible(false);
    }
    
    private void connect()
    {
        try 
        {
            Connection.getInstance()
                .connect(
                    InetAddress.getByName(txtHostAddress.getText()),
                    Integer.parseInt(txtPort.getText()),
                    txtNickname.getText(),
                    chkPriority.isSelected(),
                    true
                )
                .joinGroup(txtGroup.getText());
            
            setVisible(false);
        } 
        catch (UnknownHostException ex) 
        {
            JOptionPane.showMessageDialog(this, "The host " + txtHostAddress.getText() + 
                    " could not be located or is unavailable.\nPlease try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (SpreadException ex) 
        {
            JOptionPane.showMessageDialog(this, "There was an error when trying to connect to the Spread server.\nPlease try again.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ------------- Other (NetBeans auto generated stuff)
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtHostAddress = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        txtNickname = new javax.swing.JTextField();
        chkPriority = new javax.swing.JCheckBox();
        btnCancel = new javax.swing.JButton();
        btnConnect = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtGroup = new javax.swing.JTextField();

        setTitle("Connection");
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);

        jLabel1.setDisplayedMnemonic('s');
        jLabel1.setLabelFor(txtHostAddress);
        jLabel1.setText("Server address");

        txtHostAddress.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHostAddress.setText("localhost");

        jLabel2.setDisplayedMnemonic('p');
        jLabel2.setLabelFor(txtPort);
        jLabel2.setText("Port");

        txtPort.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPort.setText("0");

        txtNickname.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        chkPriority.setMnemonic('r');
        chkPriority.setSelected(true);
        chkPriority.setText("Priority connection");

        btnCancel.setText("Cancel");

        btnConnect.setMnemonic('c');
        btnConnect.setText("Connect");
        btnConnect.setEnabled(false);

        jLabel3.setDisplayedMnemonic('n');
        jLabel3.setLabelFor(txtNickname);
        jLabel3.setText("Nickname");

        jLabel4.setDisplayedMnemonic('g');
        jLabel4.setLabelFor(txtNickname);
        jLabel4.setText("Group");

        txtGroup.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txtHostAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(chkPriority)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(txtNickname, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtGroup))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHostAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPriority)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConnect)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConnect;
    private javax.swing.JCheckBox chkPriority;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtGroup;
    private javax.swing.JTextField txtHostAddress;
    private javax.swing.JTextField txtNickname;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables
}
