package cn.edu.swu.oldcontact.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cn.edu.swu.oldcontact.javaBean.LifeContentCommentItem;
import cn.edu.swu.oldcontact.javaBean.LifeItem;

@Dao
public interface LifeCommentDao {
    @Query("SELECT * FROM LifeContentCommentItem")
    List<LifeContentCommentItem> getAll();

    @Query("SELECT * FROM LifeContentCommentItem WHERE lifeId = :lifeId")
    List<LifeContentCommentItem> getItemById(int lifeId);

    @Insert
    void insert(LifeContentCommentItem item);

    @Delete
    void delete(LifeContentCommentItem item);


    @Query("DELETE FROM LifeContentCommentItem")
    void deleteAll();



}
