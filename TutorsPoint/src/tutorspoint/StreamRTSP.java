/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorspoint;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class StreamRTSP {
    
    HeadlessMediaPlayer mediaPlayer;
    ServerSocket ss;
    Socket s;
    DataInputStream dis;
    int port;
    String name;
    String video;
    String course;
    String stream;
    String sub;
    
    StreamRTSP(int port,String name,String video,String course,String sub,String stream,ServerSocket ss){
        this.port = port;
        this.name = name;
        this.video = video;
        this.course = course;
        this.sub = sub;
        this.stream = stream;
        this.ss=ss;
    }
    
    public void main() throws Exception {
    
    NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "VLC");
    Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        
    String media = "e:\\Data\\"+name+"\\"+course+"\\"+sub+"\\"+video;
    String options = formatRtspStream("127.0.0.1", port, stream);

    System.out.println("Streaming '" + media + "' to '" + options + "'");

    System.out.println("Media is going to be played");
    MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
    mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
    mediaPlayer.playMedia(media,
                    options,
                    ":no-sout-rtp-sap", 
                    ":no-sout-standard-sap", 
                    ":sout-all", 
                    ":sout-keep"
                );
    System.out.println("Media has now been played");

        try{
            s = ss.accept();
            System.out.println("HASH TAG Cient connected");
            dis = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            Thread t = new Thread(new Runnable(){
                public void run(){
                while(true)
                {
                    try{
                        String str = dis.readUTF();
                        System.out.println(str);
                        if(str.equals("change"))
                        {
                            float f = Float.parseFloat(dis.readUTF());
                            mediaPlayer.setPosition(f);
                            System.out.println("OK");
                        }
                        else if(str.equals("exit"))
                        {
                            break;
                        }
                        else if(str.equals("position"))
                        {
                            System.out.println(stream);
                            dout.writeUTF(""+mediaPlayer.getPosition());
                        }
                    }   
                    catch (Exception e) {
                        break;
                    }
                }
                try{
                    s.close();
                }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }});
            t.start();
            t.join();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            
        }
        
    // Don't exit
    //Thread.currentThread().join();
  }
    private static String formatRtspStream(String serverAddress, int serverPort, String id) {
    StringBuilder sb = new StringBuilder(60);
    sb.append(":sout=#transcode{vcodec=h264,acodec=mp3}:rtp{mux=ts,sdp=rtsp://@");
    sb.append(serverAddress);
    sb.append(':');
    sb.append(serverPort);
    sb.append('/');
    sb.append(id+"}");
    return sb.toString();
  }
}
