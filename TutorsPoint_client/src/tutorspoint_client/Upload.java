/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorspoint_client;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import static tutorspoint_client.ProfileStudent.s;

/**
 *
 * @author Arpit
 */

public class Upload extends javax.swing.JFrame {

    String path="";
    static Socket s;
    static String name,ip;
    DataOutputStream dout;
    DataInputStream din;
    
    public Upload(Socket s,String name,String ip) {
        initComponents();
        this.ip = ip;
        getContentPane().setBackground(new java.awt.Color(204,250,250));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent event) {
            exitProcedure();
        }
    });
        this.setLocation(500,200);
        this.setResizable(false);
        this.s = s;
        this.name = name;
        try {
            
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            dout.writeUTF("Course");
            dout.writeUTF(name);
            String course=null;
            while(!(course=din.readUTF()).equals("exit"))
            {
                cmb_course.addItem(course);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Excetion occured : "+e.getMessage());
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
        jLabel2 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_ext = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_tag = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lbl_decide = new javax.swing.JLabel();
        lbl_percent = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cmb_course = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cmb_sub = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Upload");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Upload Videos");

        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Name of file : ");

        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Type in the extension : ");

        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Tag (Optional) : ");

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Chose File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Upload");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbl_decide.setForeground(new java.awt.Color(255, 102, 102));
        lbl_decide.setText("File not selected");

        lbl_percent.setForeground(new java.awt.Color(0, 102, 51));
        lbl_percent.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbl_percent.setText("Status of the file uploaded...");

        jButton3.setBackground(new java.awt.Color(0, 153, 153));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Profile");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Course : ");

        cmb_course.setForeground(new java.awt.Color(0, 102, 102));
        cmb_course.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--select--" }));
        cmb_course.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_courseItemStateChanged(evt);
            }
        });
        cmb_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_courseActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Sub Course");

        cmb_sub.setForeground(new java.awt.Color(0, 102, 102));
        cmb_sub.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--select--" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_name))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_ext))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_tag)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(70, 70, 70))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_decide)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_percent, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmb_sub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_ext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_tag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmb_course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cmb_sub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_decide)
                    .addComponent(lbl_percent))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Choose
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Videos","mkv","mp4","avi");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result==JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = file.getSelectedFile();
            path = selectedFile.getAbsolutePath();
            lbl_decide.setForeground(Color.blue);
            lbl_decide.setText("File successfully selected :)");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(path.equals("")||txt_name.getText().equals("")||txt_ext.getText().equals("")||cmb_course.getSelectedIndex()==0)
            JOptionPane.showMessageDialog(null,"Something is not defined!!!");
        else{
            try {
                //Upload
                String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
                dout.writeUTF("Upload");
                dout.writeUTF(name);
                dout.writeUTF(txt_name.getText());
                dout.writeUTF("."+txt_ext.getText());
                if(txt_tag.getText().equals(""))
                        dout.writeUTF("random");
                    else
                        dout.writeUTF(txt_tag.getText());
                dout.writeUTF(date);
                dout.writeUTF(cmb_course.getSelectedItem().toString());
                dout.writeUTF(cmb_sub.getSelectedItem().toString());
                String res = din.readUTF();
                if(res.equals("Success"))
                {
                    
                    //Specify the file
                    lbl_percent.setText("Waiting for server");
                    Thread t = new Thread(new Runnable(){
                        @Override
                        public void run(){
                            try{
                                File file = new File(path);
                                FileInputStream fis = new FileInputStream(file);
                                BufferedInputStream bis = new BufferedInputStream(fis);
                                //Get socket's output stream
                                Socket socket = new Socket(ip,9999);
                                OutputStream os = socket.getOutputStream();

                                //Read File Contents into contents array
                                byte[] contents;
                                long fileLength = file.length();
                                long current = 0;

                                long start = System.nanoTime();
                                while(current!=fileLength){
                                    int size = 10000;
                                    if(fileLength - current >= size)
                                        current += size;
                                    else{
                                        size = (int)(fileLength - current);
                                        current = fileLength;
                                    }
                                    contents = new byte[size];
                                    bis.read(contents, 0, size);
                                    os.write(contents);
                                    lbl_percent.setText("Uploading file ... "+(current*100)/fileLength+"% complete!");
                                }
                                os.flush();
                                socket.close();
                                System.out.println("File sent succesfully!");
                                txt_name.setText("");
                                txt_ext.setText("");
                                txt_tag.setText("");
                                path="";
                                lbl_decide.setText("File not selected");
                                lbl_percent.setText("Status of the file uploaded...");
                                
                           }
                           catch(Exception e){
                               JOptionPane.showMessageDialog(null,"Excetion occured : "+e.getMessage());
                           }
                       } 
                    });
                    t.start();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"One name is already present. Type in another name.");
                }
                //File transfer done. Close the socket connection!
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Excetion occured : "+ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Profile
        Profile p = new Profile(s,name,'t',ip);
        p.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cmb_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_courseActionPerformed
        // cmb_course
        int choice = cmb_course.getSelectedIndex();
        if(choice==0)
        {
            cmb_sub.removeAllItems();
            cmb_sub.addItem("--select--");
        }
        else
        {
            String course = (String)cmb_course.getSelectedItem();
            cmb_sub.removeAllItems();
            try{
                dout.writeUTF("SubCourse");
                dout.writeUTF(name);
                dout.writeUTF(course);
                String str="";
                while(!(str=din.readUTF()).equals("exit"))
                {
                    cmb_sub.addItem(str);
                }
            }
            catch(Exception e)
            {
                
            }
        }
    }//GEN-LAST:event_cmb_courseActionPerformed

    private void cmb_courseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_courseItemStateChanged
        // Item State changed
        
    }//GEN-LAST:event_cmb_courseItemStateChanged

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
            java.util.logging.Logger.getLogger(Upload.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Upload.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Upload.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Upload.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Upload(s,name,ip).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_course;
    private javax.swing.JComboBox<String> cmb_sub;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lbl_decide;
    private javax.swing.JLabel lbl_percent;
    private javax.swing.JTextField txt_ext;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_tag;
    // End of variables declaration//GEN-END:variables
}
