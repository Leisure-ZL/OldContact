package cn.edu.swu.oldcontact.javaBean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import butterknife.BindView;

@Entity
public class LifeContentCommentItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "headImg")
    private int headImg;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "like")
    private int like;
    @ColumnInfo(name = "lifeId")
    private int lifeId;     //LifeItem中主键
    @ColumnInfo(name = "clickFlag")
    private int clickFlag;

    public int getClickFlag() {
        return clickFlag;
    }

    public void setClickFlag(int clickFlag) {
        this.clickFlag = clickFlag;
    }

    public int getLifeId() {
        return lifeId;
    }

    public void setLifeId(int lifeId) {
        this.lifeId = lifeId;
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

    public int getHeadImg() {
        return headImg;
    }

    public void setHeadImg(int headImg) {
        this.headImg = headImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
