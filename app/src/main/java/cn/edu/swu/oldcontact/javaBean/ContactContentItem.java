package cn.edu.swu.oldcontact.javaBean;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ContactContentItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "index")
    private int index;
    @ColumnInfo(name = "bgImg")
    private String bgImg;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "dist")
    private String dist;
    @ColumnInfo(name = "allNum")
    private int allNum;
    @ColumnInfo(name = "alreadyNum")
    private int alreadyNum;
    @ColumnInfo(name = "actPlace")
    private String actPlace;
    @ColumnInfo(name = "actPhoneNum")
    private String actPhoneNum;
    @ColumnInfo(name = "note")
    private String note;



    public String getActPlace() {
        return actPlace;
    }

    public void setActPlace(String actPlace) {
        this.actPlace = actPlace;
    }

    public String getActPhoneNum() {
        return actPhoneNum;
    }

    public void setActPhoneNum(String actPhoneNum) {
        this.actPhoneNum = actPhoneNum;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }



    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getAlreadyNum() {
        return alreadyNum;
    }

    public void setAlreadyNum(int alreadyNum) {
        this.alreadyNum = alreadyNum;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }


}
