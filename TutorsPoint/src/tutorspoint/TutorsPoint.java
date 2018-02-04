
package tutorspoint;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

class Server extends Thread{
    
    Socket s;
    Connection con;
    DataOutputStream dout;
    DataInputStream dis;
    final Object obj;
    ServerSocket ss1;
    int j;
    int i;
    
    Server(Socket soc,Connection c,Object obj,int i,ServerSocket ss1){
        s = soc;
        con = c;
        this.obj = obj;
        this.i = i;
        this.ss1 = ss1;
        j=0;
    }
    
    synchronized public void run(){
        try{
            dis=new DataInputStream(s.getInputStream());
            dout=new DataOutputStream(s.getOutputStream());
            System.out.println("Streams created");
            while(true)
            {
                try{
                    String name=dis.readUTF();
                    PreparedStatement stmt=null;
                    if(name.equals("Login Student")){
                        stmt = con.prepareStatement("select * from tutor.login_s where UID=? and Password=?");
                        String user = dis.readUTF();
                        String pass = dis.readUTF();
                        stmt.setString(1,user);
                        stmt.setString(2,pass);
                        ResultSet res;
                        synchronized(obj){
                            res = stmt.executeQuery();
                        }
                        if(res.next()){
                            dout.writeUTF("Success");
                            dout.flush();
                        }
                        else{
                            dout.writeUTF("Unsuccess");
                            dout.flush();
                        }
                    }
                    else if(name.equals("Login Teacher")){
                        stmt = con.prepareStatement("select * from tutor.login_t where UID=? and Password=?");
                        String user = dis.readUTF();
                        String pass = dis.readUTF();
                        stmt.setString(1,user);
                        stmt.setString(2,pass);
                        ResultSet res;
                        synchronized(obj){
                            res = stmt.executeQuery();
                        }
                        if(res.next()){
                            dout.writeUTF("Success");
                            dout.flush();
                        }
                        else{
                            dout.writeUTF("Unsuccess");
                            dout.flush();
                        }
                    }
                    else if(name.equals("SignUp_s")){
                        stmt = con.prepareStatement("select * from tutor.login_s where UID=?");
                        String user = dis.readUTF();
                        stmt.setString(1, user);
                        ResultSet res;
                        synchronized(obj){
                            res = stmt.executeQuery();
                        }
                        if(res.next()){
                            dout.writeUTF("Unsuccess");
                        }
                        else{
                            dout.writeUTF("Success");
                            String mail = dis.readUTF();
                            String pass = dis.readUTF();
                            ServerSocket ss = new ServerSocket(9999);
                            Socket socket = ss.accept();
                            BufferedImage image = ImageIO.read(socket.getInputStream());
                            socket.close();
                            ss.close();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ImageIO.write(image, "jpg", baos );
                            byte[] imageInByte = baos.toByteArray();
                            Blob blob = con.createBlob();
                            blob.setBytes(1, imageInByte);
                            stmt = con.prepareStatement("insert into tutor.login_s values(?,?,?,?);");
                            stmt.setString(1,user);
                            stmt.setString(2,mail);
                            stmt.setString(3,pass);
                            stmt.setBlob(4,blob);
                            synchronized(obj){
                                stmt.execute();
                            }
                            stmt = con.prepareStatement("create table "+user+"_subscriber_student ( Name varchar(30) PRIMARY KEY );");
                            stmt.execute();
                        }
                    }
                    else if(name.equals("SignUp_t")){
                        stmt = con.prepareStatement("select * from tutor.login_t where UID=?");
                        String user = dis.readUTF();
                        stmt.setString(1, user);
                        ResultSet res;
                        synchronized(obj){
                            res = stmt.executeQuery();
                        }
                        if(res.next()){
                            dout.writeUTF("Unsuccess");
                        }
                        else{
                            dout.writeUTF("Success");
                            String mail = dis.readUTF();
                            String pass = dis.readUTF();
                            ServerSocket ss = new ServerSocket(9999);
                            Socket socket = ss.accept();
                            BufferedImage image = ImageIO.read(socket.getInputStream());
                            socket.close();
                            ss.close();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ImageIO.write(image, "jpg", baos );
                            byte[] imageInByte = baos.toByteArray();
                            Blob blob = con.createBlob();
                            blob.setBytes(1, imageInByte);
                            stmt = con.prepareStatement("insert into tutor.login_t values(?,?,?,?);");
                            stmt.setString(1,user);
                            stmt.setString(2,mail);
                            stmt.setString(3,pass);
                            stmt.setBlob(4,blob);
                            synchronized(obj){
                                stmt.execute();
                            }
                            new File("e:\\Data\\"+user).mkdir();
                            stmt  = con.prepareStatement("create table "+user+"_course ( Name varchar(30) PRIMARY KEY );");
                            synchronized(obj){
                                stmt.execute();
                            }
                            stmt = con.prepareStatement("create table "+user+"_subscriber ( Name varchar(30) PRIMARY KEY );");
                            synchronized(obj){
                                stmt.execute();
                            }
                        }
                    }
                    else if(name.equals("Profile_s")){
                        String user = dis.readUTF();
                        stmt = con.prepareStatement("select * from tutor.login_s where UID=?");
                        stmt.setString(1,user);
                        ResultSet res;
                        synchronized(obj){
                            res = stmt.executeQuery();
                        }
                        if(res.next()){
                            String email = res.getString(2);
                            dout.writeUTF(email);
                            String pass = res.getString(3);
                            dout.writeUTF(pass);
                            Blob blob = res.getBlob("Image");
                            InputStream in = blob.getBinaryStream(1, blob.length());
                            BufferedImage image = ImageIO.read(in);
                            ServerSocket ss = new ServerSocket(9999);
                            Socket socket = ss.accept();
                            ImageIO.write(image, "jpg", socket.getOutputStream());
                            socket.close();
                            ss.close();
                            System.out.println("Image is sent !!!");
                        }
                    }
                    else if(name.equals("Profile_t")){
                        String user = dis.readUTF();
                        stmt = con.prepareStatement("select * from tutor.login_t where UID=?");
                        stmt.setString(1,user);
                        ResultSet res;
                        synchronized(obj){
                            res = stmt.executeQuery();   
                        }
                        if(res.next()){
                            String email = res.getString(2);
                            dout.writeUTF(email);
                            String pass = res.getString(3);
                            dout.writeUTF(pass);
                            Blob blob = res.getBlob("Image");
                            InputStream in = blob.getBinaryStream(1, blob.length());  
                            BufferedImage image = ImageIO.read(in);
                            ServerSocket ss = new ServerSocket(9999);
                            Socket socket = ss.accept();
                            ImageIO.write(image, "jpg", socket.getOutputStream());
                            socket.close();
                            ss.close();
                            System.out.println("Image is sent !!!");
                        }
                    }
                    else if(name.equals("Delete_s"))
                    {
                        String user = dis.readUTF();
                        stmt = con.prepareStatement("delete from tutor.login_s where UID=?");
                        stmt.setString(1,user);
                        synchronized(obj){
                            stmt.execute();
                        }
                        stmt = con.prepareStatement("drop table "+user+"_subscriber_student;");
                        stmt.execute();
                    }
                    else if(name.equals("Delete_t"))
                    {
                        String user = dis.readUTF();
                        stmt = con.prepareStatement("delete from tutor.login_t where UID=?");
                        stmt.setString(1,user);
                        synchronized(obj){
                            stmt.execute();
                        }
                        new File("e:\\Data\\"+user).delete();
                        stmt = con.prepareStatement("drop table "+user+"_subscriber;");
                        synchronized(obj){
                            stmt.execute();
                        }
                        stmt = con.prepareStatement("drop table "+user+"_course;");
                        synchronized(obj){
                            stmt.execute();
                        }
                    }
                    else if(name.equals("Edit_s"))
                    {
                        String user = dis.readUTF();
                        String mail = dis.readUTF();
                        String pass = dis.readUTF();
                        String decide = dis.readUTF();
                        if(decide.equals("n"))
                        {
                            ServerSocket ss = new ServerSocket(9999);
                            Socket socket = ss.accept();
                            BufferedImage image = ImageIO.read(socket.getInputStream());
                            socket.close();
                            ss.close();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ImageIO.write(image, "jpg", baos );
                            byte[] imageInByte = baos.toByteArray();
                            Blob blob = con.createBlob();
                            blob.setBytes(1, imageInByte);
                            System.out.println("Blob Converted successfully");
                            stmt = con.prepareStatement("update tutor.login_s set mail=?, Password=?, Image=? where UID=?;");
                            stmt.setString(1,mail);
                            stmt.setString(2,pass);
                            stmt.setBlob(3,blob);
                            stmt.setString(4,user);
                        }
                        else if(decide.equals("y"))
                        {
                            stmt = con.prepareStatement("update tutor.login_s set mail=?, Password=? where UID=?;");
                            stmt.setString(1,mail);
                            stmt.setString(2,pass);
                            stmt.setString(3,user);
                        }
                        try{
                            System.out.println("executing statements...");
                            synchronized(obj){
                                stmt.execute();
                            }
                            dout.writeUTF("Success");
                            dout.flush();
                            System.out.println("Successfully executed...");
                        } catch(Exception e){
                            dout.writeUTF("Unsuccess");
                        }
                    }
                    else if(name.equals("Edit_t"))
                    {
                        String user = dis.readUTF();
                        String mail = dis.readUTF();
                        String pass = dis.readUTF();
                        String decide = dis.readUTF();
                        if(decide.equals("n"))
                        {
                            ServerSocket ss = new ServerSocket(9999);
                            Socket socket = ss.accept();
                            BufferedImage image = ImageIO.read(socket.getInputStream());
                            socket.close();
                            ss.close();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ImageIO.write(image, "jpg", baos );
                            byte[] imageInByte = baos.toByteArray();
                            Blob blob = con.createBlob();
                            blob.setBytes(1, imageInByte);

                            stmt = con.prepareStatement("update tutor.login_t set mail=?, Password=?, Image=? where UID=?;");
                            stmt.setString(1,mail);
                            stmt.setString(2,pass);
                            stmt.setBlob(3,blob);
                            stmt.setString(4,user);
                        }
                        else if(decide.equals("y"))
                        {
                            stmt = con.prepareStatement("update tutor.login_t set mail=?, Password=? where UID=?;");
                            stmt.setString(1,mail);
                            stmt.setString(2,pass);
                            stmt.setString(3,user);
                        }
                        try{

                            synchronized(obj){
                                stmt.execute();
                            }
                            dout.writeUTF("Success");
                            dout.flush();
                        } catch(Exception e){
                            dout.writeUTF("Unsuccess");
                        }
                    }
                    else if(name.equals("Upload"))
                    {
                        String user = dis.readUTF();
                        String filename = dis.readUTF();
                        String ext = dis.readUTF();
                        String tag = dis.readUTF();
                        String date = dis.readUTF();
                        String course = dis.readUTF();
                        String sub = dis.readUTF();
                        stmt = con.prepareStatement("select * from tutor.videos where Filename=? and UID=? and Course=? and sub=?;");
                        stmt.setString(1,filename);
                        stmt.setString(2, user);
                        stmt.setString(3,course);
                        stmt.setString(4,sub);
                        ResultSet res;
                        synchronized(obj){    
                            res = stmt.executeQuery();
                        }
                        if(res.next())
                        {
                            dout.writeUTF("Unsuccess");
                        }
                        else
                        {
                            dout.writeUTF("Success");
                            byte[] contents = new byte[100000];

                            //Initialize the FileOutputStream to the output file's full path.
                            FileOutputStream fos = new FileOutputStream("e:\\Data\\"+user+"\\"+course+"\\"+sub+"\\"+filename+ext);
                            BufferedOutputStream bos = new BufferedOutputStream(fos);
                            synchronized(obj){
                                ServerSocket ss = new ServerSocket(9999);
                                Socket socket = ss.accept();
                                InputStream is = socket.getInputStream();
                                //No of bytes read in one read() call
                                int bytesRead = 0;

                                while((bytesRead=is.read(contents))!=-1)
                                    bos.write(contents, 0, bytesRead);

                                bos.flush();
                                socket.close();
                                ss.close();
                            }
                            System.out.println("File saved successfully!");
                            stmt = con.prepareStatement("insert into tutor.videos values(?,?,?,?,?,?);");
                            stmt.setString(1,user);
                            stmt.setString(2,filename+ext);
                            stmt.setString(3,tag);
                            stmt.setString(4,course);
                            stmt.setString(5,date);
                            stmt.setString(6,sub);
                            synchronized(obj){
                                stmt.execute();
                            }
                            System.out.println("create table "+user+"_"+course+"_"+sub+"_"+filename+"_like ( UID varchar(30) PRIMARY KEY );");
                            stmt = con.prepareStatement("create table "+user+"_"+course+"_"+sub+"_"+filename+"_like ( UID varchar(30) PRIMARY KEY );");
                            synchronized(obj){
                                stmt.execute();
                            }
                            System.out.println("here2");
                            stmt = con.prepareStatement("create table "+user+"_"+course+"_"+sub+"_"+filename+"_comment ( UID varchar(30) , "
                                    + "Comment varchar(100) );");
                            synchronized(obj){
                                stmt.execute();
                            }
                            stmt = con.prepareStatement("create table "+user+"_"+course+"_"+sub+"_"+filename+"_rate ( UID varchar(30) PRIMARY KEY, "
                                    + "Rate SMALLINT DEFAULT 5 );");
                            synchronized(obj){
                                stmt.execute();
                            }
                            stmt = con.prepareStatement("select * from "+user+"_subscriber;");
                            res = stmt.executeQuery();
                            while(res.next())
                            {
                                stmt = con.prepareStatement("insert into notification values (?,?,?,?,?,?);");
                                stmt.setString(1,user);
                                stmt.setString(2,res.getString(1));
                                stmt.setString(3,course);
                                stmt.setString(4,sub);
                                stmt.setString(5,date);
                                stmt.setString(6,filename+ext);
                                stmt.execute();
                            }
                        }
                    }
                    else if(name.equals("Uploaded"))
                    {
                        String user = dis.readUTF();
                        stmt = con.prepareStatement("select Filename, Course, sub from tutor.videos where UID=?;");
                        stmt.setString(1,user);
                        ResultSet res;
                        System.out.println("Server Tutor : After ResultSet");
                        synchronized(obj){
                            res = stmt.executeQuery();
                        }
                        System.out.println("Server Tutor : After Executing Query");
                        while(res.next())
                        {
                            dout.writeUTF(res.getString(1));
                            System.out.println(res.getString(1));
                            dout.writeUTF(res.getString(2));
                            dout.writeUTF(res.getString(3));
                        }
                        System.out.println("Server Tutor : Writing exit");
                        dout.writeUTF("exit");
                        System.out.println("Server Tutor : Exit Written");
                        dout.flush();
                    }
                    else if(name.equals("RemoveVid"))
                    {
                        String user = dis.readUTF();
                        String course = dis.readUTF();
                        String video = dis.readUTF();
                        String sub = dis.readUTF();
                        File f = new File("e:\\Data\\"+user+"\\"+course+"\\"+sub+"\\"+video);
                        System.gc();
                        f.delete();
                        stmt = con.prepareStatement("delete from tutor.videos where Filename=? and UID=? and Course=? and sub=?");
                        stmt.setString(1,video);
                        stmt.setString(2,user);
                        stmt.setString(3,course);
                        stmt.setString(4,sub);
                        synchronized(obj){
                            stmt.execute();
                        }
                        String table_name = user+"_"+course+"_"+sub+"_"+video;
                        int n = table_name.indexOf('.');
                        stmt = con.prepareStatement("drop table "+table_name.substring(0, n)+"_like;");
                        synchronized(obj){
                            stmt.execute();
                        }
                        stmt = con.prepareStatement("drop table "+table_name.substring(0, n)+"_rate;");
                        synchronized(obj){
                            stmt.execute();
                        }
                        stmt = con.prepareStatement("drop table "+table_name.substring(0, n)+"_comment;");
                        synchronized(obj){
                            stmt.execute();
                        }
                    }
                    else if(name.equals("Course"))
                    {
                        String user = dis.readUTF();
                        stmt = con.prepareStatement("select * from "+user+"_course;");
                        ResultSet res;
                        synchronized(obj){
                            res = stmt.executeQuery();
                        }
                        while(res.next())
                        {
                            dout.writeUTF(res.getString(1));
                        }
                        dout.writeUTF("exit");
                    }
                    else if(name.equals("CourseAdd"))
                    {
                        String user = dis.readUTF();
                        String course = dis.readUTF();
                        stmt = con.prepareStatement("insert into "+user+"_course values(?);");
                        stmt.setString(1,course);
                        synchronized(obj){
                            stmt.execute();
                        }
                        new File("e:\\Data\\"+user+"\\"+course).mkdir();
                        stmt = con.prepareStatement("create table "+user+"_"+course+"_subcourse ( Name varchar(30) PRIMARY KEY )");
                        stmt.execute();
                    }
                    else if(name.equals("CourseDel"))
                    {
                        String user = dis.readUTF();
                        String course = dis.readUTF();
                        stmt = con.prepareStatement("select * from "+user+"_"+course+"_subcourse;");
                        ResultSet res;
                        synchronized(obj) {
                            res = stmt.executeQuery();
                        }
                        if(res.next())
                            dout.writeUTF("Unsuccess");
                        else
                        {
                            dout.writeUTF("Success");
                            stmt = con.prepareStatement("delete from "+user+"_course where Name=?;");
                            stmt.setString(1,course);
                            synchronized(obj){
                                stmt.execute();
                            }
                            stmt = con.prepareStatement("drop table "+user+"_"+course+"_subcourse;");
                            stmt.execute();
                            new File("e:\\Data\\"+user+"\\"+course).delete();
                        }
                    }
                    else if(name.equals("AddSubCourse"))
                    {
                        String user = dis.readUTF();
                        String course = dis.readUTF();
                        String sub = dis.readUTF();
                        stmt = con.prepareStatement("select * from "+user+"_"+course+"_subcourse where Name=?;");
                        stmt.setString(1,sub);
                        ResultSet res = stmt.executeQuery();
                        if(res.next())
                        {
                            dout.writeUTF("Unsuccess");
                        }
                        else
                        {
                            dout.writeUTF("Success");
                            stmt = con.prepareStatement("insert into "+user+"_"+course+"_subcourse values(?);");
                            stmt.setString(1,sub);
                            stmt.execute();
                        }
                        new File("e:\\Data\\"+user+"\\"+course+"\\"+sub).mkdir();
                    }
                    else if(name.equals("DelSubCourse"))
                    {
                        String user = dis.readUTF();
                        String course = dis.readUTF();
                        String sub = dis.readUTF();
                        stmt = con.prepareStatement("select * from tutor.videos where UID=? and Course=? and sub=?");
                        stmt.setString(1,user);
                        stmt.setString(2,course);
                        stmt.setString(3, sub);
                        ResultSet res = stmt.executeQuery();
                        if(res.next())
                        {
                            dout.writeUTF("Unsuccess");
                        }
                        else
                        {
                            dout.writeUTF("Success");
                            stmt = con.prepareStatement("delete from "+user+"_"+course+"_subcourse where Name=?;");
                            stmt.setString(1, sub);
                            stmt.execute();
                            new File("e:\\Data\\"+user+"\\"+course+"\\"+sub).delete();
                        }
                    }
                    else if(name.equals("SubCourse"))
                    {
                        String user = dis.readUTF();
                        String course = dis.readUTF();
                        stmt = con.prepareStatement("select * from "+user+"_"+course+"_subcourse;");
                        ResultSet res = stmt.executeQuery();
                        while(res.next())
                        {
                            dout.writeUTF(res.getString(1));
                        }
                        dout.writeUTF("exit");
                    }
                    else if(name.equals("VidList"))
                    {
                        String file = dis.readUTF();
                        String UID = dis.readUTF();
                        String course = dis.readUTF();
                        String tag = dis.readUTF();
                        String query = "select * from videos";
                        if(!(file.equals("all")&&UID.equals("all")&&course.equals("all")&&tag.equals("all")))
                        {
                            query = query + " where";
                        }
                        int flag=0;
                        if(!file.equals("all"))
                        {
                            query = query + " Filename Like \'"+file+"%\'";
                            flag=1;
                        }
                        if(!UID.equals("all"))
                        {
                            if(flag==0)
                                query = query + " UID=\'"+UID+"\'";
                            else
                                query = query + " and UID=\'"+UID+"\'";
                            flag=1;
                        }
                        if(!course.equals("all"))
                        {
                            if(flag==0)
                                query = query + " Course=\'"+course+"\'";
                            else
                                query = query + " and Course=\'"+course+"\'";
                            flag=1;
                        }
                        if(!tag.equals("all"))
                        {
                            if(flag==0)
                                query = query + " Tag=\'"+tag+"\'";
                            else
                                query = query + " and Tag=\'"+tag+"\'";
                            flag=1;
                        }
                        query = query+";";
                        System.out.println(query);
                        stmt = con.prepareStatement(query);
                        ResultSet res = stmt.executeQuery();
                        while(res.next())
                        {
                            dout.writeUTF(res.getString(1));
                            dout.writeUTF(res.getString(2));
                            dout.writeUTF(res.getString(3));
                            dout.writeUTF(res.getString(4));
                            dout.writeUTF(res.getString(5));
                            dout.writeUTF(res.getString(6));
                        }
                        dout.writeUTF("exit");
                    }
                    else if(name.equals("play"))
                    {
                        String video = dis.readUTF();
                        String author = dis.readUTF();
                        String course = dis.readUTF();
                        String sub = dis.readUTF();
                        StreamRTSP stream = new StreamRTSP(5555,author,video,course,sub,(("stream"+i).toString()+j),ss1);
                        Thread t1 = new Thread(new Runnable(){
                            @Override
                            public void run(){
                                try {
                                    stream.main();
                                } 
                                catch (Exception ex) {
                                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        t1.start();
                        Thread.sleep(1000);
                        dout.writeUTF(("stream"+i).toString()+j);
                        j++;
                    }
                    else if(name.equals("Like"))
                    {
                        System.out.println("Inside Like");
                        String user = dis.readUTF();
                        String video = dis.readUTF();
                        String author = dis.readUTF();
                        String course = dis.readUTF();
                        String sub = dis.readUTF();
                        String table_name = author+"_"+course+"_"+sub+"_"+video;
                        int n = table_name.indexOf('.');
                        stmt = con.prepareStatement("select UID from "+table_name.substring(0, n)+"_like where UID=\'"+user+"\';");
                        
                        ResultSet res = stmt.executeQuery();
                        if(res.next())
                        {
                            dout.writeUTF("1");
                        }
                        else
                            dout.writeUTF("0");
                        stmt = con.prepareStatement("select count( UID ) from "+table_name.substring(0, n)+"_like;");
                        res = stmt.executeQuery();
                        if(res.next())
                        {
                            dout.writeUTF(""+res.getLong(1));
                        }
                        stmt = con.prepareStatement("select avg(Rate) from "+table_name.substring(0, n)+"_rate;");
                        res = stmt.executeQuery();
                        if(res.next())
                        {
                            dout.writeUTF(""+res.getDouble(1));
                            System.out.println("output : "+res.getDouble(1));
                        }
                    }
                    else if(name.equals("Liked"))
                    {
                        String author = dis.readUTF();
                        String course = dis.readUTF();
                        String video = dis.readUTF();
                        String user = dis.readUTF();
                        String sub = dis.readUTF();
                        String table_name = author+"_"+course+"_"+sub+"_"+video;
                        int n = table_name.indexOf('.');
                        stmt = con.prepareStatement("insert into "+table_name.substring(0, n)+"_like values ( \'"+user+"\' );");
                        stmt.execute();
                    }
                    else if(name.equals("Unliked"))
                    {
                        String author = dis.readUTF();
                        String course = dis.readUTF();
                        String video = dis.readUTF();
                        String user = dis.readUTF();
                        String sub = dis.readUTF();
                        String table_name = author+"_"+course+"_"+sub+"_"+video;
                        int n = table_name.indexOf('.');
                        stmt = con.prepareStatement("delete from "+table_name.substring(0, n)+"_like where UID=\'"+user+"\';");
                        stmt.execute();
                    }
                    else if(name.equals("Subscribe"))
                    {
                        String user = dis.readUTF();
                        String author = dis.readUTF();
                        stmt = con.prepareStatement("select * from "+author+"_subscriber where Name=?;");
                        stmt.setString(1,user);
                        ResultSet res = stmt.executeQuery();
                        if(res.next())
                        {
                            dout.writeUTF("1");
                        }
                        else
                            dout.writeUTF("0");
                    }
                    else if(name.equals("Subscribed"))
                    {
                        String user = dis.readUTF();
                        String author = dis.readUTF();
                        stmt = con.prepareStatement("insert into "+author+"_subscriber values (?);");
                        stmt.setString(1, user);
                        stmt.execute();
                        stmt = con.prepareStatement("insert into "+user+"_subscriber_student values (?);");
                        stmt.setString(1,author);
                        stmt.execute();
                    }
                    else if(name.equals("Unsubscribed"))
                    {
                        String user = dis.readUTF();
                        String author = dis.readUTF();
                        stmt = con.prepareStatement("delete from "+author+"_subscriber where Name=?;");
                        stmt.setString(1, user);
                        stmt.execute();
                        stmt = con.prepareStatement("delete from "+user+"_subscriber_student where Name=?;");
                        stmt.setString(1,author);
                        stmt.execute();
                    }
                    else if(name.equals("ShowComment"))
                    {
                        String author = dis.readUTF();
                        String course = dis.readUTF();
                        String video = dis.readUTF();
                        String sub = dis.readUTF();
                        String table_name = author+"_"+course+"_"+sub+"_"+video;
                        int n = table_name.indexOf(".");
                        stmt = con.prepareStatement("select * from "+table_name.substring(0,n)+"_comment");
                        ResultSet res = stmt.executeQuery();
                        while(res.next())
                        {
                            dout.writeUTF(res.getString(1));
                            dout.writeUTF(res.getString(2));
                        }
                        dout.writeUTF("exit");
                    }
                    else if(name.equals("AddComment"))
                    {
                        String author = dis.readUTF();
                        String course = dis.readUTF();
                        String video = dis.readUTF();
                        String user = dis.readUTF();
                        String comment = dis.readUTF();
                        String sub = dis.readUTF();
                        String table_name = author+"_"+course+"_"+sub+"_"+video;
                        int n = table_name.indexOf(".");
                        stmt = con.prepareStatement("insert into "+table_name.substring(0,n)+"_comment values (?,?);");
                        stmt.setString(1, user);
                        stmt.setString(2, comment);
                        stmt.execute();
                    }
                    else if(name.equals("RemComment"))
                    {
                        String author = dis.readUTF();
                        String course = dis.readUTF();
                        String video = dis.readUTF();
                        String user = dis.readUTF();
                        String comment = dis.readUTF();
                        String sub = dis.readUTF();
                        String table_name = author+"_"+course+"_"+sub+"_"+video;
                        int n = table_name.indexOf(".");
                        stmt = con.prepareStatement("delete from "+table_name.substring(0,n)+"_comment where UID=? and Comment=?");
                        stmt.setString(1,user);
                        stmt.setString(2,comment);
                        stmt.execute();
                    }
                    else if(name.equals("Subscribed_t"))
                    {
                        String user = dis.readUTF();
                        stmt = con.prepareStatement("select * from "+user+"_subscriber;");
                        ResultSet res = stmt.executeQuery();
                        while(res.next())
                        {
                            dout.writeUTF(res.getString(1));
                        }
                        dout.writeUTF("exit");
                    }
                    else if(name.equals("Subscribed_s"))
                    {
                        String user = dis.readUTF();
                        stmt = con.prepareStatement("select * from "+user+"_subscriber_student;");
                        ResultSet res = stmt.executeQuery();
                        while(res.next())
                        {
                            dout.writeUTF(res.getString(1));
                        }
                        dout.writeUTF("exit");
                    }
                    else if(name.equals("Rate"))
                    {
                        String author = dis.readUTF();
                        String course = dis.readUTF();
                        String video = dis.readUTF();
                        String user = dis.readUTF();
                        String sub = dis.readUTF();
                        int x = Integer.parseInt(dis.readUTF());
                        String table_name = author+"_"+course+"_"+sub+"_"+video;
                        int n = table_name.indexOf('.');
                        stmt = con.prepareStatement("select * from "+table_name.substring(0,n)+"_rate where UID=?;");
                        stmt.setString(1, user);
                        ResultSet res = stmt.executeQuery();
                        if(res.next())
                        {
                            dout.writeUTF("Unsuccess");
                        }
                        else
                        {
                            dout.writeUTF("Success");
                            stmt = con.prepareStatement("insert into "+table_name.substring(0,n)+"_rate values (?,?);");
                            stmt.setString(1,user);
                            stmt.setInt(2,x);
                            stmt.execute();
                        }
                    }
                    else if(name.equals("Notify"))
                    {
                        String user = dis.readUTF();
                        stmt = con.prepareStatement("select * from notification where SID=?");
                        stmt.setString(1,user);
                        ResultSet res = stmt.executeQuery();
                        while(res.next())
                        {
                            dout.writeUTF(res.getString(1));
                            dout.writeUTF(res.getString(3));
                            dout.writeUTF(res.getString(4));
                            dout.writeUTF(res.getString(5));
                            dout.writeUTF(res.getString(6));
                        }
                        dout.writeUTF("exit");
                    }
                    else if(name.equals("DeleteNotify"))
                    {
                        String user=dis.readUTF();
                        String author=dis.readUTF();
                        String course=dis.readUTF();
                        String sub=dis.readUTF();
                        String video=dis.readUTF();
                        stmt = con.prepareStatement("delete from notification where SID=? and Author=? and Course=? and sub=? and Video=?;");
                        stmt.setString(1,user);
                        stmt.setString(2,author);
                        stmt.setString(3,course);
                        stmt.setString(4,sub);
                        stmt.setString(5,video);
                        stmt.execute();
                    }
                    else if(name.equals("towatch"))
                    {
                        String author = dis.readUTF();
                        String user = dis.readUTF();
                        String course = dis.readUTF();
                        String sub = dis.readUTF();
                        String date = dis.readUTF();
                        String video = dis.readUTF();
                        stmt = con.prepareStatement("select * from towatch where Author=? and SID=? and Course=? and sub=? and Video=?;");
                        stmt.setString(1, author);
                        stmt.setString(2,user);
                        stmt.setString(3,course);
                        stmt.setString(4,sub);
                        stmt.setString(5,video);
                        ResultSet res = stmt.executeQuery();
                        if(res.next())
                        {
                            dout.writeUTF("Unsuccess");
                        }
                        else
                        {
                            dout.writeUTF("Success");
                            stmt = con.prepareStatement("insert into towatch values (?,?,?,?,?,?);");
                            stmt.setString(1,author);
                            stmt.setString(2,user);
                            stmt.setString(3,course);
                            stmt.setString(4,sub);
                            stmt.setString(5,date);
                            stmt.setString(6,video);
                            stmt.execute();
                            
                        }
                    }
                    else if(name.equals("watchlist"))
                    {
                        String user = dis.readUTF();
                        stmt = con.prepareStatement("select * from towatch where SID=?;");
                        stmt.setString(1, user);
                        ResultSet res = stmt.executeQuery();
                        while(res.next())
                        {
                            dout.writeUTF(res.getString(1));
                            dout.writeUTF(res.getString(3));
                            dout.writeUTF(res.getString(4));
                            dout.writeUTF(res.getString(5));
                            dout.writeUTF(res.getString(6));
                        }
                        dout.writeUTF("exit");
                    }
                    else if(name.equals("RemToWatch"))
                    {
                        String user = dis.readUTF();
                        String author = dis.readUTF();
                        String course = dis.readUTF();
                        String sub = dis.readUTF();
                        String video = dis.readUTF();
                        stmt = con.prepareStatement("delete from towatch where SID=? and Author=? and Course=? and sub=? Video=?;");
                        stmt.setString(1, user);
                        stmt.setString(2,author);
                        stmt.setString(3,course);
                        stmt.setString(4,sub);
                        stmt.setString(5,video);
                        stmt.execute();
                    }
                }
                catch(Exception e)
                {
                    break;
                }
            }
            s.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Server : Some exception occured "+e.getMessage());
        }
        finally{
            try{
                s.close();
                dis.close();
                dout.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }
}
public class TutorsPoint{

    static Object obj;
    static ServerSocket ss;
    
    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("Enter the port number !!!");
        obj = new Object();
        Scanner sc=new Scanner(System.in);
        int port=Integer.parseInt(sc.next());
        Connection con = Connect_db.still_connecting();
        ss = new ServerSocket(port);
        ServerSocket ss1;
        ss1 = new ServerSocket(8554);
        int i=0;
        while(true){
            try {
                System.out.println("Waiting for client requests");
                Socket s=ss.accept();
                i++;
                System.out.println("Connection Established");
                Server t1=new Server(s,con,obj,i,ss1);
                t1.start();
            } 
            catch(Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
                ss.close();
                break;
            }
        }
    }
}
