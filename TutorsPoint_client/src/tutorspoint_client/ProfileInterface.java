/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorspoint_client;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public interface ProfileInterface {
    
    public void setName();
    public void setMail();
    public void setProfession();
    public void editProfileFunc();
    public void deleteProfileFunc();
    public void logoutFunc();
    public ImageIcon ResizeImage(BufferedImage image);
    
}
