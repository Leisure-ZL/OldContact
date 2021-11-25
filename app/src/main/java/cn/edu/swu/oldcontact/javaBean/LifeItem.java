package cn.edu.swu.oldcontact.javaBean;

import android.graphics.Bitmap;
import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import cn.edu.swu.oldcontact.utils.ItemConverter;

@Entity
@TypeConverters(ItemConverter.class)
public class LifeItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "index")
    private int index;
    @ColumnInfo(name = "imgIdList")
    private List<String> imgIdList;
    @ColumnInfo(name = "headImgId")
    private int headImgId;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "likeNum")
    private int likeNum;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "time")
    private String time;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImgIdList() {
        return imgIdList;
    }

    public void setImgIdList(List<String> imgIdList) {
        this.imgIdList = imgIdList;
    }

    public int getHeadImgId() {
        return headImgId;
    }

    public void setHeadImgId(int headImgId) {
        this.headImgId = headImgId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }


}
