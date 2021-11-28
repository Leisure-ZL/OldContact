package cn.edu.swu.oldcontact.javaBean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import cn.edu.swu.oldcontact.utils.ItemConverter;

@Entity
@TypeConverters(ItemConverter.class)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "headImg")
    private String headImg;
    @ColumnInfo(name = "careList")
    private List<String> careList;
    @ColumnInfo(name = "integral")
    private int integral;


    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public List<String> getCareList() {
        return careList;
    }

    public void setCareList(List<String> careList) {
        this.careList = careList;
    }


}
