/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorspoint_client;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.util.*;
import javax.swing.JFrame;

public class ListVideos extends javax.swing.JFrame {

    static Socket s;
    static String name,ip;
    DefaultListModel model;
    DataInputStream din;
    DataOutputStream dout;
    
    int coun=0;
    public ListVideos(Socket s,String name,String ip) {
        initComponents();
        this.ip = ip;
        this.setResizable(false);
        this.setLocation(325,100);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent event) {
            exitProcedure();
        }
    });
        this.s = s;
        this.name = name;
        lbl_name.setText(name);
        HashSet<String> author = new HashSet<>();
        HashSet<String> hashCourse = new HashSet<>();
        HashSet<String> hashTag = new HashSet<>();
         
        try{
           
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            System.out.println("Inside constructor");
            model = new DefaultListModel();
            dout.writeUTF("VidList");
            dout.writeUTF("all");
            dout.writeUTF("all");
            dout.writeUTF("all");
            dout.writeUTF("all");
            System.out.println("no prob");
            String UID=null;
            while(!(UID=din.readUTF()).equals("exit"))
            {
                String file = din.readUTF();
                String tag = din.readUTF();
                String course = din.readUTF();
                String date = din.readUTF();
                String sub = din.readUTF();
                author.add(UID);
                hashCourse.add(course);
                hashTag.add(tag);
                VideoModel singleVideo = new VideoModel(file,UID,date,course,tag,sub);
                model.addElement(singleVideo);
            }
            list.setModel(model);
            list.setCellRenderer(new VideoRenderer());
            Iterator it = author.iterator();
            while(it.hasNext())
            {
                cmb_author.addItem((String)it.next());
            }
            it = hashCourse.iterator();
            while(it.hasNext())
            {
                cmb_course.addItem((String)it.next());
            }
            it = hashTag.iterator();
            while(it.hasNext())
            {
                cmb_tag.addItem((String)it.next());
            }
            System.out.println("Constructor created of list videos");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Exception  Occured : "+e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        txt_video = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmb_author = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmb_course = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmb_tag = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbl_name = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Video List");

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(204, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Filter By : ");

        jLabel2.setBackground(new java.awt.Color(204, 0, 0));
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Author ID : ");

        cmb_author.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "all" }));

        jLabel3.setBackground(new java.awt.Color(204, 0, 0));
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("Course Name : ");

        cmb_course.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "all" }));
        cmb_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_courseActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(204, 0, 0));
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("Tag : ");

        cmb_tag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "all" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(0, 58, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmb_tag, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_course, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_author, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_video, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_video, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_tag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        list.setBackground(new java.awt.Color(255, 234, 224));
        list.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                listMouseMoved(evt);
            }
        });
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(list);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 153));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("TUTOR'S POINT");

        lbl_name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_name.setForeground(new java.awt.Color(0, 153, 153));
        lbl_name.setText("Name");
        lbl_name.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lbl_nameMouseMoved(evt);
            }
        });
        lbl_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_nameMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_nameMouseExited(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(204, 0, 0));
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("User ID");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(lbl_name)
                .addGap(138, 138, 138)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbl_name)
                    .addComponent(jButton2)
                    .addComponent(jLabel6))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // Logout
            s.close();
        } 
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Socket closed");
        }
        this.setVisible(false);
        Login l = new Login();
        l.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmb_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_courseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_courseActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Search
        try{
           
            model = new DefaultListModel();
            dout.writeUTF("VidList");
            String video = txt_video.getText();
            if(video.equals(""))
                dout.writeUTF("all");
            else 
                dout.writeUTF(video);
            dout.writeUTF((String)cmb_author.getSelectedItem());
            dout.writeUTF((String)cmb_course.getSelectedItem());
            dout.writeUTF((String)cmb_tag.getSelectedItem());
            String UID=null;
            while(!(UID=din.readUTF()).equals("exit"))
            {
                String file = din.readUTF();
                String tag = din.readUTF();
                String course = din.readUTF();
                String date = din.readUTF();
                String sub = din.readUTF();
                VideoModel singleVideo = new VideoModel(file,UID,date,course,tag,sub);
                model.addElement(singleVideo);
            }
            list.setModel(model);
            list.setCellRenderer(new VideoRenderer());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Exception occured : "+e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listValueChanged
        // OnClick on a particular item
        coun++;
        if(coun<=1)
        {
            list.setSelectionBackground(Color.PINK);
            int choice = list.getSelectedIndex();
            VideoModel videoModel = (VideoModel) model.getElementAt(choice);
            try{
                
                dout.writeUTF("play");
                dout.writeUTF(videoModel.getVideoName());
                dout.writeUTF(videoModel.getAuthorID());
                dout.writeUTF(videoModel.getCourseName());
                dout.writeUTF(videoModel.getSub());
                String streamName = din.readUTF();
                System.out.println("Yessssss");
                this.setVisible(false);
                Stream stream = new Stream(s,name,streamName,videoModel.getVideoName(),videoModel.getAuthorID(),videoModel.getCourseName(),videoModel.getSub(),videoModel.getDate(),ip);
                stream.setVisible(true);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,"Some exceiton occured : "+e.getMessage());
            }
        }
        
    }//GEN-LAST:event_listValueChanged

    private void lbl_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_nameMouseClicked
        // Profile
        this.setVisible(false);
        ProfileStudent p = new ProfileStudent(s,name,'s',ip);
        p.setVisible(true);
    }//GEN-LAST:event_lbl_nameMouseClicked

    private void lbl_nameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_nameMouseMoved
        // Mouse moved
        lbl_name.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl_name.setForeground(Color.red);
    }//GEN-LAST:event_lbl_nameMouseMoved

    private void listMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseMoved
        // Inside List Mouse moved
        list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_listMouseMoved

    private void lbl_nameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_nameMouseExited
        lbl_name.setForeground(new Color(0,153,153));
    }//GEN-LAST:event_lbl_nameMouseExited

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
            System.out.println("Yeh kya hai");
            java.util.logging.Logger.getLogger(ListVideos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            System.out.println("Yeh kya hai");
            java.util.logging.Logger.getLogger(ListVideos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            System.out.println("Yeh kya hai");
            java.util.logging.Logger.getLogger(ListVideos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println("Yeh kya hai");
            java.util.logging.Logger.getLogger(ListVideos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListVideos(s,name,ip).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_author;
    private javax.swing.JComboBox<String> cmb_course;
    private javax.swing.JComboBox<String> cmb_tag;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_name;
    private javax.swing.JList<VideoModel> list;
    private javax.swing.JTextField txt_video;
    // End of variables declaration//GEN-END:variables
}
