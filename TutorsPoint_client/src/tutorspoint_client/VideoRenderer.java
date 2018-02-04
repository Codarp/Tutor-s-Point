
package tutorspoint_client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class VideoRenderer extends JLabel implements ListCellRenderer<VideoModel>{

    public VideoRenderer() {
        setOpaque(true);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends VideoModel> list, VideoModel value, int index, boolean isSelected, boolean cellHasFocus) {
        String videoName = value.getVideoName();
        String authorID = value.getAuthorID();
        String courseName = value.getCourseName();
        String sub = value.getSub();
        
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("button.png").getImage().getScaledInstance(60, 40, Image.SCALE_SMOOTH));
        setIcon(imageIcon);
        setText("<html><font size=15 color=\'Red\'>"+videoName+"</font><br>Sub-Course: "+sub+"<br>Course: "+courseName+"<br>  Auhor: "+authorID+"</html>");
        setSize(250, 60);
        setIconTextGap(10);
        this.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                func();
            }
        });
        
        
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
    
    void func(){
        this.setBackground(Color.blue);
        this.setForeground(Color.white);
    }
}
