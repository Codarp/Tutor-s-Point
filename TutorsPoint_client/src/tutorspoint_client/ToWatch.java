/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Arpit
 */
public class ToWatch extends javax.swing.JFrame {

    static Socket s;
    static String name;
    DataOutputStream dout;
    DataInputStream din;
    DefaultTableModel model;
    static String ip;
    
    public ToWatch(Socket s,String name,String ip) {
        initComponents();
        this.s = s;
        this.name = name;
        this.ip= ip;
        getContentPane().setBackground(new java.awt.Color(255,255,153));
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });
        model = (DefaultTableModel)table.getModel();
        try{
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream((s.getInputStream()));
            
            dout.writeUTF("watchlist");
            dout.writeUTF(name);
            String author="";
            while(!(author=din.readUTF()).equals("exit"))
            {
                String course = din.readUTF();
                String sub = din.readUTF();
                String date = din.readUTF();
                String video = din.readUTF();
                model.addRow(new String[]{author,course,sub,date,video});
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btn_stream = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_rem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("To Watch");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Author", "Course", "Sub Course", "Date", "Video"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        btn_stream.setBackground(new java.awt.Color(255, 51, 51));
        btn_stream.setForeground(new java.awt.Color(255, 255, 255));
        btn_stream.setText("Stream");
        btn_stream.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_streamActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Profile");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btn_rem.setBackground(new java.awt.Color(255, 51, 51));
        btn_rem.setForeground(new java.awt.Color(255, 255, 255));
        btn_rem.setText("Remove");
        btn_rem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_remActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_rem))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_stream)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btn_rem))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_stream)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_remActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_remActionPerformed
        // Remove Button
        int x = table.getSelectedRow();
        if(x>=0)
        {
            try{
                String author=(String)model.getValueAt(x, 0);
                String course=(String)model.getValueAt(x, 1);
                String sub=(String)model.getValueAt(x, 2);
                String video=(String)model.getValueAt(x, 4);
                dout.writeUTF("RemToWatch");
                dout.writeUTF(name);
                dout.writeUTF(author);
                dout.writeUTF(course);
                dout.writeUTF(sub);
                dout.writeUTF(video);
                model.removeRow(x);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
        else
            JOptionPane.showMessageDialog(null,"Select Something");
    }//GEN-LAST:event_btn_remActionPerformed

    private void btn_streamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_streamActionPerformed
        // Stream
        int x = table.getSelectedRow();
        if(x>=0)
        {
            String author = (String)model.getValueAt(x, 0);
            String course = (String)model.getValueAt(x, 1);
            String sub = (String)model.getValueAt(x, 2);
            String date = (String)model.getValueAt(x, 3);
            String video = (String)model.getValueAt(x, 4);
            try{
                dout.writeUTF("play");
                dout.writeUTF(video);
                dout.writeUTF(author);
                dout.writeUTF(course);
                dout.writeUTF(sub);
                String streamName = din.readUTF();
                this.setVisible(false);
                Stream stream = new Stream(s,name,streamName,video,author,course,sub,date,ip);
                stream.setVisible(true);
            }
            catch(Exception e)
            {
                
            }
        }
        else
            JOptionPane.showMessageDialog(null,"Select Something");
    }//GEN-LAST:event_btn_streamActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Profile
        this.setVisible(false);
        ProfileStudent p = new ProfileStudent(s,name,'s',ip);
        p.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ToWatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ToWatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ToWatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ToWatch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ToWatch(s,name,ip).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_rem;
    private javax.swing.JButton btn_stream;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
