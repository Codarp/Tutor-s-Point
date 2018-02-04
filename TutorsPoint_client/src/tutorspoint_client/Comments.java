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

public class Comments extends javax.swing.JFrame {

    static Socket s;
    static String name;
    static String video;
    static String author;
    static String course;
    static String sub,ip;
    static char n;
    DataOutputStream dout;
    DataInputStream din;
    DefaultTableModel model;

    public Comments(Socket s,String name,String video,String author,String course,String sub,char n,String ip) {
        initComponents();
        this.ip = ip;
        setTitle(video);
        getContentPane().setBackground(new java.awt.Color(255,255,153));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent event) {
            exitProcedure();
        }
    });
        this.s = s;
        this.name = name;
        this.video = video;
        this.author = author;
        this.course = course;
        this.sub = sub;
        this.n = n;
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if(n=='s')
            btn_remove.setVisible(false);
        try{
            model = (DefaultTableModel)table.getModel();
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            lbl_name.setText(name);
            lbl_video.setText(video);
            dout.writeUTF("ShowComment");
            dout.writeUTF(author);
            dout.writeUTF(course);
            dout.writeUTF(video);
            dout.writeUTF(sub);
            String UID = "",text="";
            while(!(UID = din.readUTF()).equals("exit"))
            {
                text = din.readUTF();
                model.addRow(new String[]{ UID,text });
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Some exception occured : "+e.getMessage());
        }
        
    }

    public void exitProcedure() {
        this.dispose();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_video = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        lbl_name = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_comment = new javax.swing.JTextArea();
        btn_post = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btn_remove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Comment");

        lbl_video.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_video.setForeground(new java.awt.Color(204, 0, 0));
        lbl_video.setText("Video Name");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(150);
            table.getColumnModel().getColumn(0).setPreferredWidth(150);
            table.getColumnModel().getColumn(0).setMaxWidth(500);
        }

        lbl_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name.setForeground(new java.awt.Color(204, 0, 0));
        lbl_name.setText("Name");

        txt_comment.setColumns(20);
        txt_comment.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_comment.setRows(2);
        txt_comment.setTabSize(4);
        jScrollPane2.setViewportView(txt_comment);

        btn_post.setBackground(new java.awt.Color(255, 51, 51));
        btn_post.setForeground(new java.awt.Color(255, 255, 255));
        btn_post.setText("Post");
        btn_post.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_postActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Comment Limit is 100 words");

        btn_remove.setBackground(new java.awt.Color(255, 51, 51));
        btn_remove.setForeground(new java.awt.Color(255, 255, 255));
        btn_remove.setText("Remove Comment");
        btn_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbl_name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_video)
                        .addGap(272, 272, 272))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                        .addComponent(btn_post)
                        .addGap(232, 232, 232)
                        .addComponent(btn_remove)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_video)
                    .addComponent(lbl_name))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_post)
                    .addComponent(jLabel2)
                    .addComponent(btn_remove))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_postActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_postActionPerformed
        // Post Comment
        String comment = txt_comment.getText();
        txt_comment.setText("");
        try{
            dout.writeUTF("AddComment");
            dout.writeUTF(author);
            dout.writeUTF(course);
            dout.writeUTF(video);
            dout.writeUTF(name);
            dout.writeUTF(comment);
            dout.writeUTF(sub);
            model.addRow(new String[] {name,comment});
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
    }//GEN-LAST:event_btn_postActionPerformed

    private void btn_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeActionPerformed
        // Button Remove Comment
        try{
            int x = table.getSelectedRow();
            if(x>=0)
            {
                String UID = (String)model.getValueAt(x,0);
                String comment = (String)model.getValueAt(x,1);
                model.removeRow(x);
                dout.writeUTF("RemComment");
                dout.writeUTF(author);
                dout.writeUTF(course);
                dout.writeUTF(video);
                dout.writeUTF(UID);
                dout.writeUTF(comment);
                dout.writeUTF(sub);
            }
            else
                JOptionPane.showMessageDialog(null,"Atleast select any one item");
        }
        catch(Exception  e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }//GEN-LAST:event_btn_removeActionPerformed

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
            java.util.logging.Logger.getLogger(Comments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Comments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Comments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Comments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Comments(s,name,video,author,course,sub,n,ip).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_post;
    private javax.swing.JButton btn_remove;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_name;
    private javax.swing.JLabel lbl_video;
    private javax.swing.JTable table;
    private javax.swing.JTextArea txt_comment;
    // End of variables declaration//GEN-END:variables
}