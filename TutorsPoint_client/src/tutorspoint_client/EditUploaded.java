
package tutorspoint_client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import static tutorspoint_client.SubscribedMembers.s;

public class EditUploaded extends javax.swing.JFrame implements Editing{

    static Socket s;
    static String name,ip;
    DefaultTableModel model;
    DataOutputStream dout;
    DataInputStream din;
    public EditUploaded(Socket s,String name,String ip) {
        initComponents();
        this.ip = ip;
        getContentPane().setBackground(new java.awt.Color(255,255,153));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent event) {
            exitProcedure();
        }
    });
        this.s = s;
        this.name = name;
        this.setLocation(500,200);
        this.setResizable(false);
        try {
            System.out.println("Inside try");
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            System.out.println("After dout and din");
            dout.writeUTF("Uploaded");
            dout.writeUTF(name);
            model = (DefaultTableModel) table.getModel();
            String res="";
            System.out.println("After writing res");
            while(!(res = din.readUTF()).equals("exit"))
            {
                String course = din.readUTF();
                System.out.println(res+course);
                String sub = din.readUTF();
                model.addRow(new Object[]{course,sub,res});
            }
            System.out.println("Done");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Some error occured : "+e.getMessage());
        }
        
    }
    
    public void exitProcedure() {
        try{
            s.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        System.exit(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btn_rem = new javax.swing.JButton();
        btn_upload = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btn_comment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Uploaded Videos");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Delete/Add new Videos");

        btn_rem.setBackground(new java.awt.Color(255, 51, 51));
        btn_rem.setForeground(new java.awt.Color(255, 255, 255));
        btn_rem.setText("Remove");
        btn_rem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_remActionPerformed(evt);
            }
        });

        btn_upload.setBackground(new java.awt.Color(255, 51, 51));
        btn_upload.setForeground(new java.awt.Color(255, 255, 255));
        btn_upload.setText("Upload");
        btn_upload.setPreferredSize(new java.awt.Dimension(83, 23));
        btn_upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_uploadActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Profile");
        jButton1.setMaximumSize(new java.awt.Dimension(83, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(83, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(83, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course", "Sub Course", "Video"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table);

        btn_comment.setBackground(new java.awt.Color(255, 51, 51));
        btn_comment.setForeground(new java.awt.Color(255, 255, 255));
        btn_comment.setText("Comments");
        btn_comment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_commentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_comment)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_rem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_upload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_rem)
                        .addGap(42, 42, 42)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btn_upload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_comment))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_remActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_remActionPerformed
        // Remove
        removeFunc();
    }//GEN-LAST:event_btn_remActionPerformed

    private void btn_uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_uploadActionPerformed
        // Upload
        Upload u = new Upload(s,name,ip);
        u.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_uploadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Profile
        profileFunc();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_commentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_commentActionPerformed
        // Check For Comments
        try {
            int idx = table.getSelectedRow();
            if(idx>=0)
            {
                String video = (String)model.getValueAt(idx,2);
                String course = (String)model.getValueAt(idx,0);
                String sub = (String)model.getValueAt(idx, 1);
                Comments c = new Comments(s, name, video, name, course,sub,'t',ip);
                c.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Select at least one row");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }//GEN-LAST:event_btn_commentActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditUploaded.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditUploaded.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditUploaded.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditUploaded.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditUploaded(s,name,ip).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_comment;
    private javax.swing.JButton btn_rem;
    private javax.swing.JButton btn_upload;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    @Override
    public void removeFunc() {
        try {
            int idx = table.getSelectedRow();
            if(idx>=0)
            {
                String sub = (String)model.getValueAt(idx,1);
                String course = (String)model.getValueAt(idx,0);
                String video = (String)model.getValueAt(idx, 2);
                model.removeRow(idx);
                dout = new DataOutputStream(s.getOutputStream());
                dout.writeUTF("RemoveVid");
                dout.writeUTF(name);
                dout.writeUTF(course);
                dout.writeUTF(video);
                dout.writeUTF(sub);
                JOptionPane.showMessageDialog(null,"File deleted sucessfully");
            }
            else
                JOptionPane.showMessageDialog(null,"Think before clicking this button");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error : "+e.getMessage());
        }
    }

    @Override
    public void profileFunc() {
        this.setVisible(false);
        Profile p = new Profile(s,name,'t',ip);
        p.setVisible(true);
    }
}
