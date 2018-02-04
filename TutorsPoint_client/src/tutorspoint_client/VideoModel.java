/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorspoint_client;

public class VideoModel {
    
    String videoName;
    String authorID;
    String date;
    String courseName;
    String Tag;
    String sub;

    public VideoModel(String videoName, String authorID, String date, String courseName, String Tag, String sub) {
        this.videoName = videoName;
        this.authorID = authorID;
        this.date = date;
        this.courseName = courseName;
        this.Tag = Tag;
        this.sub = sub;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String Tag) {
        this.Tag = Tag;
    }
    
    public void setSub(String sub){
        this.sub = sub;
    }
    
    public String getSub(){
        return this.sub;
    }
}
